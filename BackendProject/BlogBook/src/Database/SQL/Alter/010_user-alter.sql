ALTER TABLE User 
    ADD subscription VARCHAR(255),
    ADD subscriptionStatus VARCHAR(255),
    ADD subscriptionCreatedAt DateTime,
    ADD subscriptionEndAt DateTime;