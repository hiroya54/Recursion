<?php

use Persons\Person;


class Customer extends Person{
    protected array $interestedTaseMap; 

    public function __construct(String $name, int $age, String $address, array $interestedTaseMap){
        parent::__construct($name, $age, $address);
        $this->interestedTaseMap = $interestedTaseMap;
    }

    public function interestedCategories(Restaurant $restaurant): array{
        $menu = $restaurant->getMenu();
        $orderList = [];
        foreach($menu as $foodItem){
            if(array_key_exists($foodItem -> getCategory(), $this->interestedTasteMap)){
                $orderList[] = $foodItem;
            }
        }

        return $orderList;
    }

    public function order(Restaurant $restaurant):invoice{
        $orderList = $this->interestedCategories($restaurant);
        $restaurant -> order($orderList);
    }
}