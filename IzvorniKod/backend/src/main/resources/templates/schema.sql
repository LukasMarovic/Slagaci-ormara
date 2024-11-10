CREATE TABLE IF NOT EXISTS ORMAR (
    sif_ormara int auto_increment,
    naziv_ormara varchar(30),
    sif_korisnika bigint
);

CREATE TABLE IF NOT EXISTS ARTIKL (
    id_artikla int auto_increment,
    naziv_artikla varchar(30),
    /*opis_artikla blob,
    slika_artikla varchar(10),
    opca_kategorija varchar(30),
    kategorija_lezernosti varchar(30),
    glavna_boja varchar(30),
    sporedna_boja varchar(30),
    stanje_artikla varchar(30),
    sif_oglasivaca bigint*/
);