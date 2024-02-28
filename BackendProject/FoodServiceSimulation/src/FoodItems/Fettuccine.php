<?php

namespace FoodItems;
use FoodItems\FoodItem;

class Fettuccine extends FoodItem{
    const CATEGORY = "Fettuccine";

    public function __construct(){
        parent::__construct(self::getCategory(), "tasty pasta with white sauce", 300);
    }

    static function getCategory(): String{
        return self::CATEGORY;
    }
}