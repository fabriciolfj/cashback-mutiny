create table cashback(
id                  int8 NOT NULL,
value               numeric(15,4) not null,
transaction_code    varchar(255) not null,
value_transaction   numeric(15,4) not null,
customer            varchar(255) not null,
create_date         timestamp    not null,
total               numeric(15,4) not null,
primary key (id)
);

CREATE SEQUENCE cashback_seq
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;