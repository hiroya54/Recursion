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

    public function toHTML()  :string{

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

    public function toString() :string{
        $locations = "";
        foreach($this->restaurantLocations as $restaurantLocation){
            $locations .= "{".$restaurantLocation->toString()."}\n";
        }
        return sprintf("Name: %s\nFound Year: %d\nDescription: %s\nWebsite: %s\nPhone: %s\nIndustry: %s\nCEO: %s\nIs Publicly Traded: %s\nCountry: %s\nFounder: %s\nTotal Employees: %d\nChain ID: %d\nRestaurant Locations: {%s}\nCuisine Type: %s\nNumber of Locations: %d\nParent Company: %s\n",
        $this->name, $this->foundYear, $this->description, $this->website, $this->phone, $this->industry, $this->ceo, $this->isPubliclyTraded, $this->country, $this->founder, $this->totalEmployees, $this->chainId, $locations, $this->cuisineType, $this->numberOfLocations, $this->parentCompany);
    }

    public function toMarkdown() :string{
        $locations = "";
        foreach($this->restaurantLocations as $restaurantLocation){
            $locations .= $restaurantLocation->toMarkdown()."\n";
        }
        return "## Restaurant Chain: {$this->name}
        - Found Year: {$this->foundYear}
        - Description: {$this->description}
        - Website: {$this->website}
        - Phone: {$this->phone}
        - Industry: {$this->industry}
        - CEO: {$this->ceo}
        - Is Publicly Traded: {$this->isPubliclyTraded}
        - Country: {$this->country}
        - Founder: {$this->founder}
        - Total Employees: {$this->totalEmployees}
        - Chain ID: {$this->chainId}
        - Restaurant Locations: 
        $locations
        - Cuisine Type: {$this->cuisineType}
        - Number of Locations: {$this->numberOfLocations}
        - Parent Company: {$this->parentCompany}";
    }

    public function toArray(): array{
        $locations = [];
        foreach($this->restaurantLocations as $restaurantLocation){
            $locations[] = $restaurantLocation->toArray();
        }
        return [
            "name" => $this->name,
            "foundYear" => $this->foundYear,
            "description" => $this->description,
            "website" => $this->website,
            "phone" => $this->phone,
            "industry" => $this->industry,
            "ceo" => $this->ceo,
            "isPubliclyTraded" => $this->isPubliclyTraded,
            "country" => $this->country,
            "founder" => $this->founder,
            "totalEmployees" => $this->totalEmployees,
            "chainId" => $this->chainId,
            "restaurantLocations" => $locations,
            "cuisineType" => $this->cuisineType,
            "numberOfLocations" => $this->numberOfLocations,
            "parentCompany" => $this->parentCompany
        ];
    }
}