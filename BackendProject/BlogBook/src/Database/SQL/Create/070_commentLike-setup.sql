CREATE TABLE IF NOT EXISTS CommentLike(
    userID INT,
    commentId INT,
    PRIMARY KEY (userID, commentId),
    FOREIGN KEY (userID) REFERENCES User(userID),
    FOREIGN KEY (commentId) REFERENCES Comment(commentId)
);