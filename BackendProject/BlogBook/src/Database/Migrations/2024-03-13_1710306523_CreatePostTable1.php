<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreatePostTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        // ON DELETE CASCADE: 親テーブルの行が削除されたとき、それに関連する子テーブルの行も削除される
        return [
            "CREATE TABLE posts (
                id INT PRIMARY KEY AUTO_INCREMENT,
                title VARCHAR(255) NOT NULL,
                content TEXT NOT NULL,
                created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                user_id BIGINT,
                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
            )"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE posts"
        ];
    }
}