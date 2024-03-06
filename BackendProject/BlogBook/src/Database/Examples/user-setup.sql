CREATE TABLE IF NOT EXISTS User(
    userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    email_confirmed_at VARCHAR(255),
    created_at DateTime,
    updated_at DateTime
);