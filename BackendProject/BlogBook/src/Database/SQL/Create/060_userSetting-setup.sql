CREATE TABLE IF NOT EXISTS UserSetting(
    enrtyId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT,
    metaKey VARCHAR(255),
    metaValue TEXT,
    FOREIGN KEY (userId) REFERENCES User(userID)
);