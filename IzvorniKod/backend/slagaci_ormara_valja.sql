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
INSERT INTO public.korisnik (sifkorisnika, imekorisnika, email, lozinka) VALUES (100, 'Nikola Tesla', 'nikolatesla@gmail.com', 'password123');

INSERT INTO public.korisnik (sifkorisnika, imekorisnika, email, lozinka) VALUES (010, 'Oglašivač 1', 'oglasivac1@gmail.com', 'passwordoglasivac123');

--ORMAR
INSERT INTO public.ormar (siformara, nazivormara, sifkorisnika)  VALUES (001, 'Prvi ormar', 100);



--ARTIKLI
INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika ) VALUES ( 101, 'Zimska jakna', NULL, 'Jakna', 'zima', 'ležerno', 'plava', 'bijela', 'novo', 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika ) VALUES ( 102, 'Džemper', NULL, 'Puloveri i džemperi', 'zima', 'ležerno', 'žuta', 'bijela', 'novo', 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika ) VALUES ( 103, 'Traperice', NULL, 'Hlače', 'jesen', 'ležerno', 'tamno plava', 'plava', 'novo', 100 );

INSERT INTO public.artikl ( sifartikla, nazivartikla, slikaartikla, opcakategorija, kategorijagoddoba, kategorijalezernosti, glavnaboja, sporednaboja, stanjeartikla, sifkorisnika ) VALUES ( 104, 'Košulja', NULL, 'Košulje i bluze', 'proljeće', 'poslovno', 'bež', 'krem', 'novo', 100 );



--ODJEĆA
INSERT INTO public.odjeca(sifartikla) VALUES (101);

INSERT INTO public.odjeca(sifartikla) VALUES (102);

INSERT INTO public.odjeca(sifartikla) VALUES (103);

INSERT INTO public.odjeca(sifartikla) VALUES (104);



--LOKACIJA
INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (1, 001, 'šipka');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (2, 001, 'polica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (3, 001, 'ladica');

INSERT INTO public.lokacija (brojlokacije, siformara, vrstalokacije)
VALUES (10, 001, 'šipka');

--NALAZIZEU
INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (101, 1, 001);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (102, 1, 001);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (103, 1, 001);

INSERT INTO public.nalaziseu (sifArtikla, brojLokacije, sifOrmara)
VALUES (104, 10, 001);


--REGISTRIRANI KORISNIK
INSERT INTO public.registrirani_korisnik (sifkorisnika, geolokacija) VALUES (100, 'Zagreb');



--OGLAŠIVAČ
INSERT INTO public.oglasivac (sifkorisnika, logo) VALUES (010, NULL);




