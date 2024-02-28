<?php

namespace Persons\Employees;

use Persons\Employees\Employee;
use FoodOrders\FoodOrder;

class Chef extends Employee{

    public function __construct(String $name, int $age, String $address, int $employeeId, int $salary){
        parent::__construct($name, $age, $address, $employeeId, $salary);
    }

    public function prepareFood(FoodOrder $foodOrder, array $interestedTasteMap): String{
        $res = "";
        foreach($foodOrder -> getfoodOrderList() as $foodItem){
            $i = $interestedTasteMap[$foodItem -> getCategory()];
            for($j = 0; $j < $i; $j++){
                $res .= "Chef has prepared ".$foodItem -> getName() . "\n";
            }
        }
        return $res;
    }
}