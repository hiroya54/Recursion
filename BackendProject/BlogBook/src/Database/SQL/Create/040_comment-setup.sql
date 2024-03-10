CREATE TABLE IF NOT EXISTS Comment(
    commentId INT PRIMARY KEY AUTO_INCREMENT,
    commentText VARCHAR(255),
    created_at DateTime,
    updated_at DateTime,
    userId INT,
    postId INT,
    FOREIGN KEY (userId) REFERENCES User(userID),
    FOREIGN KEY (postId) REFERENCES Post(postId)
);