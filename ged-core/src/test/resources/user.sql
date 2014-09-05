drop table if exists utilisateur;
create table utilisateur ( 
	identifiant bigint not null, 
	email varchar(255) not null, 
	login varchar(255) not null, 
	hash varchar(255) not null,
	salt varchar(255) not null,
	primary key (identifiant)
);