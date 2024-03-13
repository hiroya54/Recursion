<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreatePostTagTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS PostTag(
                postId INT,
                tagId INT,
                PRIMARY KEY (postId, tagId),
                FOREIGN KEY (postId) REFERENCES Post(postId) ON DELETE CASCADE,
                FOREIGN KEY (tagId) REFERENCES Tag(tagId) ON DELETE CASCADE
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE PostTag;"
        ];
    }
}