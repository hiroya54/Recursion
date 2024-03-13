<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class AlterPostTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "ALTER TABLE Post
                DROP FOREIGN KEY post_ibfk_2,
                DROP COLUMN categoryId;
            "
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "ALTER TABLE Post
                ADD COLUMN categoryId INT,
                ADD FOREIGN KEY (categoryId) REFERENCES Category(categoryId);
            "
        ];
    }
}