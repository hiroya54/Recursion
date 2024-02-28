<?php

use Persions\Employees\Employee;
use FoodOrders\FoodOrder;
use Invoices\Invoice;

class Cashier extends Employee{
    public function __construct(String $name, int $age, String $address, int $employeeId, double $salary){
        parent::__construct($name, $age, $address, $employeeId, $salary);
    }

    public function generateOrder(array $orderList): FoodOrder{
        $foodOrder = new FoodOrder($orderList, date("D M d, Y G:i"));

        return $foodOrder;
    }

    public function generateInvoice(){

    }
}