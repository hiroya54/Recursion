<?php

namespace Persons\Customers;

use Persons\Person;
use Restaurants\Restaurant;
use Invoices\Invoice;


class Customer extends Person{
    protected array $interestedTasteMap; 

    public function __construct(String $name, int $age, String $address, array $interestedTasteMap){
        parent::__construct($name, $age, $address);
        $this->interestedTasteMap = $interestedTasteMap;
    }

    public function interestedCategories(Restaurant $restaurant): array{
        $menu = $restaurant->getMenu();
        // orderListはfoodItemを格納する配列
        $orderList = [];
        foreach($menu as $foodItem){
            $foodCategory = $foodItem -> getCategory();
            if(array_key_exists($foodCategory, $this->interestedTasteMap)){
                $orderList[] = $foodItem;
            }
        }
        echo "I want to order the following menu: \n";
        foreach($orderList as $foodItem){
            echo "* ". $foodItem -> getCategory() . " × " . $this -> interestedTasteMap[$foodItem -> getCategory()] . "\n";
        }

        return $orderList;
    }

    public function order(Restaurant $restaurant):invoice{
        echo "Welcome to our restaurant! Here is our menu: \n";
        foreach($restaurant->getMenu() as $foodItem){
            echo "* " . $foodItem -> getName() . ": " . $foodItem -> getPrice() . " yen\n";
        }
        echo "What would you like to order? \n\n";
        $orderList = $this->interestedCategories($restaurant);
        $orderCounts = [];
        foreach($orderList as $foodItem){
            $foodCategory = $foodItem -> getCategory();
            $orderCounts[$foodCategory] = $this -> interestedTasteMap[$foodCategory];
        }
        
        echo "\n";
        echo "OK! Please wait while we prepare your order. \n\n";
        $invoice = $restaurant -> order($orderList, $orderCounts);
        
        return $invoice;
    }
}