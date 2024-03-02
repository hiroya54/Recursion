<?php

namespace Companies;

use Interfaces\FileConvertible;

class RestaurantChain extends Company implements FileConvertible{
    private int $chainId;
    private array $restaurantLocations;
    private string $cuisineType;
    private int $numberOfLocations;
    private string $parentCompany;

    public function __construct(string $name, int $foundYear, string $description, string $website, 
    string $phone, string $industry, string $ceo, bool $isPubliclyTraded, 
    string $country, string $founder, int $totalEmployees, int $chainId, array $restaurantLocations,
    string $cuisineType, int $numberOfLocations, string $parentCompany){
        parent::__construct($name, $foundYear, $description, $website, $phone, $industry, $ceo, 
        $isPubliclyTraded, $country, $founder, $totalEmployees);
        $this->chainId = $chainId;
        $this->restaurantLocations = $restaurantLocations;
        $this->cuisineType = $cuisineType;
        $this->numberOfLocations = $numberOfLocations;
        $this->parentCompany = $parentCompany;
    }

    public function toHTML(){

        $html = sprintf("
        <h1 class='text-center mt-4'>%s</h1>
        <div class='border col-10 px-0 pb-2'>
            <div class='bg-light border col-12'>
                <p class='m-0 py-2'>Restaurant Chain Infomation</p>
            </div>
        ", $this->name);

        foreach($this->restaurantLocations as $restaurantLocation){
            $html .= $restaurantLocation->toHTML();
        }
        $html .= "</div>";

        return $html;
    }
}