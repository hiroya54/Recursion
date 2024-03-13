<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreateCommentTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS Comment(
                commentId INT PRIMARY KEY AUTO_INCREMENT,
                commentText VARCHAR(255),
                created_at DateTime,
                updated_at DateTime,
                userId BIGINT,
                postId INT,
                FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE,
                FOREIGN KEY (postId) REFERENCES Post(postId) ON DELETE CASCADE
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE Comment;"
        ];
    }
}