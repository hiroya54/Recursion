<?php

namespace Users;

use DateTime;

class Employee extends User{
    private string $jobTitle;
    private float $salary;
    private DateTime $startDate;
    private array $awards;
    
    public function __construct(
        int $id, string $firstName, string $lastName, string $email, 
        string $password, string $phoneNumber, string $address, 
        DateTime $birthDate, DateTime $membershipExpirationDate, string $role,
        string $jobTitle, float $salary, DateTime $startDate, array $awards){

        parent::__construct($id, $firstName, $lastName, $email, $password, $phoneNumber,
        $address, $birthDate, $membershipExpirationDate, $role);
        $this->jobTitle = $jobTitle;
        $this->salary = $salary;
        $this->startDate = $startDate;
        $this->awards = $awards;
            
        }

}