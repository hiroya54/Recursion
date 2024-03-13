<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreateCommentLikeTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS CommentLike(
                userId BIGINT,
                commentId INT,
                PRIMARY KEY (userID, commentId),
                FOREIGN KEY (userId) REFERENCES User(userId),
                FOREIGN KEY (commentId) REFERENCES Comment(commentId)
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE IF EXISTS CommentLike"
        ];
    }
}