<?php

namespace Helpers;

use Faker\Factory;
use Users\Employee;
use Companies\RestaurantChain;
use Companies\RestaurantLocation;

class RandomGenerator{

    public static function employee(): Employee{
        $faker = Factory::create();

        return new Employee(
            $faker->randomNumber(),
            $faker->firstName(),
            $faker->lastName(),
            $faker->email(),
            $faker->password(),
            $faker->phoneNumber(),
            $faker->address(),
            $faker->dateTimeThisCentury(),
            $faker->dateTimeBetween("-1 year", "+1 year"),
            $faker->randomElement(["admin", "user", "editor"]),
            $faker->jobTitle(),
            $faker->randomFloat(2000, 20000, 100000),
            $faker->dateTimeThisCentury(),
        );
    }

    public static function employees(int $minNumOfEmployees, int $maxNumOfEmployees): array{
        $faker = Factory::create();

        $employees = [];
        $numOfEmployees = $faker->numberBetween($minNumOfEmployees, $maxNumOfEmployees);

        for($i = 0; $i < $numOfEmployees; $i++){
            $employees[] = self::employee();
        }

        return $employees;
    }

    public static function restaurantLocation(int $minNumOfEmployees, int $maxNumOfEmployees): RestaurantLocation{
        $faker = Factory::create();

        return new RestaurantLocation(
            $faker->company(),
            $faker->streetAddress(),
            $faker->city(),
            $faker->state(),
            $faker->postcode(),
            self::employees($minNumOfEmployees, $maxNumOfEmployees),
            $faker->boolean(),
            $faker->boolean()
        );
    }

    public static function restaurantLocations(
        int $numOfLocations,
        int $minNumOfEmployees, int $maxNumOfEmployees): array{
        $faker = Factory::create();

        $restaurantLocations = [];

        for($i = 0; $i < $numOfLocations; $i++){
            $restaurantLocations[] = self::restaurantLocation($minNumOfEmployees, $maxNumOfEmployees);
        }

        return $restaurantLocations;
    }

    public static function restaurantChain(
        int $minNumOfRestaurantLocations, int $maxNumOfRestaurantLocations,
        int $minNumOfEmployees, int $maxNumOfEmployees): RestaurantChain{
        $faker = Factory::create();

        $numOfLocations = $faker->numberBetween($minNumOfRestaurantLocations, $maxNumOfRestaurantLocations);

        return new RestaurantChain(
            $faker->company(),
            $faker->year(),
            $faker->catchPhrase(),
            $faker->url(),
            $faker->phoneNumber(),
            $faker->randomElement(["restaurant", "fast food", "cafe"]),
            $faker->name(),
            $faker->boolean(),
            $faker->country(),
            $faker->name(),
            $faker->numberBetween(100, 10000),
            $faker->randomNumber(),
            self::restaurantLocations($numOfLocations, $minNumOfEmployees, $maxNumOfEmployees),
            $faker->randomElement(["American", "Italian", "Mexican", "Chinese", "Japanese"]),
            $numOfLocations,
            $faker->company()
        );
    }

    public static function restaurantChains(
        $minNumOfRestaurantChains, $maxNumOfRestaurantChains,
        $minNumOfRestaurantLocations, $maxNumOfRestaurantLocations,
        $minNumOfEmployees, $maxNumOfEmployees): array{
        $faker = Factory::create();

        $restaurantChains = [];
        $numOfChains = $faker->numberBetween($minNumOfRestaurantChains, $maxNumOfRestaurantChains);

        for($i = 0; $i < $numOfChains; $i++){
            $restaurantChains[] = self::restaurantChain(
                $minNumOfRestaurantLocations, $maxNumOfRestaurantLocations,
                $minNumOfEmployees, $maxNumOfEmployees);
        }

        return $restaurantChains;
    }
}