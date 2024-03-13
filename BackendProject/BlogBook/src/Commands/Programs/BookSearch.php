<?php

namespace Commands\Programs;

use Commands\AbstractCommand;
use Commands\Argument;
use Database\MySQLWrapper;

class BookSearch extends AbstractCommand{
    protected static ?string $alias = 'book-search';
    protected static bool $requiredCommandValue = false;

    public static function getArguments():array{
        return [
            (new Argument("isbn"))->description("The ISBN of the book you want to search for.")->required(true)->allowAsShort(true),
        ];
    }

    public function execute():int{
        $isbn = $this->getArgumentValue('isbn');
        $timezone = new \DateTimeZone('Asia/Tokyo');

        // キャッシュデータベースを確認し、データがあればそれを返す
        // 更新日付が30日以上前の場合は、OpenLibrary API からデータを取得し、キャッシュデータベースを更新する
        $hasCache = false;
        $needToUpdate = false;
    
        $mysql = new MySQLWrapper();
        $key = "ISBN-{$isbn}";
        $query = "SELECT * FROM CacheBookSearch WHERE `key` = '{$key}';";

        $result = $mysql->query($query);
        if($result === false){
            $this->log("Failed to fetch data from CacheBookSearch");
            return 1;
        }

        if($result->num_rows > 0){
            $this->log("Cache data found");
            $hasCache = true;
            // resultをfetchして連想配列に変換
            $row = $result->fetch_assoc();

            $updatedAt = new \DateTime($row['updated_at'], $timezone);
            $now = new \DateTime('now', $timezone);

            $needToUpdate = $this->checkToNeedToUpdate($updatedAt, $now);
        } 

        if($hasCache && !$needToUpdate){
            // キャッシュデータベースからデータを取得する
            // 更新日のみを更新して終了する
            $query = "UPDATE CacheBookSearch SET `updated_at` = NOW() WHERE `key` = '{$key}'";
            $result = $mysql->query($query);
            if($result === false){
                $this->log("Failed to update CacheBookSearch");
                return 1;
            }
            $this->log("updated_at is up to date");
        }else{
            // OpenLibrary API からデータを取得する
            $url = "https://openlibrary.org/api/books?bibkeys=ISBN:{$isbn}&format=json";

            $curl = curl_init();
            curl_setopt($curl, CURLOPT_URL, $url);
            curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

            $response = curl_exec($curl);

            if($response === false){
                $this->log("Failed to fetch data from OpenLibrary API");
                return 1;
            }
            $this->log("Data fetched from OpenLibrary API");

            curl_close($curl);
            // グローバル名前空間にアクセスする場合は、バックスラッシュをつける
            $datetime = new \DateTime('now', $timezone);

            $createdAt = $datetime->format('Y-m-d H:i:s');
            if($needToUpdate){
                // キャッシュデータベースを更新する
                $query = "UPDATE CacheBookSearch SET `value` = '{$response}', `updated_at` = '{$createdAt}' WHERE `key` = '{$key}'";
                $result = $mysql->query($query);
                if($result === false){
                    $this->log("Failed to update CacheBookSearch");
                    return 1;
                }
                $this->log("Cache data updated");
            }else{
                // キャッシュデータベースにデータを追加する
                $query = "INSERT INTO CacheBookSearch (`key`, `value`, `created_at`, `updated_at`) 
                    VALUES ('{$key}', '{$response}', '{$createdAt}', '{$createdAt}')";
                
                $result = $mysql->query($query);

                if($result === false){
                    $this->log("Failed to insert data into CacheBookSearch");
                    return 1;
                }
                $this->log("Cache data inserted");

            }
        }

        // 取得したデータを表示する
        // キャッシュデータベースからデータを取得する
        $query = "SELECT * FROM CacheBookSearch WHERE `key` = '{$key}'";
        $result = $mysql->query($query);

        if($result === false){
            $this->log("Failed to fetch data from CacheBookSearch");
            return 1;
        }

        $mysql->close();
        // 一度fetchすると、次のfetchは次の行を取得するので
        // 一度fetchして$mapにデータを格納し、それを使って表示する
        $map = $result->fetch_assoc();
        $this->showBookInfo($map, $isbn);
        
        return 0;

    }

    private function checkToNeedToUpdate(\DateTime $updatedAt, \DateTime $now) : bool{
        $this->log("Cache data updated_at is {$updatedAt->format('Y-m-d H:i:s')}");
        $updatedAt = $updatedAt->getTimestamp();
        $this->log("Now is {$now->format('Y-m-d H:i:s')}");
        $now = $now->getTimestamp();

        $diff = $now - $updatedAt;

        $this->log("Cache data is generated {$diff} seconds ago");
        if($diff > 30 * 24 * 60 * 60){
            $this->log("Cache data is too old");
            return true;
        }else {
            return false;
        }
    }

    private function showBookInfo(array $map, string $isbn): void{
        $value = json_decode($map["value"],true)["ISBN:{$isbn}"];
        $this->log("-----book information-----");
        $this->log("bib_key: ".$value["bib_key"]);
        $this->log("info_url: ".$value["info_url"]);
        $this->log("preview: ".$value["preview"]);
        $this->log("preview_url: ".$value["preview_url"]);
        $this->log("thumbnail_url: ".$value["thumbnail_url"]);
        $this->log("--------------------------");
        // $this->log("key: ".$map["key"]);
    }
}