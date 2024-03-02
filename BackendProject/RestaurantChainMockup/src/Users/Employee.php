<?php

namespace Users;

use DateTime;
use Interfaces\FileConvertible;

class Employee extends User implements FileConvertible{
    private string $jobTitle;
    private float $salary;
    private DateTime $startDate;
    
    public function __construct(
        int $id, string $firstName, string $lastName, string $email, 
        string $password, string $phoneNumber, string $address, 
        DateTime $birthDate, DateTime $membershipExpirationDate, string $role,
        string $jobTitle, float $salary, DateTime $startDate){

        parent::__construct($id, $firstName, $lastName, $email, $password, $phoneNumber,
        $address, $birthDate, $membershipExpirationDate, $role);
        $this->jobTitle = $jobTitle;
        $this->salary = $salary;
        $this->startDate = $startDate;
            
    }

    public function toHTML(){
        return sprintf("
        <div class='border'>
            <p class='m-0 p-2'>ID: %d, Job Title; %s, %s %s, Start Date: %s</p>
        </div>",
        $this->id, $this->jobTitle, $this->firstName, $this->lastName, $this->startDate->format('Y-m-d')
    );
    }

}