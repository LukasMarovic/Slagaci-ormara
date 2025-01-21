CREATE TABLE Korisnik (
	sifKorisnika INTEGER,
	imeKorisnika VARCHAR(50),
	email VARCHAR(50),
	lozinka VARCHAR(30),
	PRIMARY KEY(sifKorisnika)
);

CREATE TABLE Ormar (
	sifOrmara INTEGER,
	nazivOrmara VARCHAR(50),
	sifKorisnika INTEGER,
	PRIMARY KEY(sifOrmara),
	FOREIGN KEY(sifKorisnika) REFERENCES korisnik(sifKorisnika)
);

CREATE TABLE Registrirani_korisnik (
	sifKorisnika INTEGER,
	geolokacija VARCHAR(50),
	PRIMARY KEY(sifKorisnika),
	FOREIGN KEY(sifKorisnika) REFERENCES korisnik(sifKorisnika)
);

CREATE TABLE Oglasivac (
	sifKorisnika INTEGER,
	logo VARCHAR(30),
	PRIMARY KEY(sifKorisnika),
	FOREIGN KEY(sifKorisnika) REFERENCES korisnik(sifKorisnika)
);

CREATE TABLE Artikl(
	sifArtikla INTEGER,
	nazivArtikla VARCHAR(50),
	slikaArtikla VARCHAR(200),
	opcaKategorija VARCHAR(50),
	kategorijaGodDoba VARCHAR(50),
	kategorijaLezernosti VARCHAR(50),
	glavnaBoja VARCHAR(20),
	sporednaBoja VARCHAR(20),
	stanjeArtikla VARCHAR(50),
	cijena NUMERIC(5,2),
	sifKorisnika INTEGER, 
	PRIMARY KEY (sifArtikla),
	FOREIGN KEY (sifKorisnika) REFERENCES korisnik(sifKorisnika)
);

CREATE TABLE Obuca (
	sifArtikla INTEGER,
	kategorijaOtvorenosti VARCHAR(50),
	PRIMARY KEY (sifArtikla),
	FOREIGN KEY (sifArtikla) REFERENCES artikl(sifArtikla)
);

CREATE TABLE Odjeca (
	sifArtikla INTEGER,
	PRIMARY KEY (sifArtikla),
	FOREIGN KEY (sifArtikla) REFERENCES artikl(sifArtikla)
);


CREATE TABLE Lokacija (
	brojLokacije INTEGER,
	sifOrmara INTEGER,
	vrstaLokacije VARCHAR(20),
	PRIMARY KEY(brojLokacije, sifOrmara),
	FOREIGN KEY(sifOrmara) REFERENCES ormar(sifOrmara)
);

CREATE TABLE NalaziSeU (
	sifArtikla INTEGER,
	brojLokacije INTEGER,
	sifOrmara INTEGER,
	PRIMARY KEY(sifArtikla, brojLokacije, sifOrmara),
	FOREIGN KEY(brojLokacije, sifOrmara) REFERENCES lokacija(brojLokacije, sifOrmara)
--	FOREIGN KEY(sifOrmara) REFERENCES ormar(sifOrmara)
);


--KORISNIK
--korisnik
INSERT INTO public.korisnik (sifkorisnika, imekorisnika, email, lozinka)
VALUES (100, 'Nikola Tesla', 'nikolatesla@gmail.com', 'password123');

INSERT INTO public.korisnik (sifkorisnika, imekorisnika, email, lozinka)
VALUES (200, 'Halid Bešlić', 'halidbeslic@gmail.com', 'passwordhalidov123');

INSERT INTO public.korisnik (sifkorisnika, imekorisnika, email, lozinka)
VALUES (300, 'Josipa Lisac', 'josipica@gmail.com', 'passwordjosipin123');
--oglasivaci
INSERT INTO public.korisnik (sifkorisnika, imekorisnika, email, lozinka)
VALUES (010, 'Pero Peric', 'peroperic1@gmail.com', 'passwordoglasivac123');

INSERT INTO public.korisnik (sifkorisnika, imekorisnika, email, lozinka)
VALUES (020, 'Anica Kabanica', 'anicakabanica@gmail.com', 'passwordoglasivac2123');



--ORMAR
INSERT INTO public.ormar (siformara, nazivormara, sifkorisnika)
VALUES (001, 'Prvi Nikolin ormar', 100);

INSERT INTO public.ormar (siformara, nazivormara, sifkorisnika)
VALUES (002, 'Prvi Halidov ormar', 200);

INSERT INTO public.ormar (siformara, nazivormara, sifkorisnika)
VALUES (003, 'Prvi Josipin ormar', 300);



--ARTIKLI
--korisnik nikola tesla
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 101, 'Zimska jakna', NULL, 'Jackets', 'Winter', 'Casual', 'Blue', 'White', 'new', 250.00, 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 102, 'Džemper', NULL, 'Sweatshirts & hoodies', 'Winter', 'Casual', 'Yellow', 'White', 'new', 30.75, 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 103, 'Traperice', NULL, 'Jeans', 'Fall', 'Casual', 'Blue', 'Blue', 'new', 50.25, 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 104, 'Košulja', NULL, 'Blouses', 'Spring', 'Business', 'Beige', 'Beige', 'new', 45.00, 100 );

--korisnik halid beslic
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 401, 'Šešir', NULL, 'Hats', 'Fall', 'Business', 'Grey', 'Grey', 'Used', 15.50, 200 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 402, 'Čizme', NULL, 'Boots', 'Fall', 'Casual', 'Green', 'Green', 'Used', 27.00, 200 );

--korisnik josipa lisac
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 501, 'Haljina', NULL, 'Dresses', 'Summer', 'Formal', 'Green', 'Green', 'Used', 28.00, 300 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 502, 'Štikle', NULL, 'Heels', 'Fall', 'Casual', 'Black', 'Black', 'Used', 15.00, 300 );


--oglasivac pero peric
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 201, 'Jakna', NULL, 'Jackets', 'Fall', 'Casual', 'Grey', 'Black', 'new', 60.00, 010 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 202, 'Majica', NULL, 'Shirts', 'Winter', 'Business', 'Beige', 'Black', 'new', 26.50, 010 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 203, 'Traperice', NULL, 'Jeans', 'Spring', 'Casual', 'Blue', 'Blue', 'new', 47.00, 010 );

--oglasivac anica kabanica
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 301, 'Kabanica', NULL, 'Jackets', 'Fall', 'Casual', 'yellow', 'yellow', 'new', 13.50, 020 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, cijena, sifkorisnika)
VALUES ( 302, 'Jaknica', NULL, 'Jackets', 'Fall', 'Business', 'Black', 'Black', 'Used', 25.50, 020 );







--ODJEĆA
--korisnik nikola tesla
INSERT INTO public.odjeca(sifartikla)
VALUES (101);

INSERT INTO public.odjeca(sifartikla)
VALUES (102);

INSERT INTO public.odjeca(sifartikla)
VALUES (103);

INSERT INTO public.odjeca(sifartikla)
VALUES (104);

--korisnik halid beslic
INSERT INTO public.odjeca(sifartikla)
VALUES (401);

--korisnik josipa lisac
INSERT INTO public.odjeca(sifartikla)
VALUES (501);

--oglasivac pero peric
INSERT INTO public.odjeca(sifartikla)
VALUES (201);

INSERT INTO public.odjeca(sifartikla)
VALUES (202);

INSERT INTO public.odjeca(sifartikla)
VALUES (203);

--oglasivac anica kabanica
INSERT INTO public.odjeca(sifartikla)
VALUES (301);

INSERT INTO public.odjeca(sifartikla)
VALUES (302);



--OBUĆA
--korisnik halid beslic
INSERT INTO public.obuca(sifartikla, kategorijaotvorenosti)
VALUES (401, 'Rain');

--korisnik josipa lisac
INSERT INTO public.obuca(sifartikla, kategorijaotvorenosti)
VALUES (502, 'Closed');



--LOKACIJA
--korisnik Nikola Tesla
INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (1, 001, 'Hangers');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (2, 001, 'Hangers');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (3, 001, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (4, 001, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (5, 001, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (6, 001, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (7, 001, 'Drawers');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (8, 001, 'Drawers');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (9, 001, 'Drawers');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (10, 001, 'Drawers');

--korisnik halid beslic
INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (1, 002, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (2, 002, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (3, 002, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (4, 002, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (5, 002, 'Drawers');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (6, 002, 'Hangers');

--korisnik josipa lisac
INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (1, 003, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (2, 003, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (3, 003, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (4, 003, 'Shelves');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (5, 003, 'Drawers');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (6, 003, 'Hangers');



--NALAZIZEU
--nikoa tesla
INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (101, 1, 001);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (102, 7, 001);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (103, 8, 001);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (104, 2, 001);

--halid beslic
INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (401, 5, 002);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (401, 1, 002);

--josipa lisac
INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (501, 6, 003);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (502, 1, 003);



--REGISTRIRANI KORISNIK
INSERT INTO public.registrirani_korisnik (sifkorisnika, geolokacija) VALUES (100, 'Zagreb');
INSERT INTO public.registrirani_korisnik (sifkorisnika, geolokacija) VALUES (200, 'Sarajevo');
INSERT INTO public.registrirani_korisnik (sifkorisnika, geolokacija) VALUES (300, 'Zagreb');



--OGLAŠIVAČ
INSERT INTO public.oglasivac (sifkorisnika, logo) VALUES (010, NULL);
INSERT INTO public.oglasivac (sifkorisnika, logo) VALUES (020, NULL);
