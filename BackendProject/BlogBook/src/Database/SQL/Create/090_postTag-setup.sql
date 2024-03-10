CREATE TABLE IF NOT EXISTS PostTag(
    postId INT,
    tagId INT,
    PRIMARY KEY (postId, tagId),
    FOREIGN KEY (postId) REFERENCES Post(postId),
    FOREIGN KEY (tagId) REFERENCES Tag(tagId)
);