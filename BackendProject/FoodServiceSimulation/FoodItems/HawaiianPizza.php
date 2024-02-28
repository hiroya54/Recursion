<?php

use FoodItems\FoodItem;

class HawaiianPizza extends FoodItem{
    static $CATEGORY = HawaiianPizza::classname;

    public function __construct(){
        parent::__construct(self::getCategory(), 400, "tasty pizza with pineapple and ham");
    }

    static function getCategory(): String{
        return self::$CATEGORY;
    }
}