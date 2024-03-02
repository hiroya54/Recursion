<?php

namespace Companies;

class Company{
    protected string $name;
    protected int $foundYear;
    protected string $description;
    protected string $website;
    protected string $phone;
    protected string $industry;
    protected string $ceo;
    protected bool $isPubliclyTraded;
    protected string $country;
    protected string $founder;
    protected int $totalEmployees;

    public function __construct(
        string $name, int $foundYear, string $description, string $website, 
        string $phone, string $industry, string $ceo, bool $isPubliclyTraded, 
        string $country, string $founder, int $totalEmployees
    ){
        $this->name = $name;
        $this-> foundYear = $foundYear;
        $this->description = $description;
        $this->website = $website;
        $this->phone = $phone;
        $this->industry = $industry;
        $this->ceo = $ceo;
        $this->isPubliclyTraded = $isPubliclyTraded;
        $this->country = $country;
        $this->founder = $founder;
        $this->totalEmployees = $totalEmployees;
    }
}