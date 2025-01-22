CREATE TABLE IF NOT EXISTS ORMAR (
    sif_ormara bigint generated by default as identity primary key,
    naziv_ormara varchar(30),
    sif_korisnika bigint
);

CREATE TABLE IF NOT EXISTS LOKACIJA (
    broj_lokacije bigint,
    sif_ormara bigint,
    vrsta_lokacije varchar(20),
    foreign key (sif_ormara) references ormar(sif_ormara),
    primary key (broj_lokacije, sif_ormara)
);

CREATE TABLE IF NOT EXISTS ARTIKL (
    sif_artikla bigint generated by default as identity primary key,
    naziv_artikla varchar(30),
    slika_artikla blob(1M),
    opca_kategorija varchar(30),
    kategorija_goddoba varchar(30),
    kategorija_lezernosti varchar(30),
    glavna_boja varchar(30),
    sporedna_boja varchar(30),
    stanje_artikla varchar(30),
    sif_korisnika bigint
);

CREATE TABLE IF NOT EXISTS KORISNIK (
    sif_korisnika bigint generated by default as identity primary key,
    ime_korisnika varchar(30),
    email varchar(50),
    lozinka varchar(30),
    geolokacija varchar(50)
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