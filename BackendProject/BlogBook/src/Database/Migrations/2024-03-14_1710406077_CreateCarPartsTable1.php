<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreateCarPartsTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS Part (
                id INT PRIMARY KEY AUTO_INCREMENT,
                carId INT,
                description TEXT,
                price FLOAT,
                quantityStock INT,
                FOREIGN KEY (carId) REFERENCES Car(id)
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE Part"
        ];
    }
}