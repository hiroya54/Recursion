<?php

namespace Database;

$mysqli = new MySQLWrapper();


$result = $mysqli->query(file_get_contents(__DIR__ . "/Examples/user-setup.sql"));

if ($result===false) throw Exception('Could not execute query.');
else print("Successfully ran all SQL setup queries.".PHP_EOL);

$mysqli->close();