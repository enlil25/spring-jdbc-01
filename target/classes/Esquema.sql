drop table if exists persona;
create table persona(
id_persona bigint auto_increment,
nombre varchar(40) NOT NULL,
ape_paterno varchar(50) NOT NULL,
ape_materno varchar(50),
email varchar(50) unique NOT NULL,
PRIMARY KEY(id_persona)
);
