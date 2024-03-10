<?php

spl_autoload_extensions(".php");
spl_autoload_register();

use Database\MySQLWrapper;

$opts = getopt("",["create","alter"]);
if(isset($opts["create"])){
    printf("Database migration enabled.".PHP_EOL);
    include("Database/setup.php");
    printf('Database migration ended.'.PHP_EOL);
}elseif(isset($opts["alter"])){
    printf("Database alteration enabled.".PHP_EOL);
    include("Database/alter.php");
    printf('Database alteration ended.'.PHP_EOL);
};

$mysqli = new MySQLWrapper();

$charset = $mysqli->get_charset();
if($charset === null) throw new Exception('Charset could be read');

// データベースの文字セット、照合順序、および統計に関する情報を取得します。
printf(
    "%s's charset: %s.%s",
    $mysqli->getDatabaseName(),
    $charset->charset,
    PHP_EOL
);

printf(
    "collation: %s.%s",
    $charset->collation,
    PHP_EOL
);

// 接続を閉じるには、closeメソッドが使用されます。

$mysqli->close();