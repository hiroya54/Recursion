CREATE TABLE IF NOT EXISTS CacheBookSearch(
    `key` varchar(255) PRIMARY KEY,
    `value` text NOT NULL,
    created_at DateTime,
    updated_at DateTime
);