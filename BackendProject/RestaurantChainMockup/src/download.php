<?php

spl_autoload_extensions('.php');
spl_autoload_register();

require_once "vendor/autoload.php";


//POSTリクエストからパラメータを取得
$minNumOfRestaurantChains = $_POST["minNumOfRestaurantChains"] ?? 1;
$maxNumOfRestaurantChains = $_POST["maxNumOfRestaurantChains"] ?? 3;
$minNumOfRestaurantLocations = $_POST["minNumOfRestaurantLocations"] ?? 1;
$maxNumOfRestaurantLocations = $_POST["maxNumOfRestaurantLocations"] ?? 3;
$minNumOfEmployees = $_POST["minNumOfEmployees"] ?? 1;
$maxNumOfEmployees = $_POST["maxNumOfEmployees"] ?? 3;
$format = $_POST["format"] ?? "html";

$restaurantChains = \Helpers\RandomGenerator::restaurantChains(
    $minNumOfRestaurantChains, $maxNumOfRestaurantChains,
    $minNumOfRestaurantLocations, $maxNumOfRestaurantLocations,
    $minNumOfEmployees, $maxNumOfEmployees);

if ($format === "json") {
    header("Content-Type: application/json");
    header("Content-Disposition: attachment; filename=restaurantChains.json");
    $restaurantChainsArray = array_map(fn($restaurantChain)=>$restaurantChain->toArray(), $restaurantChains);
    echo json_encode($restaurantChainsArray);
}elseif ($format === "txt") {
    header("Content-Type: text/plain");
    header("Content-Disposition: attachment; filename=restaurantChains.txt");
    foreach ($restaurantChains as $restaurantChain) {
        echo $restaurantChain->toString();
    }
}elseif ($format === "markdown") {
    header("Content-Type: markdown");
    header("Content-Disposition: attachment; filename=restaurantChains.md");
    foreach ($restaurantChains as $restaurantChain) {
        echo $restaurantChain->toMarkdown();
    }
}else {
    header("Content-Type: text/html");
    foreach ($restaurantChains as $restaurantChain) {
        echo $restaurantChain->toHTML();
    }
}