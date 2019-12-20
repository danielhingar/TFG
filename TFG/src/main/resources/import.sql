/* ROLES------------------------------------------------------------------------------- */
INSERT INTO `role` (id,nombre) VALUES (1,'ROLE_CLIENT');
INSERT INTO `role` (id,nombre) VALUES (2,'ROLE_ADMIN');
INSERT INTO `role` (id,nombre) VALUES (3,'ROLE_REPORTER'); 
INSERT INTO `role` (id,nombre) VALUES (4,'ROLE_COMPANY');


/*COMPANIES-----------------------------------------------------------------------------*/
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (991,'daniel','hinojo garcía','danhingar','$2a$10$7nlxuDw39HBwdu0AQz0I/.qTOjI702AnqmZqzudGcMylrjBAn3qli',1,'672389123','danielhingar3397@gmail.com','showcase','foto1','deportes',1);
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about_id) VALUES (992,'ramon','hinojo garcía','danhingar2','$2a$10$bLWWf4R6N61Q3XX9iF88t..pUG0PzUza4XZy2jdzFPOvejNk8e9rK',1,'672389123','danielhingar3397@gmail.com','showcase','foto1','deportes',2);
 
/*ABOUTS--------------------------------------------------------------------------------*/
INSERT INTO `about`  (id,address,instagram,facebook,description,images) VALUES (1,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años',null);
INSERT INTO `about`  (id,address,instagram,facebook,description,images) VALUES (2,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años',null);

/*ADMINS--------------------------------------------------------------------------------*/
INSERT INTO `admin` (id,name,surnames,username,password,enabled,phone,email) VALUES (993,'Antonio','Pérez','admin','$2a$10$6XWAGJZ1dQ85TUIfEpUJ6u2nyNSltYAWuktcZYh6gGw71tEZLVpEO',1,'674839213', 'admin@gmail.com');
 
/*BASKET---------------------------------------------------------------------------------*/

INSERT INTO `basket` (id) VALUES (1);
INSERT INTO `basket` (id) VALUES (2);
INSERT INTO `basket` (id) VALUES (3);
INSERT INTO `basket` (id) VALUES (4);

/*CLIENT--------------------------------------------------------------------------------*/
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id) VALUES (994,'Ramón','Pérez','rampez','$2a$10$innlJ999oNhZGPVfFh2tMexaVkx/xfkPON17x9FPuhWQrzvckoQcO',1,'674839213', 'rampez@gmail.com',1);
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id) VALUES (995,'Pablo','Cabeza','pabcab','$2a$10$HvR2hVz1ScjkpZS8ZlUEf.0BgNSq/Ir0hkmeYuJz7bqZK6xNSQOXu',1,'67483973', 'pabcab@gmail.com',2);
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id) VALUES (996,'Marta','García','margar','$2a$10$1K5FCAUDCniomaZkSRLmm.Yl2B06UEpBMMr73C6hMofeqO9Iai66m',1,'674839908', 'margar@gmail.com',3);
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket_id) VALUES (997,'Clara','Hinojo','clahin','$2a$10$jR6DPrQLyrpQcocQwEIzIuEyGWTvsSddl83Ij3Na5o0expqh4pw5.',1,'674839672', 'clahin@gmail.com',4);

/*REPORTER------------------------------------------------------------------------------*/
INSERT INTO `reporter` (id,name,surnames,username,password,enabled,phone,email) VALUES (998,'Luis','García','lusgar','$2a$10$GQRY7aLGz5AEmSG1rUm7COXcd7r7OgFJSacOawSAX.kpjREtqzmjS',1,'674839213', 'lusgar@gmail.com');

/*SHIPPING------------------------------------------------------------------------------*/

INSERT INTO `shipping` (id,date_max,date_min,title,observation,price) VALUES (1,2,5,'Envío Express',null,10.0);
INSERT INTO `shipping` (id,date_max,date_min,title,observation,price) VALUES (2,4,7,'Envío Normal',null,5.0);
INSERT INTO `shipping` (id,date_max,date_min,title,observation,price) VALUES (3,5,14,'Envío Gratis',null,0.0);

/*CONFIGURATIONS-------------------------------------------------------------------------*/

INSERT INTO `configuration` (id,phone,email) VALUES (1,'673456721','showcase@gmail.com');

/*CLAIMS---------------------------------------------------------------------------------*/

INSERT INTO `claim` (id,reporter_id,title,description,create_date,attachment,facture_id,status,answer) VALUES (1,998,'PEDIDO NO HA LLEGADO','SE HA PASADO EL TIEMPO DE ENVÍO','2019-01-01',null,2,'REJECTED','No cumple las bases legales');

/*PRODUCTS-------------------------------------------------------------------------------*/

INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (1,991,'NIKE AIR-MAX','zapatillas con suela ancha','photo1',100.7,'2019-11-23','SHOES','37,38,39,40,41',null,null,'550 gramos',null,null,'NIKE',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (2,991,'ZAPATILLA ULTRABOOST','zapatillas perfecta para correr','photo1',120.7,'2019-11-23','SHOES','37,38,39,40,41',null,null,'550 gramos',null,null,'ADIDAS',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (3,991,'LÁMPARA DE TECHO METAL','lámpara de aluminio perfecta para el salón','photo1',50.7,'2019-11-23','DECORATION',null,null,null,'300 gramos','20 pulgadas',null,'NIKE',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (4,991,'CHAQUETA ACOLCHADA','chaqueta de lana perfecta para el invierno','photo1',69.7,'2019-11-23','CLOTHES','S,M,L,XL',null,null,'550 gramos',null,null,'SPRINGFIELD',20);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (5,991,'CAMISETA MANGA CORTA','camiseta con logo de disney','photo1',30.7,'2019-11-23','CLOTHES','S,M',null,null,null,null,null,'PULL&BEARD',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (6,991,'IPHONE 11','nuevo modelo de iphone','photo1',1000.7,'2019-11-23','TECNOLOGY',null,'18 cm','7 cm','750 gramos','10','36,64','IPHONE',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (7,991,'PORTATIL HP','portatil hp de última generación','photo1',600.7,'2019-11-23','TECNOLOGY',null,'20 cm','40 cm','550 gramos','17',null,'HP',null);
INSERT INTO `product` (id,company_id,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (8,991,'SAL DEL HIMALAYA','sal del himalaya en escamas','photo1',6.7,'2019-11-23','FOOD',null,null,null,'250 gramos',null,null,'CARREFOUR',null);
 
/*RELATION BASKET-PRODUCTS*/
INSERT INTO `item_basket` (quantity,basket_id,product_id) VALUES (1,1,1);
INSERT INTO `item_basket` (quantity,basket_id,product_id) VALUES (2,1,2);
INSERT INTO `item_basket` (quantity,basket_id,product_id) VALUES (1,1,3);
INSERT INTO `item_basket` (quantity,basket_id,product_id) VALUES (3,2,4);
INSERT INTO `item_basket` (quantity,basket_id,product_id) VALUES (1,2,5);
INSERT INTO `item_basket` (quantity,basket_id,product_id) VALUES (8,3,7);
INSERT INTO `item_basket` (quantity,basket_id,product_id) VALUES (3,3,6);


/*FACTURE--------------------------------------------------------------------------------*/

INSERT INTO `facture` (id,create_date,status,address,name,surnames,phone,locality,province,postal_code,number,client_id,basket_id,company_id) VALUES (1,'2019-11-22','PENDING','CALLE CIUDAD DE MÉJICO','DANIEL','HINOJO GARCÍA','673521671','DOS HERMANAS','SEVILLA','41701','51',994,1,991);
INSERT INTO `facture` (id,create_date,status,address,name,surnames,phone,locality,province,postal_code,number,client_id,basket_id,company_id) VALUES (2,'2019-11-22','ACCEPTED','CALLE CIUDAD DE MÉJICO','DANIEL','HINOJO GARCÍA','673521671','DOS HERMANAS','SEVILLA','41701','51',994,1,991);

/*Usuario_role*/
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (991,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (992,4);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (993,2);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (994,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (995,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (996,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (997,1);
INSERT INTO `usuario_roles` (usuario_id,role_id) VALUES (998,3);