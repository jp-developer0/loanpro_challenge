drop database calculatordb;
drop user calculator;
create user calculator with password 'password';
create database calculatordb with template=template0 owner=calculator;
\connect calculatordb;
alter default privileges grant all on tables to calculator;
alter default privileges grant all on sequences to calculator;

create table users(
user_id integer primary key not null,
email varchar(30) not null,
password text not null,
status varchar(30),
balance DOUBLE PRECISION
);

create table operations(
operation_id integer primary key not null,
type varchar(30) not null,
cost DOUBLE PRECISION
);

create table records(
record_id integer primary key not null,
type_operation varchar(30),
user_id integer,
amount DOUBLE PRECISION,
user_balance DOUBLE PRECISION,
operation_response varchar(30),
fecha date,
CONSTRAINT fk_user
      FOREIGN KEY(user_id) 
        REFERENCES users(user_id)
);

INSERT INTO operations(operation_id, type, cost)
VALUES (1,'addition',1.0);

INSERT INTO operations(operation_id, type, cost)
VALUES (2,'subtraction',1.0);

INSERT INTO operations(operation_id, type, cost)
VALUES (3,'multiplication',1.0);

INSERT INTO operations(operation_id, type, cost)
VALUES (4,'division',1.0);

INSERT INTO operations(operation_id, type, cost)
VALUES (5,'square_root',1.0);

INSERT INTO operations(operation_id, type, cost)
VALUES (6,'random_string',1.0);

create sequence users_seq increment 1 start 1;