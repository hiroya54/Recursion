<?php

use FoodItems\FoodItem;

class Spaghetti extends FoodItem{
    static $CATEGORY = Spaghetti::classname;

    public function __construct(){
        parent::__construct(self::getCategory(), 250, "tasty pasta with red sauce");
    }

    static function getCategory(): String{
        return self::$CATEGORY;
    }
}