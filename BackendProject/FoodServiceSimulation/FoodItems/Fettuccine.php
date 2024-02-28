<?php

use FoodItems\FoodItem;

class Fettuccine extends FoodItem{
    static $CATEGORY = Fettuccine::classname;

    public function __construct(){
        parent::__construct(self::getCategory(), 300, "tasty pasta with white sauce");
    }

    static function getCategory(): String{
        return self::$CATEGORY;
    }
}