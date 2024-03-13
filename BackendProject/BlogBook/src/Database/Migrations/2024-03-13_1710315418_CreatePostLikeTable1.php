<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreatePostLikeTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS PostLike(
                userId BIGINT ,
                postId INT ,
                PRIMARY KEY (userId, postId),
                FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE,
                FOREIGN KEY (postId) REFERENCES Post(postId) ON DELETE CASCADE
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE PostLike;"
        ];
    }
}