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
    locationID   IDENTITY PRIMARY KEY,
    closetID     INTEGER,
    locationType VARCHAR(20),
    FOREIGN KEY (closetID) REFERENCES Closet (closetID)
);

CREATE TABLE IF NOT EXISTS LocatedAt
(
    locatedAtID IDENTITY PRIMARY KEY,
    articleID   INTEGER,
    locationID  INTEGER,
    closetID    INTEGER,
    FOREIGN KEY (articleID) REFERENCES Article (articleID),
    FOREIGN KEY (locationID) REFERENCES Location (locationID),
    FOREIGN KEY (closetID) REFERENCES Closet (closetID)
);

