create table IF NOT EXISTS CUSTOMER
(
    CUSTOMER_ID int not null auto_increment,
    CUSTOMER_NAME varchar(255) not null,
    primary key(CUSTOMER_ID)
    );

create table IF NOT EXISTS TRANSACTION
(
    TRANSACTION_ID int not null,
    CUSTOMER_ID int ,
    TRANSACTION_DATE DATE,
    AMOUNT int,
    primary key(TRANSACTION_ID)
 );