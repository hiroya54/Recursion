<?php

spl_autoload_extensions(".php"); 
spl_autoload_register();

$cheeseBurger = new FoodItems\CheeseBurger();
$fettuccine = new FoodItems\Fettuccine();
$hawaiianPizza = new FoodItems\HawaiianPizza();
$spaghetti = new FoodItems\Spaghetti();

$invah = new Persons\Employees\Chef("Invah", 25, "Bacoor", 1, 20000);
$nadia = new Persons\Employees\Cashier("Nadia", 23, "Imus", 2, 15000);

$saizeriya  = new Restaurants\Restaurant([$cheeseBurger, $fettuccine, $hawaiianPizza, $spaghetti], [$invah, $nadia]);

$interestedTasteMap = [
    "CheeseBurger"=> 2,
    "Fettuccine"=> 3,
    "HawaiianPizza"=> 2,
    "Spaghetti"=> 3
];

$customer = new Persons\Customers\Customer("John", 25, "Bacoor", $interestedTasteMap);

$invoice = $customer->order($saizeriya);
echo "customer paid the invoice. \n";