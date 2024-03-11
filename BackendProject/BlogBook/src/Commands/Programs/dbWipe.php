<?php

namespace Commands\Programs;

use Commands\AbstractCommand;
use Commands\Argument;
use Helpers\Settings;
use Database\MySQLWrapper;
use Exception;

class DBWipe extends AbstractCommand{
    protected static ?string $alias = 'db-wipe';
    protected static bool $requiredCommandValue = false;
    private static string $mysqlPath = "/Applications/MAMP/Library/bin/";
    private static string $dumpPath = "./Database/Backups/";

    public static function getArguments(): array{
        return [
            // requiredはその引数が値を持つ必要があるかどうかを示します
            (new Argument('dump'))->description('Dump the database before wiping it.')->required(false)->allowAsShort(true),
            (new Argument('restore'))->description('Restore the database from dump')->required(false)->allowAsShort(true),
        ];
    }
    public function execute(): int{
        $dump = $this->getArgumentValue('dump');
        $restore = $this->getArgumentValue('restore');
        if($dump){
            $this->log("Dumping the database...");
            $this->dump();
        }else if($restore){
            $this->log("Restoring the database...");
            $this->restore();
        }else{
            $this->log("Wiping the database...");
            $this->wipe();
        }

        return 0;
    }

    private function dump(): void{
        $mysqldumpPath = self::$mysqlPath . "mysqldump";
        $dumpFilePath = self::$dumpPath . "dump.sql";
        $username = Settings::env('DATABASE_USER');
        $password = Settings::env('DATABASE_USER_PASSWORD');
        $database = Settings::env('DATABASE_NAME');
        exec("{$mysqldumpPath} -u {$username} -p{$password} {$database} > {$dumpFilePath}");
        $this->log("Database dumped...");
    }

    private function restore(): void{
        $mysqlPath = self::$mysqlPath . "mysql";
        $dumpFilePath = self::$dumpPath . "dump.sql";
        $username = Settings::env('DATABASE_USER');
        $password = Settings::env('DATABASE_USER_PASSWORD');
        $database = Settings::env('DATABASE_NAME');
        if(!file_exists($dumpFilePath)) throw new Exception("Dump file does not exist");
        exec("{$mysqlPath} -u {$username} -p{$password} {$database} < {$dumpFilePath}");
        $this->log("Database restored...");
    }

    private function wipe(): void{
        $mysqli = new MySQLWrapper();
        $result = $mysqli->query("SHOW TABLES");
        while($row = $result->fetch_array()){
            $this->log("Dropping table {$row[0]}...");

            // 外部キー制約を無効化
            $mysqli->query("SET FOREIGN_KEY_CHECKS = 0");
            $mysqli->query("DROP TABLE {$row[0]}");
            $mysqli->query("SET FOREIGN_KEY_CHECKS = 1");

            $this->log("Table {$row[0]} dropped...");
        }
    }
}