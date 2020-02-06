create schema deisy;
create schema authorization postgres;
create table deisy.persona
(
   id              decimal(10),
   primer_nombre   varchar(255),
   segundo_nombre  varchar(255),
   primer_apellido varchar(255),
   segundo_apellido varchar(255),
   fecha_nacimiento date,
   genero           varchar(255)

);


