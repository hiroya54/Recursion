<?php

spl_autoload_extensions('.php');
spl_autoload_register();

require_once "vendor/autoload.php";

$restaurantChains = Helpers\RandomGenerator::restaurantChains();

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
        <!-- restaurantchainのtoHTML使う -->
        <?php foreach($restaurantChains as $restaurantChain): ?>
            <?php $html = $restaurantChain->toHTML(); ?>
            <?php echo $html ?>
        <?php endforeach; ?>
    </div>  
</body>
</html>
