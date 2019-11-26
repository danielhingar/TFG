/* ROLES */
INSERT INTO `role` (id,nombre) VALUES (1,'ROLE_CLIENT');
INSERT INTO `role` (id,nombre) VALUES (2,'ROLE_ADMIN');
INSERT INTO `role` (id,nombre) VALUES (3,'ROLE_REPORTER');
INSERT INTO `role` (id,nombre) VALUES (4,'ROLE_COMPANY');

/*Companies*/
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about,role) VALUES (1,'daniel','hinojo garcía','danhingar','people3012',1,'672389123','danielhingar3397@gmail.com','showcase','foto1','deportes',1,4);


/*Abouts*/
INSERT INTO `about`  (id,address,instagram,facebook,description,images) VALUES (1,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años',null);


/*Users*/
INSERT INTO `admin` (id,name,surnames,username,password,enabled,phone,email,role) VALUES (1,'Antonio','Pérez','admin','admin1',1,'674839213', 'admin@gmail.com',2);
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,role) VALUES (2,'Ramón','Pérez','rampez','decide123',1,'674839213', 'rampez@gmail.com',1);
INSERT INTO `reporter` (id,name,surnames,username,password,enabled,phone,email,role) VALUES (3,'Luis','García','lusgar','tokio99',1,'674839213', 'lusgar@gmail.com',3);
