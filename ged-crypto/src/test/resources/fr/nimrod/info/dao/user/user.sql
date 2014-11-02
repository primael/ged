drop table if exists utilisateur;
create table utilisateur ( 
	identifiant bigint not null, 
	email varchar(255) unique not null, 
	login varchar(255) unique not null, 
	hash varchar(255) not null,
	salt varchar(255) not null,
	nbrEssai int not null,
	actif int not null,
	numeroTelephone varchar(255),
	primary key (identifiant)
);