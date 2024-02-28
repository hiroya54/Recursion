<?php

abstract class FoodItem{
    protected String $name;
    protected String $description;
    protected double $price;

    public function __construct(String $name, String $description, double $price){
        $this->name = $name;
        $this->description = $description;
        $this->price = $price;
    }

    abstract static function getCategory(): String;
}