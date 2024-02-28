<?php

namespace Persons\Employees;

use FoodOrders\FoodOrder;
use Invoices\Invoice;

class Cashier extends Employee{
    public function __construct(String $name, int $age, String $address, int $employeeId, int $salary){
        parent::__construct($name, $age, $address, $employeeId, $salary);
    }

    public function generateOrder(array $orderList): FoodOrder{
        $foodOrder = new FoodOrder($orderList, date("D M d, Y G:i"));
        return $foodOrder;
    }

    public function generateInvoice(FoodOrder $foodOrder, array $interestedTasteMap):invoice{
        $total = 0;
        foreach($foodOrder -> getfoodOrderList() as $foodItem){
            $total += $foodItem->getPrice() * $interestedTasteMap[$foodItem -> getCategory()];
        }
        $invoice = new Invoice($total, $foodOrder -> getOrderTime(), date("D M d, Y G:i"));
        return $invoice;
    }
}