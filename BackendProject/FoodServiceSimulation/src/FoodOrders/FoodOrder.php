<?php

namespace FoodOrders;
use FoodItems\FoodItem;

class FoodOrder{
    protected array $foodOrderList;
    protected array $foodOrderCount;
    protected string $orderTime;

    public function __construct(array $foodOrderList, array $foodOrderCount, string $orderTime){
        $this->foodOrderList = $foodOrderList;
        $this->foodOrderCount = $foodOrderCount;
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
     * Get the counts of the items in the food order.
     *
     * @return array
     */
    public function getfoodOrderCounts(): array{
        return $this->foodOrderCount;
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