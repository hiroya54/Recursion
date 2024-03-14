<?php

namespace Database\Seeds;

use Faker\Factory;
use Database\AbstractSeeder;

class ComputerPartsSeeder extends AbstractSeeder {
    protected ?string $tableName = 'computer_parts';
    protected array $tableColumns = [
        [
            'data_type' => 'string',
            'column_name' => 'name'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'type'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'brand'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'model_number'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'release_date'
        ],
        [
            'data_type' => 'string',
            'column_name' => 'description'
        ],
        [
            'data_type' => 'int',
            'column_name' => 'performance_score'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'market_price'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'rsm'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'power_consumptionw'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'lengthm'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'widthm'
        ],
        [
            'data_type' => 'float',
            'column_name' => 'heightm'
        ],
        [
            'data_type' => 'int',
            'column_name' => 'lifespan'
        ]
    ];

    public function createRowData(): array {
        return self::generateRandomRowData();
    }

    private static function generateRandomRowData(int $numOfRow = 10000) : array{
        $faker = Factory::create();
        $rowData = [];

        for ($i = 0; $i < $numOfRow; $i++) {
            $rowData[] = [
                $faker->name,
                $faker->randomElement(['CPU', 'GPU', 'SSD', 'RAM']),
                $faker->company,
                $faker->bothify('????-####'),
                $faker->dateTimeThisCentury->format('Y-m-d'),
                $faker->sentence,
                $faker->numberBetween(70, 100),
                $faker->randomFloat(2, 50, 1000),
                $faker->randomFloat(2, 0.01, 0.1),
                $faker->randomFloat(2, 1, 500),
                $faker->randomFloat(3, 0.01, 1),
                $faker->randomFloat(3, 0.01, 1),
                $faker->randomFloat(3, 0.01, 1),
                $faker->numberBetween(3, 10)
            ];
        }

        return $rowData;
    }
}
