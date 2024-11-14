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
	slikaArtikla BYTEA,
	opcaKategorija VARCHAR(50),
	kategorijaGodDoba VARCHAR(50),
	kategorijaLezernosti VARCHAR(50),
	glavnaBoja VARCHAR(20),
	sporednaBoja VARCHAR(20),
	stanjeArtikla VARCHAR(50),
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
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 101, 'Zimska jakna', NULL, 'Jakna', 'zima', 'ležerno', 'plava', 'bijela', 'novo', 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 102, 'Džemper', NULL, 'Puloveri i džemperi', 'zima', 'ležerno', 'žuta', 'bijela', 'novo', 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 103, 'Traperice', NULL, 'Hlače', 'jesen', 'ležerno', 'plava', 'plava', 'novo', 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 104, 'Košulja', NULL, 'Košulje i bluze', 'proljeće', 'poslovno', 'bež', 'krem', 'novo', 100 );

--korisnik halid beslic
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 401, 'Šešir', NULL, 'Šeširi i kape', 'jesen', 'poslovno', 'siva', 'siva', 'rabljeno', 200 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 402, 'Čizme', NULL, 'Čizme', 'jesen', 'ležerno', 'zelena', 'zelena', 'rabljeno', 200 );

--korisnik josipa lisac
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 501, 'Haljina', NULL, 'Haljine', 'ljeto', 'svećano', 'zelena', 'zelena', 'rabljeno', 300 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 502, 'Štikle', NULL, 'Štikle', 'jesen', 'ležerno', 'crna', 'crna', 'rabljeno', 300 );


--oglasivac pero peric
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 201, 'Jakna', NULL, 'Jakna', 'jesen', 'ležerno', 'siva', 'crna', 'novo', 010 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 202, 'Majica', NULL, 'Majice', 'zima', 'poslovno', 'bež', 'crna', 'novo', 010 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 203, 'Traperice', NULL, 'Hlače', 'proljeće', 'ležerno', 'plava', 'tamno plava', 'novo', 010 );

--oglasivac anica kabanica
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 301, 'Kabanica', NULL, 'Jakna', 'jesen', 'ležerno', 'žuta', 'žuta', 'novo', 020 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika )
VALUES ( 302, 'Jakna', 'Jakna', 'Majice', 'jesen', 'poslovno', 'crna', 'crna', 'rabljeno', 020 );







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
VALUES (401, 'za kišu');

--korisnik josipa lisac
INSERT INTO public.obuca(sifartikla, kategorijaotvorenosti)
VALUES (502, 'zatvoreno');



--LOKACIJA
--korisnik Nikola Tesla
INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (1, 001, 'šipka');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (2, 001, 'šipka');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (3, 001, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (4, 001, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (5, 001, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (6, 001, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (7, 001, 'ladica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (8, 001, 'ladica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (9, 001, 'ladica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (10, 001, 'ladica');

--korisnik halid beslic
INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (1, 002, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (2, 002, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (3, 002, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (4, 002, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (5, 002, 'ladica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (6, 002, 'šipka');

--korisnik josipa lisac
INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (1, 003, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (2, 003, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (3, 003, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (4, 003, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (5, 003, 'ladica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (6, 003, 'šipka');



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
