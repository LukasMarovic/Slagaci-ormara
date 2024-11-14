--KORISNIK
INSERT INTO KORISNIK (sif_korisnika, ime_korisnika, email, lozinka, geolokacija) VALUES (default, 'Nikola Tesla', 'nikolatesla@gmail.com', 'password123', 'Smiljan');
INSERT INTO KORISNIK (sif_korisnika, ime_korisnika, email, lozinka, geolokacija) VALUES (default, 'Oglašivač 1', 'oglasivac1@gmail.com', 'passwordoglasivac123', 'Tuvalu');

--ORMAR
INSERT INTO ORMAR (sif_ormara, naziv_ormara, sif_korisnika)  VALUES (default, 'Prvi ormar', 1);

--ARTIKLI
INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Winter jacket', NULL, 'Jackets', 'Winter', 'Casual', 'Blue', 'Black', 'New', 1 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Sweater', NULL, 'Jumpers and sweaters', 'Winter', 'Casual', 'Yellow', 'White', 'New', 1 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Jeans', NULL, 'Pants', 'Fall', 'Casual', 'Dark blue', 'Blue', 'Worn', 1 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Button-down shirt', NULL, 'Shirts and blouses', 'Spring', 'Business', 'Beige', 'Cream', 'New', 1 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Straw hat', NULL, 'Hats', 'Summer', 'Casual', 'Yellow', 'Red', 'Worn', 1 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'T-shirt', NULL, 'T-shirts', 'Summer', 'Casual', 'Red', 'White', 'New', 2 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Hoodie', NULL, 'Jumpers and sweaters', 'Winter', 'Casual', 'Green', 'White', 'New', 2 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Running shoes', NULL, 'Sneakers', 'Summer', 'Casual', 'Black', 'White', 'New', 2 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Blazer', NULL, 'Jackets', 'Fall', 'Business', 'Black', 'Gray', 'New', 2 );

INSERT INTO artikl ( sif_artikla, naziv_artikla, slika_artikla, opca_kategorija, kategorija_goddoba, kategorija_lezernosti, glavna_boja, sporedna_boja, stanje_artikla, sif_korisnika ) VALUES ( default, 'Sweatpants', NULL, 'Pants', 'Spring', 'Casual', 'Gray', 'Dark gray', 'New', 2 );