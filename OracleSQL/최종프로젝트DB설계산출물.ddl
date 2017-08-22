-- 생성자 Oracle SQL Developer Data Modeler 4.1.3.901
--   위치:        2017-07-31 11:03:34 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g




CREATE TABLE board
  (
    bno      NUMBER (10) NOT NULL ,
    btitle   VARCHAR2 (100) NOT NULL ,
    bcontent VARCHAR2 (4000) NOT NULL ,
    writer   VARCHAR2 (10) NOT NULL
  ) ;
ALTER TABLE board ADD CONSTRAINT board_PK PRIMARY KEY ( bno ) ;


CREATE TABLE hobby
  (
    mid   VARCHAR2 (10) NOT NULL ,
    hobby VARCHAR2 (20) NOT NULL
  ) ;
ALTER TABLE hobby ADD CONSTRAINT hobby_PK PRIMARY KEY ( mid, hobby ) ;


CREATE TABLE member
  (
    mid       VARCHAR2 (10) NOT NULL ,
    mname     VARCHAR2 (10) NOT NULL ,
    mpassword VARCHAR2 (10)
  ) ;
ALTER TABLE member ADD CONSTRAINT member_PK PRIMARY KEY ( mid ) ;


CREATE TABLE order_item
  (
    orders_ono  NUMBER (10) NOT NULL ,
    product_pno NUMBER (10) NOT NULL ,
    oamount     NUMBER (5)
  ) ;


CREATE TABLE orders
  (
    ono        NUMBER (10) NOT NULL ,
    member_mid VARCHAR2 (10) NOT NULL ,
    odate      DATE NOT NULL
  ) ;
ALTER TABLE orders ADD CONSTRAINT orders_PK PRIMARY KEY ( ono ) ;


CREATE TABLE product
  (
    pno   NUMBER (10) NOT NULL ,
    pname VARCHAR2 (20) NOT NULL
  ) ;
ALTER TABLE product ADD CONSTRAINT product_PK PRIMARY KEY ( pno ) ;


ALTER TABLE board ADD CONSTRAINT board_member_FK FOREIGN KEY ( writer ) REFERENCES member ( mid ) ;

ALTER TABLE hobby ADD CONSTRAINT hobby_member_FK FOREIGN KEY ( mid ) REFERENCES member ( mid ) ;

ALTER TABLE order_item ADD CONSTRAINT order_item_orders_FK FOREIGN KEY ( orders_ono ) REFERENCES orders ( ono ) ;

ALTER TABLE order_item ADD CONSTRAINT order_item_product_FK FOREIGN KEY ( product_pno ) REFERENCES product ( pno ) ;

ALTER TABLE orders ADD CONSTRAINT orders_member_FK FOREIGN KEY ( member_mid ) REFERENCES member ( mid ) ;


-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             6
-- CREATE INDEX                             0
-- ALTER TABLE                             10
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
