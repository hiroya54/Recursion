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

    public function toHTML() :string{
        return sprintf("
        <div class='border'>
            <p class='m-0 p-2'>ID: %d, Job Title; %s, %s %s, Start Date: %s</p>
        </div>",
        $this->id, $this->jobTitle, $this->firstName, $this->lastName, $this->startDate->format('Y-m-d')
    );
    }

    public function toString() :string{
    return sprintf("ID: %d\nFirstName: %s\nLastName: %s\nEmail: %s\nPhoneNumber: %s\nAddress: %s\nBirthDate: %s\nMembershipExpirationDate: %s\nRole: %s\nJobTitle: %s\nSalary: %f\nStartDate: %s\n",
        $this->id,$this->firstName,$this->lastName,$this->email,$this->hashedPassword,$this->phoneNumber,$this->address,$this->birthDate->format('Y-m-d'),$this->membershipExpirationDate->format('Y-m-d'),$this->role,$this->jobTitle,$this->salary,$this->startDate->format('Y-m-d') );
    }

    public function toMarkdown() :string{
        return "#### Employee: {$this->firstName} {$this->lastName}
        - Email: {$this->email}
        - Phone Number: {$this->phoneNumber}
        - Address: {$this->address}
        - Birth Date: {$this->birthDate->format('Y-m-d')}
        - Membership Expiration Date: {$this->membershipExpirationDate->format('Y-m-d')}
        - Role: {$this->role}
        - Job Title: {$this->jobTitle}
        - Salary: {$this->salary}
        - Start Date: {$this->startDate->format('Y-m-d')}";
    }

    public function toArray() :array{
        return [
            "id" => $this->id,
            "firstName" => $this->firstName,
            "lastName" => $this->lastName,
            "email" => $this->email,
            "phoneNumber" => $this->phoneNumber,
            "address" => $this->address,
            "birthDate" => $this->birthDate->format('Y-m-d'),
            "membershipExpirationDate" => $this->membershipExpirationDate->format('Y-m-d'),
            "role" => $this->role,
            "jobTitle" => $this->jobTitle,
            "salary" => $this->salary,
            "startDate" => $this->startDate->format('Y-m-d')
        ];
    }


}