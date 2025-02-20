CREATE TABLE Users (
                       userID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- Automatski primarni ključ
                       username VARCHAR(50),
                       email VARCHAR(50),
                       password VARCHAR(30)
);

CREATE TABLE Closet (
                        closetID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- Automatski primarni ključ
                        closetName VARCHAR(50),
                        userID INTEGER,
                        FOREIGN KEY(userID) REFERENCES Users(userID)
);

CREATE TABLE RegisteredUser (
                                userID INTEGER PRIMARY KEY, -- Veza na Users.userID (bez automatskog generiranja)
                                geolocation VARCHAR(50),
                                FOREIGN KEY(userID) REFERENCES Users(userID)
);

CREATE TABLE Seller (
                        userID INTEGER PRIMARY KEY, -- Veza na Users.userID (bez automatskog generiranja)
                        logo VARCHAR(30),
                        FOREIGN KEY(userID) REFERENCES Users(userID)
);

CREATE TABLE Article (
                         articleID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- Automatski primarni ključ
                         articleName VARCHAR(50),
                         articlePicture VARCHAR(200),
                         category VARCHAR(50),
                         seasonality VARCHAR(50),
                         formality VARCHAR(50),
                         mainColor VARCHAR(20),
                         secondaryColor VARCHAR(20),
                         availability VARCHAR(50),
                         price NUMERIC(5,2),
                         userID INTEGER,
                         FOREIGN KEY(userID) REFERENCES Users(userID)
);

CREATE TABLE Footwear (
                          articleID INTEGER PRIMARY KEY, -- Veza na Article.articleID
                          openness VARCHAR(50),
                          FOREIGN KEY(articleID) REFERENCES Article(articleID)
);

CREATE TABLE Clothes (
                         articleID INTEGER PRIMARY KEY, -- Veza na Article.articleID
                         FOREIGN KEY(articleID) REFERENCES Article(articleID)
);

CREATE TABLE Location (
                          locationID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- Automatski primarni ključ
                          closetID INTEGER,
                          locationType VARCHAR(20),
                          FOREIGN KEY(closetID) REFERENCES Closet(closetID)
);

CREATE TABLE LocatedAt (
                           locatedAtID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, -- Jedinstveni identifikator za svaki redak
                           articleID INTEGER,
                           locationID INTEGER,
                           closetID INTEGER,
                           FOREIGN KEY(articleID) REFERENCES Article(articleID),
                           FOREIGN KEY(locationID) REFERENCES Location(locationID),
                           FOREIGN KEY(closetID) REFERENCES Closet(closetID)
);


-- Users
INSERT INTO Users (username, email, password)
VALUES ('Nikola Tesla', 'nikolatesla@gmail.com', 'password123');

INSERT INTO Users (username, email, password)
VALUES ('Halid Bešlić', 'halidbeslic@gmail.com', 'passwordhalidov123');

INSERT INTO Users (username, email, password)
VALUES ('Josipa Lisac', 'josipica@gmail.com', 'passwordjosipin123');

-- Selleri
INSERT INTO Users (username, email, password)
VALUES ('Pero Peric', 'peroperic1@gmail.com', 'passwordseller123');

INSERT INTO Users (username, email, password)
VALUES ('Anica Kabanica', 'anicakabanica@gmail.com', 'passwordseller2123');

-- Ormar
INSERT INTO Closet (closetName, userID)
VALUES ('Prvi Nikolin closet', 1);

INSERT INTO Closet (closetName, userID)
VALUES ('Prvi Halidov closet', 2);

INSERT INTO Closet (closetName, userID)
VALUES ('Prvi Josipin closet', 3);

-- Artikli
-- Nikola Tesla
INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Zimska jakna', NULL, 'Jackets', 'Winter', 'Casual', 'Blue', 'White', 'new', 250.00, 1);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Džemper', NULL, 'Sweatshirts & hoodies', 'Winter', 'Casual', 'Yellow', 'White', 'new', 30.75, 1);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Traperice', NULL, 'Jeans', 'Fall', 'Casual', 'Blue', 'Blue', 'new', 50.25, 1);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Košulja', NULL, 'Blouses', 'Spring', 'Business', 'Beige', 'Beige', 'new', 45.00, 1);

-- Halid Bešlić
INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Šešir', NULL, 'Hats', 'Fall', 'Business', 'Grey', 'Grey', 'Used', 15.50, 2);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Čizme', NULL, 'Boots', 'Fall', 'Casual', 'Green', 'Green', 'Used', 27.00, 2);

-- Josipa Lisac
INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Haljina', NULL, 'Dresses', 'Summer', 'Formal', 'Green', 'Green', 'Used', 28.00, 3);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Štikle', NULL, 'Heels', 'Fall', 'Casual', 'Black', 'Black', 'Used', 15.00, 3);

-- Seller Pero Perić
INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Jakna', NULL, 'Jackets', 'Fall', 'Casual', 'Grey', 'Black', 'new', 60.00, 4);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Majica', NULL, 'Shirts', 'Winter', 'Business', 'Beige', 'Black', 'new', 26.50, 4);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Traperice', NULL, 'Jeans', 'Spring', 'Casual', 'Blue', 'Blue', 'new', 47.00, 4);

-- Seller Anica Kabanica
INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Kabanica', NULL, 'Jackets', 'Fall', 'Casual', 'Yellow', 'Yellow', 'new', 13.50, 5);

INSERT INTO Article (articleName, articlePicture, category, seasonality, formality, mainColor, secondaryColor, availability, price, userID)
VALUES ('Jaknica', NULL, 'Jackets', 'Fall', 'Business', 'Black', 'Black', 'Used', 25.50, 5);

-- Clothes
INSERT INTO Clothes (articleID)
VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

-- Footwear
INSERT INTO Footwear (articleID, openness)
VALUES (6, 'Rain'), (7, 'Closed');

-- Location
INSERT INTO Location (closetID, locationType)
VALUES (1, 'Hangers'), (1, 'Shelves'), (1, 'Drawers'), (2, 'Shelves'), (2, 'Hangers');

-- LocatedAt
INSERT INTO LocatedAt (articleID, locationID, closetID)
VALUES (1, 1, 1), (2, 2, 1), (3, 3, 1);

-- Registered Users
INSERT INTO RegisteredUser (userID, geolocation)
VALUES (1, 'Zagreb'), (2, 'Sarajevo'), (3, 'Zagreb');

-- Sellers
INSERT INTO Seller (userID, logo)
VALUES (4, NULL), (5, NULL);
