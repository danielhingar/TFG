/* ROLES------------------------------------------------------------------------------- */
INSERT INTO `role` (id,nombre) VALUES (1,'ROLE_CLIENT');
INSERT INTO `role` (id,nombre) VALUES (2,'ROLE_ADMIN');
INSERT INTO `role` (id,nombre) VALUES (3,'ROLE_REPORTER'); 
INSERT INTO `role` (id,nombre) VALUES (4,'ROLE_COMPANY');

 
/*COMPANIES-----------------------------------------------------------------------------*/
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (985,'alicia','hinojo garcía','danhingar5','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Feuvert','','Automoviles',5);
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (986,'cristina','hinojo garcía','danhingar6','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Telefónica','0ce9d878-62d1-467b-a4b0-99c8336a3262_lorenzo-rui-J7xrBW_oYJc-unsplash.jpg','Telefonía',6);
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (987,'carmen','hinojo garcía','danhingar7','$2a$10$7nlxuDw39HBwdu0AQz0I/.qTOjI702AnqmZqzudGcMylrjBAn3qli',1,'672389123','danielhingar3397@gmail.com','Pull&Bear','d049272b-e40d-46c4-b60e-9f978f4047bb_8df98e45-c6d3-49ed-b489-a8efe4b40d36_edbc18ee-4ba9-4ec8-9cdc-32fa951d4011_photo-1512436991641-6745cdb1723f.jpg','Moda',7);
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (988,'lucia','hinojo garcía','danhingar8','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Cristalbox','911d4c63-8131-4a57-a731-2e70b6e4d8c7_photo-1477333183135-292dd5b3910f.jpg','Automoviles',8);

INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (989,'patricio','hinojo garcía','danhingar4','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Carglass','911d4c63-8131-4a57-a731-2e70b6e4d8c7_photo-1477333183135-292dd5b3910f.jpg','Automoviles',4);
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (990,'juana','hinojo garcía','danhingar3','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Jazztel','0ce9d878-62d1-467b-a4b0-99c8336a3262_lorenzo-rui-J7xrBW_oYJc-unsplash.jpg','Telefonía',3);
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (991,'daniel','hinojo garcía','danhingar','$2a$10$7nlxuDw39HBwdu0AQz0I/.qTOjI702AnqmZqzudGcMylrjBAn3qli',1,'672389123','danielhingar3397@gmail.com','Zara','d049272b-e40d-46c4-b60e-9f978f4047bb_8df98e45-c6d3-49ed-b489-a8efe4b40d36_edbc18ee-4ba9-4ec8-9cdc-32fa951d4011_photo-1512436991641-6745cdb1723f.jpg','Moda',1);
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (992,'ramon','hinojo garcía','danhingar2','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','Autodoc','911d4c63-8131-4a57-a731-2e70b6e4d8c7_photo-1477333183135-292dd5b3910f.jpg','Automoviles',2);



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

INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (1,991,'NIKE AIR-MAX','Zapatillas Nike Air-Max último modelo con las nuevas plantillas adaptativas a tu pie','a5c29446-6b41-45ff-805d-5d9f2dc9b896_air-max-200-zapatillas-M5t3W6.jpg',100.70,'2019-11-23','SHOES','37,38,39,40,41',null,null,'550 gramos',null,null,null,'NIKE',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (2,992,'ZAPATILLA ULTRABOOST','Zapatillas para correr con suela Ultraboost para adaptarse mejor a las imperfecciones del suelo','66f68295-6cf5-4081-abdb-7357e10f48c7_descarga(3).jpg',120.70,'2019-11-23','SHOES','37,38,39,40,41',null,null,'550 gramos',null,null,null,'ADIDAS',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (3,991,'LÁMPARA DE TECHO METAL','Lámpara de aluminio perfecta para el salón, no incluye bombilla.','e371aff3-baea-454d-a85e-db1fad1a9aaa_descarga.jpg',50.50,'2019-11-23','DECORATION',null,null,null,'300 gramos','20 pulgadas',null,null,'NIKE',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (4,992,'CHAQUETA ACOLCHADA','Chaqueta de lana nueva temporada invierno',null,69.7,'2019-11-23','CLOTHES','S,M,L,XL',null,null,'550 gramos',null,null,null,'SPRINGFIELD',20);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (5,991,'CAMISETA MANGA CORTA','Camiseta de algodón de manga corta con logo de mickey mouse, cuello redondo.','59ee03f8-dd06-4dd0-ab83-d807bd4a3747_descarga(1).jpg',30.25,'2019-11-23','CLOTHES','S,M',null,null,null,null,null,null,'PULL&BEARD',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (6,992,'IPHONE 11','IPHONE 11 móvil de última genereción, 6.1" Liquid Retina HD, Chip A13 Bionic, iOS',null,1000.50,'2019-11-23','TECNOLOGY',null,'18 cm','7 cm','750 gramos','6.1',null,'32GB,64GB,128GB','IPHONE',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (7,991,'PORTATIL HP','Portatil HP con procesador de última generación i9, 16GB de RAM, 15.6" Full HD.',null,601.70,'2019-11-23','TECNOLOGY',null,'20 cm','40 cm','550 gramos','15.6',null,'250GB,500GB,1TB','HP',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,memory,brand,offert) VALUES (8,992,'SAL DEL HIMALAYA','Sal 100% natural procedente del himalaya en forma de escamas',null,30.21,'2019-11-23','FOOD',null,null,null,'250 gramos',null,null,null,'CARREFOUR',null);
 
/*RELATION BASKET-PRODUCTS*/
INSERT INTO `item_basket` (id,quantity,basket_id,product_id,facture_id,size,capacity) VALUES (1,1,1,1,null,'38','null'); 
INSERT INTO `item_basket` (id,quantity,basket_id,product_id,facture_id,size,capacity) VALUES (2,2,1,2,null,'39','null');
INSERT INTO `item_basket` (id,quantity,basket_id,product_id,facture_id,size,capacity) VALUES (3,1,1,3,null,'null','null');
INSERT INTO `item_basket` (id,quantity,basket_id,product_id,facture_id,size,capacity) VALUES (4,3,2,4,null,'L','null');
INSERT INTO `item_basket` (id,quantity,basket_id,product_id,facture_id,size,capacity) VALUES (5,1,2,5,null,'M','null');
INSERT INTO `item_basket` (id,quantity,basket_id,product_id,facture_id,size,capacity) VALUES (6,8,3,7,null,'null','500GB');
INSERT INTO `item_basket` (id,quantity,basket_id,product_id,facture_id,size,capacity) VALUES (7,3,3,6,null,'null','16GB');


/*Comment--------------------------------------------------------------------------------*/
INSERT INTO `comment` (id,title,description,valoration,client_id,product_id) VALUES (1,'Perfecto','El producto me llego en perfecta condiciones y estoy muy contento con el',3.0,994,1);

/*Usuario_role*/
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (985,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (986,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (987,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (988,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (989,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (990,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (991,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (992,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (993,2);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (994,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (995,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (996,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (997,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (998,3);