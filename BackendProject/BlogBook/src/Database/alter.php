<?php

namespace Database;

$mysqli = new MySQLWrapper();

$sqlDirectory = __DIR__ . "/SQL/Alter";
$files = scandir($sqlDirectory);

foreach($files as $file){
    if($file === "." || $file === "..") continue;

    $query = file_get_contents($sqlDirectory . "/" . $file);

    if($mysqli->multi_query($query)){
        echo "ファイル {$file} からテーブルを修正しました。".PHP_EOL;
        do{
            if($result = $mysqli->store_result()){
                $result->free();
            }
        }while($mysqli->more_results() && $mysqli->next_result());
    }else{
        throw new Exception('Could not modify tables.');
    }
    
}

$mysqli->close();