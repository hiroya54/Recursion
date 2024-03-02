<?php

spl_autoload_extensions('.php');
spl_autoload_register();

require_once "vendor/autoload.php";

$minNumOfRestaurantChains = $_POST["minNumOfRestaurantChains"] ?? 1;
$maxNumOfRestaurantChains = $_POST["maxNumOfRestaurantChains"] ?? 3;
$minNumOfRestaurantLocations = $_POST["minNumOfRestaurantLocations"] ?? 1;
$maxNumOfRestaurantLocations = $_POST["maxNumOfRestaurantLocations"] ?? 3;
$minNumOfEmployees = $_POST["minNumOfEmployees"] ?? 1;
$maxNumOfEmployees = $_POST["maxNumOfEmployees"] ?? 3;

$restaurantChains = \Helpers\RandomGenerator::restaurantChains(
    $minNumOfRestaurantChains, $maxNumOfRestaurantChains,
    $minNumOfRestaurantLocations, $maxNumOfRestaurantLocations,
    $minNumOfEmployees, $maxNumOfEmployees);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    
</head>
<body>
    <div class="d-flex flex-column align-items-center justify-content-center mb-5">
        <?php foreach($restaurantChains as $restaurantChain): ?>
            <?php echo $restaurantChain->toHTML(); ?>
        <?php endforeach; ?>
    </div>  
</body>
</html>
