CREATE TABLE IF NOT EXISTS Post(
    postId INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    content TEXT,
    created_at DateTime,
    updated_at DateTime,
    userId INT,
    FOREIGN KEY (userID) REFERENCES User(userID)
);