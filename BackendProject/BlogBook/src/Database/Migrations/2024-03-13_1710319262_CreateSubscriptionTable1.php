<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class CreateSubscriptionTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "CREATE TABLE Subscription (
                subscriptionId BIGINT PRIMARY KEY AUTO_INCREMENT,
                subscription VARCHAR(255) NOT NULL,
                subscriptionStatus VARCHAR(255) NOT NULL,
                subscriptionCreatedAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                subscriptionEndAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                userId BIGINT,
                FOREIGN KEY (userId) REFERENCES User(userId) ON DELETE CASCADE
            );"
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "DROP TABLE Subscription"
        ];
    }
}