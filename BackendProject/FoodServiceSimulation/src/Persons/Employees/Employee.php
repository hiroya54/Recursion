<?php

namespace Persons\Employees;

use Persons\Person;

abstract class Employee extends Person{
    protected int $employeeId;
    protected int $salary;

    public function __construct(String $name, int $age, String $address, int $employeeId, int $salary){
        parent::__construct($name, $age, $address);
        $this->employeeId = $employeeId;
        $this->salary = $salary;
    }
}