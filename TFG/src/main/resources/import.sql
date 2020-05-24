/* ROLES------------------------------------------------------------------------------- */
INSERT INTO `role` (id,nombre) VALUES (1,'ROLE_CLIENT');
INSERT INTO `role` (id,nombre) VALUES (2,'ROLE_ADMIN');
INSERT INTO `role` (id,nombre) VALUES (3,'ROLE_REPORTER'); 
INSERT INTO `role` (id,nombre) VALUES (4,'ROLE_COMPANY');

 
/*COMPANIES-----------------------------------------------------------------------------*/
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id,create_date) VALUES (985,'alicia','hinojo garcía','danhingar5','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Feuvert','','Automoviles',5,'2019-11-23');
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id,create_date) VALUES (986,'cristina','hinojo garcía','danhingar6','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','MediaMarkt','https://images.unsplash.com/photo-1556741533-411cf82e4e2d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80','Telefonía',6,'2020-04-11');
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id,create_date) VALUES (987,'carmen','hinojo garcía','danhingar7','$2a$10$7nlxuDw39HBwdu0AQz0I/.qTOjI702AnqmZqzudGcMylrjBAn3qli',1,'672389123','danielhingar3397@gmail.com','Pull&Bear','https://images.unsplash.com/photo-1512436991641-6745cdb1723f?ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80','Moda',7,'2020-04-10');
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id,create_date) VALUES (988,'lucia','hinojo garcía','danhingar8','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Douglas','https://images.unsplash.com/photo-1543857261-f71238eb4188?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=753&q=80','Perfumería',8,'2019-11-23');


INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id,create_date) VALUES (990,'juana','hinojo garcía','danhingar3','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Jazztel','https://images.unsplash.com/photo-1513611771808-7e8ab7f1dec6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60','Telefonía',3,'2020-04-12');
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id,create_date) VALUES (991,'daniel','hinojo garcía','danhingar','$2a$10$7nlxuDw39HBwdu0AQz0I/.qTOjI702AnqmZqzudGcMylrjBAn3qli',1,'672389123','danielhingar3397@gmail.com','Zara','https://images.unsplash.com/photo-1525562723836-dca67a71d5f1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=667&q=80','Moda',1,'2020-04-14');
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id,create_date) VALUES (992,'ramon','hinojo garcía','danhingar2','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Autodoc','https://images.unsplash.com/photo-1569858574460-2fae7746115c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60','Automoviles',2,'2020-04-13');



/*ABOUTS--------------------------------------------------------------------------------*/
INSERT INTO `about`  (id,address,instagram,facebook,twitter,youtube,description,compromise,polity) VALUES (1,'calle Perú','https://www.instagram.com/danielhingar/','https://facebook.com','http://www.twitter.com','https://www.youtube.com','Esta empresa comenzó en 1990, somos una empresa textil cuyo objetivo principal es llevar las tendencias en moda a nuestros clientes y que nuestros productos sean de la mejor calidad','Estamos comprometidos con el medio ambiente y por ello no usamos plásticos en nuestros envíos a domicilio ya que es sustituido por cajas de cartón.','Nuestra política de empresa apuesta por la igualdad y respeto a todas las personas sin importar de su país de origen, creencias religiosas, etc...');
INSERT INTO `about`  (id,address,instagram,facebook,description) VALUES (2,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años');
INSERT INTO `about`  (id,address,instagram,facebook,description) VALUES (3,'calle Tomás','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años');
INSERT INTO `about`  (id,address,instagram,facebook,description) VALUES (4,'calle Santa Ana','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años');

INSERT INTO `about`  (id,address,instagram,facebook,description) VALUES (5,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años');
INSERT INTO `about`  (id,address,instagram,facebook,description) VALUES (6,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años');
INSERT INTO `about`  (id,address,instagram,facebook,description) VALUES (7,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años');
INSERT INTO `about`  (id,address,instagram,facebook,description) VALUES (8,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años');

/*ADMINS--------------------------------------------------------------------------------*/
INSERT INTO `admin` (id,name,surnames,username,password,enabled,phone,email) VALUES (993,'Antonio','Pérez','admin','$2a$10$6XWAGJZ1dQ85TUIfEpUJ6u2nyNSltYAWuktcZYh6gGw71tEZLVpEO',1,'674839213', 'admin@gmail.com');
 
/*BASKET---------------------------------------------------------------------------------*/

INSERT INTO `basket` (id) VALUES (1);
INSERT INTO `basket` (id) VALUES (2);
INSERT INTO `basket` (id) VALUES (3);
INSERT INTO `basket` (id) VALUES (4);

/*CLIENT--------------------------------------------------------------------------------*/
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id,address,number,code_postal,province,locality) VALUES (994,'Ramón','Pérez','rampez','$2a$10$innlJ999oNhZGPVfFh2tMexaVkx/xfkPON17x9FPuhWQrzvckoQcO',1,'674839213', 'rampez@gmail.com',1,'Calle traiña','20','43892','Cádiz','Rota');
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id,address,number,code_postal,province,locality) VALUES (995,'Pablo','Cabeza','pabcab','$2a$10$HvR2hVz1ScjkpZS8ZlUEf.0BgNSq/Ir0hkmeYuJz7bqZK6xNSQOXu',1,'67483973', 'pabcab@gmail.com',2,'Calle Lope de Vega','5','45000','Madrid','Madrid');
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id,address,number,code_postal,province,locality) VALUES (996,'Marta','García','margar','$2a$10$1K5FCAUDCniomaZkSRLmm.Yl2B06UEpBMMr73C6hMofeqO9Iai66m',1,'674839908', 'margar@gmail.com',3,'Calle Lima','20','43892','Sevilla','Dos Hermanas');
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id,address,number,code_postal,province,locality) VALUES (997,'Clara','Hinojo','clahin','$2a$10$jR6DPrQLyrpQcocQwEIzIuEyGWTvsSddl83Ij3Na5o0expqh4pw5.',1,'674839672', 'clahin@gmail.com',4,'Calle Cervantes','20','43892','Granada','Granada');

/*REPORTER------------------------------------------------------------------------------*/
INSERT INTO `reporter` (id,name,surnames,username,password,enabled,phone,email) VALUES (998,'Luis','García','lusgar','$2a$10$GQRY7aLGz5AEmSG1rUm7COXcd7r7OgFJSacOawSAX.kpjREtqzmjS',1,'674839213', 'lusgar@gmail.com');

/*SHIPPING------------------------------------------------------------------------------*/

INSERT INTO `shipping` (id,date_max,date_min,title,observation,price) VALUES (1,5,2,'Envío Express',null,10.0);
INSERT INTO `shipping` (id,date_max,date_min,title,observation,price) VALUES (2,7,4,'Envío Normal',null,5.0);
INSERT INTO `shipping` (id,date_max,date_min,title,observation,price) VALUES (3,14,7,'Envío Gratis',null,0.0);

/*CONFIGURATIONS-------------------------------------------------------------------------*/

INSERT INTO `configuration` (id,phone,email,facebook,instagram,twitter,address,fail_system,name_system,banner) VALUES (1,'673456721','showcase@gmail.com','https://es-es.facebook.com/','https://www.instagram.com/?hl=es','https://twitter.com/login?lang=es','Sevilla, Spain',false,'Showcase',null);

/*CLAIMS---------------------------------------------------------------------------------*/


/*PRODUCTS-------------------------------------------------------------------------------*/  

INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (1,991,'MOCASIN','Mocasín negro. Detalle de antifaz en el empeine. Suela goma negra maxi volumen.','https://static.zara.net/photos///2020/V/1/2/p/5637/002/040/2/w/325/5637002040_1_1_1.jpg?ts=1578654981046',40.70,'2019-11-23','MODA','37,38,39,40,41',null,null,'550 gramos',null,null,null,'ZARA',null,'DISPONIBLE',10);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (2,987,'ZAPATILLA ULTRABOOST','Zapatillas para correr con suela Ultraboost para adaptarse mejor a las imperfecciones del suelo','66f68295-6cf5-4081-abdb-7357e10f48c7_descarga(3).jpg',120.70,'2019-11-23','MODA','37,38,39,40,41',null,null,'550 gramos',null,null,null,'ADIDAS',null,'DISPONIBLE',10);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (4,987,'CHAQUETA ACOLCHADA','Chaqueta de lana nueva temporada invierno','https://static.zara.net/photos///2020/V/0/2/p/0706/422/401/2/w/810/0706422401_6_1_1.jpg?ts=1578570195853',69.7,'2019-11-23','MODA','S,M,L,XL',null,null,'550 gramos',null,null,null,'SPRINGFIELD',20,'DISPONIBLE',10);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (5,991,'CAMISETA MANGA CORTA','Camiseta de algodón de manga corta con logo de mickey mouse, cuello redondo.','https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSy205PGKk7wd8ggwipeqMSfqf6bsbxktDiumWyMcDGy94mqANZ&usqp=CAU',30.25,'2019-11-23','MODA','S,M',null,null,null,null,null,null,'PULL&BEARD',null,'DISPONIBLE',1);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (6,986,'IPHONE 11','IPHONE 11 móvil de última genereción, 6.1" Liquid Retina HD, Chip A13 Bionic, iOS','https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSa6LTNneMtxKkRbn6ua6fWVMjuUJrVs_tm05KpYRbQfskZdZ88&usqp=CAU',1000.50,'2019-11-23','TELEFONÍA',null,'18 cm','7 cm','750 gramos','6.1',null,'32GB,64GB,128GB','IPHONE',null,'DISPONIBLE',1);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (7,986,'PORTATIL HP','Portatil HP con procesador de última generación i9, 16GB de RAM, 15.6" Full HD.',null,601.70,'2019-11-23','INFORMÁTICA Y ELECTRÓNICA',null,'20 cm','40 cm','550 gramos','15.6',null,'250GB,500GB,1TB','HP',null,'SINSTOCK',0);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (8,992,'SAL DEL HIMALAYA','Sal 100% natural procedente del himalaya en forma de escamas',null,30.21,'2019-11-23','ALIMENTACIÓN',null,null,null,'250 gramos',null,null,null,'CARREFOUR',null,'DISPONIBLE',1);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (9,992,'ZAPATOS GUCCI','Zapatillas Gucci con logo tigre',null,100.70,'2019-11-23','MODA','37,38,39,40,41',null,null,'550 gramos',null,null,null,'GUCCI',null,'ELIMINADO',0);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (10,991,'CADENA MALLA','Cadena hecha en estructura tubular de malla metálica. Cierre con mosquetón.','https://static.zara.net/photos///2020/V/0/2/p/8435/348/808/2/w/259/8435348808_1_1_1.jpg?ts=1577980960271',100.70,'2019-11-23','JOYERÍA','37,38,39,40,41',null,null,'550 gramos',null,null,null,'ADIDAS',null,'DISPONIBLE',2);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (11,991,'CAMISETA NIKE','Camiseta nike de manga corta ultima temporada','https://mosaic04.ztat.net/vgs/media/packshot/catalog-sm/NI/12/2O/0I/UA/11/NI122O0IU-A11@2.jpg',100.70,'2019-11-23','MODA','37,38,39,40,41',null,null,'550 gramos',null,null,null,'ADIDAS',null,'DISPONIBLE',2);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (12,991,'CHAQUETA DE CUERO','Chaqueta de lana, nueva temporada invierno','https://static.zara.net/photos///2020/V/0/2/p/3548/403/800/2/w/810/3548403800_6_1_1.jpg?ts=1579262894330',69.7,'2019-11-23','MODA','S,M,L,XL',null,null,'550 gramos',null,null,null,'SPRINGFIELD',20,'DISPONIBLE',2);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert,status,stock) VALUES (13,990,'IPHONE 11 PLUS','IPHONE 11 móvil de última genereción, 6.1" Liquid Retina HD, Chip A13 Bionic, iOS','https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRAVfXRGfCwfYR6EFZFIM89dqjjXR62AACm8gPVXggfT5WTEnvl&usqp=CAU',1000.50,'2019-11-23','TELEFONÍA',null,'18 cm','7 cm','750 gramos','6.1',null,'32GB,64GB,128GB','IPHONE',null,'DISPONIBLE',1);
/*conversation*/
INSERT INTO `conversation` (id,issue,create_date,client_id,company_id) VALUES (1,'Envío','2020-01-23',996,991);

/*Messages*/
INSERT INTO `message` (id,text,create_date,answer,conversation_id) VALUES (1,'Hola, quería saber cuanto tiempo suele tardar en llegar.','2020-01-23 18:30:00',0,1);
INSERT INTO `message` (id,text,create_date,answer,conversation_id) VALUES (2,'Hola Marta, pues el tiempo de entrega depende el método de envío que selecciones','2020-01-23 18:40:00',1,1);
INSERT INTO `message` (id,text,create_date,answer,conversation_id) VALUES (3,'Muchas gracias','2020-01-25 10:32:00',0,1);
INSERT INTO `message` (id,text,create_date,answer,conversation_id) VALUES (4,'De nada, para cualquier otra duda no dude en preguntarnos','2020-01-25 11:31:00',1,1);

 
 
/*Comment--------------------------------------------------------------------------------*/
INSERT INTO `comment` (id,title,description,valoration,client_id,product_id, create_date) VALUES (1,'Perfecto','El producto me llego en perfecta condiciones y estoy muy contento con el',3.0,994,1,'2019-11-23');
INSERT INTO `comment` (id,title,description,valoration,client_id,product_id,create_date) VALUES (2,'Muy bien','El producto es de muy buena calidad',2.0,995,1,'2020-2-21');

/*Usuario_role*/
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (985,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (986,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (987,4);  
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (988,4);

INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (990,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (991,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (992,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (993,2);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (994,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (995,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (996,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (997,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (998,3);