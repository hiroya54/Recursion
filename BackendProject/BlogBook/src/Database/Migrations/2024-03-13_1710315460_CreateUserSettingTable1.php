<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreateUserSettingTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE IF NOT EXISTS UserSetting(
                enrtyId INT PRIMARY KEY AUTO_INCREMENT,
                userId BIGINT,
                metaKey VARCHAR(255),
                metaValue TEXT,
                FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE UserSetting;"
        ];
    }
}