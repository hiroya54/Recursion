<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreateTaxonomyTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS Taxonomy(
                taxonomyId INT PRIMARY KEY AUTO_INCREMENT,
                taxonomyName VARCHAR(255),
                description TEXT
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE Taxonomy;"
        ];
    }
}