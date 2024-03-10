CREATE TABLE IF NOT EXISTS PostLike(
    userID INT ,
    postId INT ,
    PRIMARY KEY (userID, postId),
    FOREIGN KEY (userID) REFERENCES User(userID),
    FOREIGN KEY (postId) REFERENCES Post(postId)
);