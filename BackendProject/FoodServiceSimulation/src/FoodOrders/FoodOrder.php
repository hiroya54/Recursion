<?php

namespace FoodOrders;
use FoodItems\FoodItem;

class FoodOrder{
    protected array $foodOrderList;
    protected string $orderTime;

    public function __construct(array $foodOrderList, string $orderTime){
        $this->foodOrderList = $foodOrderList;
        $this->orderTime = $orderTime;
    }

    /**
     * Get the items in the food order.
     *
     * @return array
     */
    public function getfoodOrderList(): array
    {
        return $this->foodOrderList;
    }

    /**
     * Get the order time of the food order.
     *
     * @return string
     */
    public function getOrderTime(): string
    {
        return $this->orderTime;
    }
    
}