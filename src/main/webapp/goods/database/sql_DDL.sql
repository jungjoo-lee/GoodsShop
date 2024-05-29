SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS goodsimage;
DROP TABLE IF EXISTS order_detail;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS goods;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS qna;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS Grade;
DROP TABLE IF EXISTS order_status;




/* Create Tables */

CREATE TABLE admin
(
   adminid varchar(50) NOT NULL,
   pwd varchar(50) NOT NULL,
   name varchar(10) NOT NULL,
   phone varchar(15) NOT NULL,
   email varchar(50) NOT NULL,
   PRIMARY KEY (adminid)
);


CREATE TABLE cart
(
   cseq int NOT NULL AUTO_INCREMENT,
   userid varchar(50) NOT NULL,
   gseq int NOT NULL,
   quantity int NOT NULL,
   totalprice int NOT NULL,
   indate datetime DEFAULT now() NOT NULL,
   PRIMARY KEY (cseq)
);


CREATE TABLE goods
(
   gseq int NOT NULL AUTO_INCREMENT,
   gname varchar(50) NOT NULL,
   category varchar(30) NOT NULL,
   o_price int NOT NULL,
   s_price int NOT NULL,
   m_price int NOT NULL,
   content varchar(1000) NOT NULL,
   bestyn tinyint DEFAULT 0 NOT NULL,
   useyn tinyint DEFAULT 1,
   indate datetime DEFAULT now() NOT NULL,
   PRIMARY KEY (gseq)
);


CREATE TABLE goodsimage
(
   giseq int NOT NULL AUTO_INCREMENT,
   gseq int NOT NULL,
   oriname varchar(100) NOT NULL,
   realname varchar(50) NOT NULL,
   filesize bigint NOT NULL,
   PRIMARY KEY (giseq)
);


CREATE TABLE Grade
(
   gseq int NOT NULL AUTO_INCREMENT,
   gname varchar(20) NOT NULL,
   sale int NOT NULL,
   PRIMARY KEY (gseq)
);


CREATE TABLE Member
(
   userid varchar(50) NOT NULL,
   pwd varchar(50) NOT NULL,
   gseq int NOT NULL,
   name varchar(10) NOT NULL,
   email varchar(50) NOT NULL,
   phone varchar(15) NOT NULL,
   zip_code varchar(20) NOT NULL,
   address varchar(100) NOT NULL,
   d_address varchar(100) NOT NULL,
   indate datetime DEFAULT now() NOT NULL,
   last_login_time datetime,
   is_login tinyint DEFAULT 1 NOT NULL,
   PRIMARY KEY (userid)
);


CREATE TABLE notice
(
   nseq int NOT NULL AUTO_INCREMENT,
   adminid varchar(50) NOT NULL,
   subject varchar(100) NOT NULL,
   content varchar(1000) NOT NULL,
   indate datetime DEFAULT now() NOT NULL,
   PRIMARY KEY (nseq)
);


CREATE TABLE orders
(
   oseq int NOT NULL AUTO_INCREMENT,
   userid varchar(50) NOT NULL,
   indate datetime DEFAULT now() NOT NULL,
   PRIMARY KEY (oseq)
);


CREATE TABLE order_detail
(
   odseq int NOT NULL AUTO_INCREMENT,
   oseq int NOT NULL,
   gseq int NOT NULL,
   osseq int NOT NULL,
   quantity int NOT NULL,
   totalprice int NOT NULL,
   PRIMARY KEY (odseq)
);


CREATE TABLE order_status
(
   osseq int NOT NULL AUTO_INCREMENT,
   status varchar(20) NOT NULL,
   PRIMARY KEY (osseq)
);


CREATE TABLE qna
(
   qseq int NOT NULL AUTO_INCREMENT,
   userid varchar(50) NOT NULL,
   subject varchar(100) NOT NULL,
   content varchar(1000) NOT NULL,
   indate datetime DEFAULT now() NOT NULL,
   reply varchar(1000),
   replydate datetime,
   PRIMARY KEY (qseq)
);


CREATE TABLE review
(
   rseq int NOT NULL AUTO_INCREMENT,
   userid varchar(50) NOT NULL,
   gseq int NOT NULL,
   subject varchar(100) NOT NULL,
   content varchar(1000) NOT NULL,
   indate datetime DEFAULT now() NOT NULL,
   PRIMARY KEY (rseq)
);



/* Create Foreign Keys */

ALTER TABLE notice
   ADD FOREIGN KEY (adminid)
   REFERENCES admin (adminid)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE cart
   ADD FOREIGN KEY (gseq)
   REFERENCES goods (gseq)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE goodsimage
   ADD FOREIGN KEY (gseq)
   REFERENCES goods (gseq)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE order_detail
   ADD FOREIGN KEY (gseq)
   REFERENCES goods (gseq)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE review
   ADD FOREIGN KEY (gseq)
   REFERENCES goods (gseq)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE Member
   ADD FOREIGN KEY (gseq)
   REFERENCES Grade (gseq)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE cart
   ADD FOREIGN KEY (userid)
   REFERENCES Member (userid)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE orders
   ADD FOREIGN KEY (userid)
   REFERENCES Member (userid)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE qna
   ADD FOREIGN KEY (userid)
   REFERENCES Member (userid)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE review
   ADD FOREIGN KEY (userid)
   REFERENCES Member (userid)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE order_detail
   ADD FOREIGN KEY (oseq)
   REFERENCES orders (oseq)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


ALTER TABLE order_detail
   ADD FOREIGN KEY (osseq)
   REFERENCES order_status (osseq)
   ON UPDATE RESTRICT
   ON DELETE RESTRICT
;


