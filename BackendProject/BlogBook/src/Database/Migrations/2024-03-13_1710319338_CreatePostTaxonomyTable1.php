<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreatePostTaxonomyTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS PostTaxonomy(
                postTaxonomyId INT PRIMARY KEY AUTO_INCREMENT,
                postId INT,
                taxonomyId INT,
                FOREIGN KEY(postId) REFERENCES Post(postId),
                FOREIGN KEY(taxonomyId) REFERENCES TaxonomyTerm(taxonomyTermId)
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE PostTaxonomy;"
        ];
    }
}