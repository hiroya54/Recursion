<?php

namespace Companies;

use Interfaces\FileConvertible;

class RestaurantLocation implements FileConvertible{
    private string $name;
    private string $address;
    private string $city;
    private string $state;
    private string $zipCode;
    private array $employees;
    private bool $isOpen;
    private bool $hasDriveThru;

    public function __construct(string $name, string $address, string $city, string $state,
    string $zipCode, array $employees, bool $isOpen, bool $hasDriveThru)
    {
        $this->name = $name;
        $this->address = $address;
        $this->city = $city;
        $this->state = $state;
        $this->zipCode = $zipCode;
        $this->employees = $employees;
        $this->isOpen = $isOpen;
        $this->hasDriveThru = $hasDriveThru;
    }

    public function toHTML(): string{
        $html = sprintf("
        <div class='mx-2 mb-0 mt-2 border-top'>
            <details>
                <summary class='bg-lightblue text-primary p-2'>%s</summary>
                <div class='mb-2'>
                    <div class='mx-4'>
                        <p class='border-bottom mb-2 py-2'>Company Name: %s Address: %s, %s, %s ZipCode: %s</p>
                    <div>
                    <h5>Employees:</h5>
        ",
        $this->name, $this->name, $this->address, $this->city, $this->state, $this->zipCode);

        foreach($this->employees as $employee){
            $html .= $employee->toHTML();
        }

        $html .= "
                </div>
            </details>
        </div>
        ";

        return $html;
    }

    public function toString(): string{
        $employees = "";
        foreach($this->employees as $employee){
            $employees .= "{".$employee->toString()."}\n";
        }
        return sprintf("Name: %s\nAddress: %s\nCity: %s\nState: %s\nZipCode: %s\nEmployees: {%s}\nIs Open: %s\nHas Drive Thru: %s\n",
        $this->name, $this->address, $this->city, $this->state, $this->zipCode, $employees, $this->isOpen, $this->hasDriveThru);
    }

    public function toMarkdown(): string{
        $employees = "";
        foreach($this->employees as $employee){
            $employees .= $employee->toMarkdown()."\n";
        }
        return "### Restaurant Location: {$this->name}
        - Address: {$this->address} {$this->city}, {$this->state} {$this->zipCode}
        - Employees:
        $employees
        - Is Open: {$this->isOpen}
        - Has Drive Thru: {$this->hasDriveThru}";
    }
    
    public function toArray(): array{
        $employees = [];
        foreach($this->employees as $employee){
            $employees[] = $employee->toArray();
        }
        return [
            "name" => $this->name,
            "address" => $this->address,
            "city" => $this->city,
            "state" => $this->state,
            "zipCode" => $this->zipCode,
            "employees" => $employees,
            "isOpen" => $this->isOpen,
            "hasDriveThru" => $this->hasDriveThru
        ];
    }
}