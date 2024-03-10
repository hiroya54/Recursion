ALTER TABLE Post
    ADD CategoryId INT,
    ADD FOREIGN KEY (CategoryId) REFERENCES Category(CategoryId)
;