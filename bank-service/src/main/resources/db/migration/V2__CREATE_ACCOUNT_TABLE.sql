CREATE TABLE IF NOT EXISTS ACCOUNT
(
   `ID` BIGINT NOT NULL AUTO_INCREMENT,
   `CUSTOMER_ID` VARCHAR (20) NOT NULL UNIQUE,
   `ACCOUNT_NUMBER` VARCHAR (300) NOT NULL UNIQUE,
   `TYPE` VARCHAR (300) NOT NULL,
   `STATUS` VARCHAR (10) NOT NULL,
   `ACCOUNT_HOLDER_NAME` VARCHAR (300) NOT NULL,
   `CATEGORY_CODE` VARCHAR (20) NOT NULL,
   `BALANCE` DOUBLE
   (
      10,
      2
   )
   NOT NULL,
   `IS_NET_BANKING` BOOLEAN NOT NULL,
   BANK_ID BIGINT NOT NULL,
   PRIMARY KEY (ID),
   FOREIGN KEY (BANK_ID) REFERENCES BANK (ID)
);