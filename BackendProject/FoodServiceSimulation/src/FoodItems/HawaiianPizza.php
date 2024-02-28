<?php

namespace FoodItems;

use FoodItems\FoodItem;

class HawaiianPizza extends FoodItem{
    const CATEGORY = "HawaiianPizza";

    public function __construct(){
        parent::__construct(self::getCategory(), "tasty pizza with pineapple and ham", 400);
    }

    static function getCategory(): String{
        return self::CATEGORY;
    }
    
}