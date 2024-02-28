<?php

namespace FoodItems;

class CheeseBurger extends FoodItem{
    const CATEGORY = "CheeseBurger";

    public function __construct(){
        parent::__construct(self::getCategory(), "tasty burger with cheese", 200);
    }

    static function getCategory(): String{
        return self::CATEGORY;
    }
}
