<?php

abstract class Person{
    protected String $name;
    protected int $age;
    protected String $address;

    public function __construct(String $name, int $age, String $address){
        $this->name = $name;
        $this->age = $age;
        $this->address = $address;
    }
}