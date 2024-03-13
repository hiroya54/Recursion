<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreateTaxonomyTermTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS TaxonomyTerm(
                taxonomyTermId INT PRIMARY KEY AUTO_INCREMENT,
                taxonomyTermName VARCHAR(255),
                taxonomyTypeId INT,
                description TEXT,
                parentTaxonomyTerm INT,
                FOREIGN KEY (taxonomyTypeId) REFERENCES Taxonomy(taxonomyId)
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE TaxonomyTerm;"
        ];
    }
}