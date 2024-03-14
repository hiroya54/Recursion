<?php
 
namespace Database\Seeds;

use Faker\Factory;
use Database\AbstractSeeder;

class CarSeeder extends AbstractSeeder {

    protected ?string $tableName = 'Car';

    // TODO: tableColumns配列を割り当ててください。
    protected array $tableColumns = [
        [
            'data_type' => 'string',
            'column_name' => 'make'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'model'
        ],
        [
            'data_type' => 'int',
            'column_name' => 'year'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'color'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'price'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'mileage'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'transmission'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'engine'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'status'
        ]
    ];

    public function createRowData(): array
    {
        // TODO: createRowData()メソッドを実装してください。
        return self::generateRandomRowData();
    }

    private function generateRandomRowData(int $numOfRaw = 10000) : array{
        $faker = Factory::create();

        $data = [];

        for($i = 0; $i<$numOfRaw; $i++){
            $data[] = [
                $faker->company,
                $faker->word,
                intval($faker->year),
                $faker->colorName,
                $faker->randomFloat(2, 1000, 100000),
                $faker->randomFloat(2, 0, 100000),
                $faker->randomElement(['auto', 'manual']),
                $faker->randomElement(['v6', 'v8', 'v12']),
                $faker->randomElement(['new', 'used'])
            ];
        }

        return $data;
    }
}