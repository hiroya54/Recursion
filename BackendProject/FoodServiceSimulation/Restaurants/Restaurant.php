<?php

use Persions\Employees\Employee;
use Persions\Employees\Chef;
use Persions\Employees\Cashier;
use FoodItems\FoodItem;
use FoodOrders\FoodOrder;


class Restaurant{
    protected array $menu;
    protected array $employees;

    function __construst(array $menu, array $employees){
        $this->menu = $menu;
        $this->employees = $employees;
    }

    public function order(array $orderList): invoice{

        foreach($this->employees as $employee){
            if($employee instanceof Cashier){
                $foodOrder = $employee -> generateOrder($orderList);
            }
        }
        
        foreach($this->employees as $employee){
            if($employee instanceof Chef){
                echo $employee->prepareFood($foodOrder);
            }
        }

        foreach($this->employees as $employee){
            if($employee instanceof Cashier){
                $invoice = $employee->generateInvoice($foodOrder);
            }
        }

    }

    public function getMenu(): array
    {
        return $this->menu;
    }

    public function getEmployees(): array
    {
        return $this->employees;
    }
    
}