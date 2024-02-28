<?php

use Persons\Employees\Employee;
use FoodOrders\FoodOrder;

class Chef extends Employee{
    public function __construct(String $name, int $age, String $address, int $employeeId, double $salary){
        parent::__construct($name, $age, $address, $employeeId, $salary);
    }

    public function prepareFood(FoodOrder $foodOrder): String{
        return "Food prepared";
    }
}