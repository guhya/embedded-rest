
INSERT INTO tbUser (username,password,firstName,lastName,gender,birthday,email,country,address,mobileNo,enabled,secret,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('superadmin','$2a$10$MZvj0yYwTsOp1.1bvOVb1eb7dN8.vyiFhIpdYPPsIiaCeFqAE7./m','Super','Admin',NULL,NULL,'superadmin@mail.com',NULL,NULL,NULL,'Y',NULL,'127.0.0.1','superadmin','2017-04-05 06:57:25','127.0.0.1','superadmin','2017-04-21 00:53:21','N');

INSERT INTO tbUser (username,password,firstName,lastName,gender,birthday,email,country,address,mobileNo,enabled,secret,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('admin','$2a$10$uU.R8gIfh0eAU9ukG7vRk.iFzFHWSZcMXFRDMYKExoYZIv76yQdTS','Santino','Corleone',NULL,NULL,NULL,NULL,NULL,NULL,'Y','91e78fdf02a84e1719ff2b908206393a','127.0.0.1','superadmin','2017-04-21 00:52:14','127.0.0.1','admin','2018-05-11 08:38:43','N');

INSERT INTO tbUser (username,password,firstName,lastName,gender,birthday,email,country,address,mobileNo,enabled,secret,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('admin@admin.com','$2a$10$c3yIIJax9UWK34juPUj7luB3.q/mQbPgQysH1OSKp9mEuOICAj04C','Michael','Corleone',NULL,NULL,NULL,NULL,NULL,NULL,'Y','','127.0.0.1','superadmin','2017-09-22 08:54:08','127.0.0.1','admin@admin.com','2017-09-22 08:54:08','N');

INSERT INTO tbUserRole (username,role,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('superadmin','ROLE_ADMIN','127.0.0.1','superadmin','2017-04-05 06:57:25',NULL,NULL,NULL,'N');
INSERT INTO tbUserRole (username,role,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('superadmin','ROLE_USER','127.0.0.1','superadmin','2017-04-05 06:57:25',NULL,NULL,NULL,'N');
INSERT INTO tbUserRole (username,role,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('superadmin','ROLE_SUPER_ADMIN','127.0.0.1','superadmin','2017-04-05 06:57:25',NULL,NULL,NULL,'N');
INSERT INTO tbUserRole (username,role,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('admin','ROLE_ADMIN','127.0.0.1','superadmin','2017-04-21 00:52:14',NULL,NULL,NULL,'N');
INSERT INTO tbUserRole (username,role,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('admin','ROLE_USER','127.0.0.1','superadmin','2017-04-21 00:52:14',NULL,NULL,NULL,'N');
INSERT INTO tbUserRole (username,role,regIp,regId,regDate,modIp,modId,modDate,delYn) VALUES ('admin@admin.com','ROLE_USER','127.0.0.1','superadmin','2017-09-22 08:54:08',NULL,NULL,NULL,'N');
