/* ROLES------------------------------------------------------------------------------- */
INSERT INTO `role` (id,nombre) VALUES (1,'ROLE_CLIENT');
INSERT INTO `role` (id,nombre) VALUES (2,'ROLE_ADMIN');
INSERT INTO `role` (id,nombre) VALUES (3,'ROLE_REPORTER');
INSERT INTO `role` (id,nombre) VALUES (4,'ROLE_COMPANY');

/*COMPANIES-----------------------------------------------------------------------------*/
INSERT INTO `company` (id,name,surnames,username,password,enabled,phone,email,business_name,image,category,about,role) VALUES (1,'daniel','hinojo garcía','danhingar','people3012',1,'672389123','danielhingar3397@gmail.com','showcase','foto1','deportes',1,4);

/*ABOUTS--------------------------------------------------------------------------------*/
INSERT INTO `about`  (id,address,instagram,facebook,description,images) VALUES (1,'calle Cuba','https://www.instagram.com/danielhingar/','https://facebook.com','Esta empresa comenzó hace ya 10 años',null);

/*ADMINS--------------------------------------------------------------------------------*/
INSERT INTO `admin` (id,name,surnames,username,password,enabled,phone,email,role) VALUES (1,'Antonio','Pérez','admin','admin1',1,'674839213', 'admin@gmail.com',2);

/*BASKET---------------------------------------------------------------------------------*/

INSERT INTO `basket` (id) VALUES (1);
INSERT INTO `basket` (id) VALUES (2);
INSERT INTO `basket` (id) VALUES (3);
INSERT INTO `basket` (id) VALUES (4);

/*CLIENT--------------------------------------------------------------------------------*/
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket,role) VALUES (1,'Ramón','Pérez','rampez','decide123',1,'674839213', 'rampez@gmail.com',1,1);
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket,role) VALUES (2,'Pablo','Cabeza','pabcab','pablo1234',1,'67483973', 'pabcab@gmail.com',2,1);
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket,role) VALUES (3,'Marta','García','margar','marta1234',1,'674839908', 'margar@gmail.com',3,1);
INSERT INTO `client` (id,name,surnames,username,password,enabled,phone,email,basket,role) VALUES (4,'Clara','Hinojo','clahin','clara1234',1,'674839672', 'clahin@gmail.com',4,1);

/*REPORTER------------------------------------------------------------------------------*/
INSERT INTO `reporter` (id,name,surnames,username,password,enabled,phone,email,role) VALUES (1,'Luis','García','lusgar','tokio99',1,'674839213', 'lusgar@gmail.com',3);

/*SHIPPING------------------------------------------------------------------------------*/

INSERT INTO `shipping` (id,admin,date_max,date_min,title,observation,price) VALUES (1,1,2,5,'Envío Express',null,10.0);
INSERT INTO `shipping` (id,admin,date_max,date_min,title,observation,price) VALUES (2,1,4,7,'Envío Normal',null,5.0);
INSERT INTO `shipping` (id,admin,date_max,date_min,title,observation,price) VALUES (3,1,5,14,'Envío Gratis',null,0.0);

/*CONFIGURATIONS-------------------------------------------------------------------------*/

INSERT INTO `configuration` (id,phone,email) VALUES (1,'673456721','showcase@gmail.com');

/*CLAIMS---------------------------------------------------------------------------------*/

INSERT INTO `claim` (id,title,description,create_date,attachment,reporter,facture) VALUES (1,'PEDIDO NO HA LLEGADO','SE HA PASADO EL TIEMPO DE ENVÍO','2019-11-21',null,1,2);

/*PRODUCTS-------------------------------------------------------------------------------*/

INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (1,1,'NIKE AIR-MAX','zapatillas con suela ancha','photo1',100.7,'2019-11-23','SHOES','37,38,39,40,41',null,null,'550 gramos',null,null,'NIKE',null);
INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (2,1,'ZAPATILLA ULTRABOOST','zapatillas perfecta para correr','photo1',120.7,'2019-11-23','SHOES','37,38,39,40,41',null,null,'550 gramos',null,null,'ADIDAS',null);
INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (3,1,'LÁMPARA DE TECHO METAL','lámpara de aluminio perfecta para el salón','photo1',50.7,'2019-11-23','DECORATION',null,null,null,'300 gramos','20 pulgadas',null,'NIKE',null);
INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (4,1,'CHAQUETA ACOLCHADA','chaqueta de lana perfecta para el invierno','photo1',69.7,'2019-11-23','CLOTHES','S,M,L,XL',null,null,'550 gramos',null,null,'SPRINGFIELD',20);
INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (5,1,'CAMISETA MANGA CORTA','camiseta con logo de disney','photo1',30.7,'2019-11-23','CLOTHES','S,M',null,null,null,null,null,'PULL&BEARD',null);
INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (6,1,'IPHONE 11','nuevo modelo de iphone','photo1',1000.7,'2019-11-23','TECNOLOGY',null,'18 cm','7 cm','750 gramos','10','36,64','IPHONE',null);
INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (7,1,'PORTATIL HP','portatil hp de última generación','photo1',600.7,'2019-11-23','TECNOLOGY',null,'20 cm','40 cm','550 gramos','17',null,'HP',null);
INSERT INTO `product` (id,company,name,description,photo,price,create_date,category,size,height,width,weight,inch,capacity,brand,offert) VALUES (8,1,'SAL DEL HIMALAYA','sal del himalaya en escamas','photo1',6.7,'2019-11-23','FOOD',null,null,null,'250 gramos',null,null,'CARREFOUR',null);

/*RELATION BASKET-PRODUCTS*/
INSERT INTO `basket_products` (basket_id,products_id) VALUES (1,1);
INSERT INTO `basket_products` (basket_id,products_id) VALUES (1,2);
INSERT INTO `basket_products` (basket_id,products_id) VALUES (1,3);
INSERT INTO `basket_products` (basket_id,products_id) VALUES (2,4);
INSERT INTO `basket_products` (basket_id,products_id) VALUES (2,5);
INSERT INTO `basket_products` (basket_id,products_id) VALUES (3,1);
INSERT INTO `basket_products` (basket_id,products_id) VALUES (3,6);


/*FACTURE--------------------------------------------------------------------------------*/

INSERT INTO `facture` (id,create_date,status,address,name,surnames,phone,locality,province,postal_code,number,client,basket) VALUES (1,'2019-11-22','PENDING','CALLE CIUDAD DE MÉJICO','DANIEL','HINOJO GARCÍA','673521671','DOS HERMANAS','SEVILLA','41701','51',1,1);
INSERT INTO `facture` (id,create_date,status,address,name,surnames,phone,locality,province,postal_code,number,client,basket) VALUES (2,'2019-11-22','ACCEPTED','CALLE CIUDAD DE MÉJICO','DANIEL','HINOJO GARCÍA','673521671','DOS HERMANAS','SEVILLA','41701','51',1,1);
