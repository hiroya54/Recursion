<?php

use Persons\Person;

abstract class Employee extends Person{
    protected int $employeeId;
    protected double $salary;

    public function __construct(String $name, int $age, String $address, int $employeeId, double $salary){
        parent::__construct($name, $age, $address);
        $this->employeeId = $employeeId;
        $this->salary = $salary;
    }
}