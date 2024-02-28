<?php

namespace Invoices;

class Invoice{
    protected int $finalPrice;
    protected string $orderTime;
    protected string $estimatedTimeInMinutes;

    public function __construct(int $finalPrice, string $orderTime, string $estimatedTimeInMinutes){
        $this->finalPrice = $finalPrice;
        $this->orderTime = $orderTime;
        $this->estimatedTimeInMinutes = $estimatedTimeInMinutes;
    }

    /**
     * Get the final price of the invoice.
     *
     * @return int
     */
    public function getFinalPrice(): int{    
        return $this->finalPrice;
    }  

    /**
     * Get the order time of the invoice.
     *
     * @return string
     */
    public function getOrderTime(): string{
        return $this->orderTime;
    }

    /**
     * Get the estimated time in minutes of the invoice.
     *
     * @return string
     */
    public function getEstimatedTimeInMinutes(): string{
        return $this->estimatedTimeInMinutes;
    }
}
