CREATE TABLE tbUser (
  seq integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username varchar(50) NOT NULL,
  password varchar(255) NOT NULL,
  firstName varchar(50) DEFAULT NULL,
  lastName varchar(50) DEFAULT NULL,
  gender varchar(1) DEFAULT NULL,
  birthday date DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  country varchar(45) DEFAULT NULL,
  address varchar(100) DEFAULT NULL,
  mobileNo varchar(45) DEFAULT NULL,
  enabled varchar(1) DEFAULT NULL,
  secret varchar(256) DEFAULT NULL,
  regIp varchar(20) DEFAULT NULL,
  regId varchar(50) DEFAULT NULL,
  regDate TIMESTAMP  DEFAULT NULL,
  modIp varchar(20) DEFAULT NULL,
  modId varchar(50) DEFAULT NULL,
  modDate TIMESTAMP DEFAULT NULL,
  delYn varchar(1) DEFAULT NULL,
  PRIMARY KEY (seq)
); 

CREATE TABLE tbUserRole (
  seq integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username varchar(50) NOT NULL,
  role varchar(100) NOT NULL,
  regIp varchar(20) DEFAULT NULL,
  regId varchar(50) DEFAULT NULL,
  regDate TIMESTAMP DEFAULT NULL,
  modIp varchar(20) DEFAULT NULL,
  modId varchar(50) DEFAULT NULL,
  modDate TIMESTAMP DEFAULT NULL,
  delYn varchar(1) NOT NULL,
  PRIMARY KEY (seq)
);