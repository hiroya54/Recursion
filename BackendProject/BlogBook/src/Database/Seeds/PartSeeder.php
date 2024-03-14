<?php
 
namespace Database\Seeds;

use Faker\Factory;
use Database\AbstractSeeder;

class PartSeeder extends AbstractSeeder {

    protected ?string $tableName = 'Part';

    protected array $tableColumns = [
        [
            'data_type' => 'int',
            'column_name' => 'carId'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'description'
        ],
        [
            'data_type' => 'int',
            'column_name' => 'price'
        ],
        [
            'data_type' => 'int',
            'column_name' => 'quantityStock'
        ]
    ];

    public function createRowData(): array
    {
        return self::generateRandomRowData();
    }

    private function generateRandomRowData(int $numOfRaw = 10000) : array{
        $faker = Factory::create();

        $data = [];

        for($i = 0; $i<$numOfRaw; $i++){
            $data[] = [
                $faker->numberBetween(1, $numOfRaw),
                $faker->text(100),
                $faker->numberBetween(100, 100000),
                $faker->numberBetween(1, 1000)
            ];
        }

        return $data;
    }
}