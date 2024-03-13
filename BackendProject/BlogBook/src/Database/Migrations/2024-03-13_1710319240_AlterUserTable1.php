<?php

namespace Database\Migrations;

use Database\SchemaMigration;

class AlterUserTable1 implements SchemaMigration
{
    public function up(): array
    {
        // マイグレーションロジックをここに追加してください
        return [
            "ALTER TABLE User
                DROP COLUMN email_confirmed_at,
                DROP COLUMN subscription,
                DROP COLUMN subscriptionStatus,
                DROP COLUMN subscriptionCreatedAt,
                DROP COLUMN subscriptionEndAt;
            "
        ];
    }

    public function down(): array
    {
        // ロールバックロジックを追加してください
        return [
            "ALTER TABLE User
                ADD COLUMN email_confirmed_at VARCHAR(255),
                ADD COLUMN subscription VARCHAR(255),
                ADD COLUMN subscriptionStatus VARCHAR(255),
                ADD COLUMN subscriptionCreatedAt DateTime,
                ADD COLUMN subscriptionEndAt DateTime;
            "
        ];
    }
}