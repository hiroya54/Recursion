<?php

namespace FoodItems;

abstract class FoodItem{
    protected String $name;
    protected String $description;
    protected int $price;

    public function __construct(String $name, String $description, int $price){
        $this->name = $name;
        $this->description = $description;
        $this->price = $price;
    }

    abstract static function getCategory(): String;

    public function getName(): String{
        return $this->name;
    }

    public function getDescription(): String{
        return $this->description;
    }

    public function getPrice(): int{
        return $this->price;
    }
}
