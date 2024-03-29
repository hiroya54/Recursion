<?php

namespace Persons\Employees;

use FoodOrders\FoodOrder;
use Invoices\Invoice;

class Cashier extends Employee{
    public function __construct(String $name, int $age, String $address, int $employeeId, int $salary){
        parent::__construct($name, $age, $address, $employeeId, $salary);
    }

    public function generateOrder(array $orderList, array $orderCounts): FoodOrder{
        $foodOrder = new FoodOrder($orderList, $orderCounts, date("D M d, Y G:i"));
        return $foodOrder;
    }

    public function generateInvoice(FoodOrder $foodOrder):invoice{
        $total = 0;
        $orderCounts = $foodOrder -> getFoodOrderCounts();
        foreach($foodOrder -> getfoodOrderList() as $foodItem){
            $total += $foodItem->getPrice() * $orderCounts[$foodItem -> getCategory()];
        }
        $invoice = new Invoice($total, $foodOrder -> getOrderTime(), date("D M d, Y G:i"));
        return $invoice;
    }
}
