<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class DeleteCategoryTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "DROP TABLE IF EXISTS Category;"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "CREATE TABLE IF NOT EXISTS Category(
                categoryId INT PRIMARY KEY AUTO_INCREMENT,
                categoryName VARCHAR(255)
            );"
        ];
    }
}