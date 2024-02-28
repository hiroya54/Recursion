<?php

namespace FoodItems;

class Spaghetti extends FoodItem{
    const CATEGORY = "Spaghetti";

    public function __construct(){
        parent::__construct(self::getCategory(), "tasty pasta with red sauce", 250);
    }

    static function getCategory(): String{
        return self::CATEGORY;
    }
}