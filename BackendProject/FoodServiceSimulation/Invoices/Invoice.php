<?php

class Invoice{
    protected double $finalPrice;
    protected TimeStamp $orderTime;
    protected int $estimatedTimeInMinutes;

    public function __construct(double $finalPrice, TimeStamp $orderTime, int $estimatedTimeInMinutes){
        $this->finalPrice = $finalPrice;
        $this->orderTime = $orderTime;
        $this->estimatedTimeInMinutes = $estimatedTimeInMinutes;
    }
}