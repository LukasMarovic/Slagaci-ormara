CREATE TABLE IF NOT EXISTS Users
(
    userID   IDENTITY PRIMARY KEY,
    username VARCHAR(200),
    email    VARCHAR(200),
    password VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Closet
(
    closetID   IDENTITY PRIMARY KEY,
    closetName VARCHAR(200),
    userID     INTEGER,
    FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE IF NOT EXISTS RegisteredUser
(
    userID      INTEGER PRIMARY KEY,
    geolocation VARCHAR(200),
    FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE IF NOT EXISTS Seller
(
    userID INTEGER PRIMARY KEY,
    logo   VARCHAR(200),
    FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE IF NOT EXISTS Article
(
    articleID      IDENTITY PRIMARY KEY,
    articleName    VARCHAR(250),
    articlePicture VARCHAR(250),
    category       VARCHAR(50),
    seasonality    VARCHAR(50),
    formality      VARCHAR(50),
    mainColor      VARCHAR(20),
    secondaryColor VARCHAR(20),
    availability   VARCHAR(50),
    price          NUMERIC(7, 2),
    userID         INTEGER,
    FOREIGN KEY (userID) REFERENCES Users (userID)
);

CREATE TABLE IF NOT EXISTS Footwear
(
    articleID INTEGER PRIMARY KEY,
    openness  VARCHAR(50),
    FOREIGN KEY (articleID) REFERENCES Article (articleID)
);

CREATE TABLE IF NOT EXISTS Clothes
(
    articleID INTEGER PRIMARY KEY,
    FOREIGN KEY (articleID) REFERENCES Article (articleID)
);

CREATE TABLE IF NOT EXISTS Location
(
    locationID   INTEGER,
    closetID     INTEGER,
    locationType VARCHAR(20),
    FOREIGN KEY (closetID) REFERENCES Closet (closetID),
    PRIMARY KEY (locationID, closetID)
);

CREATE TABLE IF NOT EXISTS LocatedAt
(
    articleID   INTEGER,
    locationID  INTEGER,
    closetID    INTEGER,
    FOREIGN KEY (articleID) REFERENCES Article (articleID),
    FOREIGN KEY (locationID, closetID) REFERENCES Location (locationID, closetID),
    PRIMARY KEY (articleID, locationID, closetID)
);

CREATE TABLE IF NOT EXISTS SPRING_SESSION (
                                              PRIMARY_ID CHAR(36) NOT NULL,
                                              SESSION_ID CHAR(36) NOT NULL,
                                              CREATION_TIME BIGINT NOT NULL,
                                              LAST_ACCESS_TIME BIGINT NOT NULL,
                                              MAX_INACTIVE_INTERVAL INT NOT NULL,
                                              EXPIRY_TIME BIGINT NOT NULL,
                                              PRINCIPAL_NAME VARCHAR(100),
                                              CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE IF NOT EXISTS SPRING_SESSION_ATTRIBUTES (
                                                         SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                                         ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                                         ATTRIBUTE_BYTES LONGVARBINARY NOT NULL,
                                                         CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                                         CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);