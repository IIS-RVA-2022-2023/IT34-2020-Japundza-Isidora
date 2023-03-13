insert into bioskop(id, naziv, adresa)
values (
	nextval('BIOSKOP_SEQ'), 'Arena Cineplex', 'Bulevar Mihajla Pupina 3'
);

insert into bioskop(id, naziv, adresa)
values (
	nextval('BIOSKOP_SEQ'), 'Cineplex Promenada', 'Bulevar oslobodjenja 119'
);

insert into bioskop(id, naziv, adresa)
values (
	nextval('BIOSKOP_SEQ'), 'CineStar Novi Sad 4DX', 'Sentandrejski put 11'
);

insert into bioskop(id, naziv, adresa)
values (
	nextval('BIOSKOP_SEQ'), 'Kulturni centar', 'Katolicka porta 5'
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Oluja', 7, 156, 'Ratni, drama' 
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Krid 3', 9, 116, 'Drama' 
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Avatar - put vode', 8, 192, 'Akcija, avantura' 
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Titanik', 9, 194, 'Drama, ljubavni' 
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Vini Pu: Krv i med', 5, 84, 'Horor' 
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Vatreno srce', 10, 92, 'Animirani' 
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Antmen i Osa: Kvantumanija', 7, 122, 'Komedija, akcija, avantura' 
);

insert into film(id, naziv, recenzija, trajanje, zanr)
values (
	nextval('FILM_SEQ'), 'Covek zvani Oto', 9, 126, 'Komedija, drama' 
);

insert into sala(id, kapacitet, broj_redova, bioskop)
values (
	nextval('SALA_SEQ'), 252, 13, 1
);

insert into sala(id, kapacitet, broj_redova, bioskop)
values (
	nextval('SALA_SEQ'), 80, 10, 2
);

insert into sala(id, kapacitet, broj_redova, bioskop)
values (
	nextval('SALA_SEQ'), 60, 8, 3
);

insert into sala(id, kapacitet, broj_redova, bioskop)
values (
	nextval('SALA_SEQ'), 104, 13, 4
);

insert into rezervacija(id, broj_osoba, cena_karte, datum, placeno, film, sala)
values (
	nextval('REZERVACIJA_SEQ'), 2, 850, to_date('23.03.2023.', 'dd.mm.yyyy'), true, 5, 2
);

insert into rezervacija(id, broj_osoba, cena_karte, datum, placeno, film, sala)
values (
	nextval('REZERVACIJA_SEQ'), 4, 1750, to_date('14.04.2023.', 'dd.mm.yyyy'), false, 7, 4
);

insert into rezervacija(id, broj_osoba, cena_karte, datum, placeno, film, sala)
values (
	nextval('REZERVACIJA_SEQ'), 1, 550, to_date('28.03.2023.', 'dd.mm.yyyy'), true, 3, 1
);

insert into rezervacija(id, broj_osoba, cena_karte, datum, placeno, film, sala)
values (
	nextval('REZERVACIJA_SEQ'), 5, 2200, to_date('07.04.2023.', 'dd.mm.yyyy'), false, 1, 3
);