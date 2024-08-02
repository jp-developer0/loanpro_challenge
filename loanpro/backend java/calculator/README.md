# CalculatorAPI
A Spring Boot Rest API to calculate the square root of an input number array

You have to authenticate (token expires in 24hours)


To start app:
loanpro/CalculatorAPI$ mvn spring-boot:run

To create DB:
sudo -u postgres psql template1
\c calculatordb



psql (16.3 (Ubuntu 16.3-1.pgdg22.04+1), servidor 14.12 (Ubuntu 14.12-1.pgdg22.04+1))
Digite «help» para obtener ayuda.

template1=# \i calculator_api_db.sql
DROP DATABASE
DROP ROLE
CREATE ROLE
CREATE DATABASE
psql (16.3 (Ubuntu 16.3-1.pgdg22.04+1), servidor 14.12 (Ubuntu 14.12-1.pgdg22.04+1))
Ahora está conectado a la base de datos «calculatordb» con el usuario «postgres».
ALTER DEFAULT PRIVILEGES
ALTER DEFAULT PRIVILEGES
CREATE TABLE
CREATE TABLE
CREATE TABLE
INSERT 0 1
INSERT 0 1
INSERT 0 1
INSERT 0 1
INSERT 0 1
INSERT 0 1
CREATE SEQUENCE


To Operate

1 | addition       |    1
2 | subtraction    |    1
3 | multiplication |    1
4 | division       |    1
5 | square_root    |    1
6 | random_string  |    1





