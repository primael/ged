create table utilisateur (
	identifiant bigint not null, 
	email varchar(255) not null, 
	login varchar(255) not null, 
	primary key (identifiant)
);