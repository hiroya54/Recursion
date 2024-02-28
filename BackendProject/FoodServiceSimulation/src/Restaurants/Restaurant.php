<?php

namespace Restaurants;

use Persons\Employees\Employee;
use Persons\Employees\Chef;
use Persons\Employees\Cashier;
use FoodItems\FoodItem;
use FoodOrders\FoodOrder;
use Invoices\Invoice;

class Restaurant{
    protected array $menu;
    protected array $employees;

    function __construct(array $menu, array $employees){
        $this->menu = $menu;
        $this->employees = $employees;
    }

    public function order(array $orderList, array $orderCounts): invoice{

        foreach($this->employees as $employee){
            if($employee instanceof Cashier){
                $foodOrder = $employee -> generateOrder($orderList, $orderCounts);
                echo "Casher has generated the order. \n\n";
            }
        }
        
        foreach($this->employees as $employee){
            if($employee instanceof Chef){
                echo $employee->prepareFood($foodOrder);
            }
        }
        echo "\n";
        echo "Your order is ready! \n\n";
        echo "Costomer is eating the food. \n\n";
        echo "Customer has finished eating. \n\n";
        foreach($this->employees as $employee){
            if($employee instanceof Cashier){
                $invoice = $employee->generateInvoice($foodOrder);
            }
        }
        echo "Here is your invoice: \n";
        echo "-----------------------------------\n";
        echo $invoice -> getOrderTime() . "\n";
        echo "Total Price: ".$invoice -> getFinalPrice() . " yen\n";
        echo "-----------------------------------\n";

        return $invoice;
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