<?php

use FoodItems\FoodItem;

class FoodOrder{
    protected array $items;
    protected TimeStamp $orderTime;

    public function __construct(array $items, TimeStamp $orderTime){
        $this->items = $items;
        $this->orderTime = $orderTime;
    }
}