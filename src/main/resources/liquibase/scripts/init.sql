-- liquibase formatted sql

drop table if exists employees cascade;

create table employees
(
    id         integer        not null
        constraint employees_pk
            primary key,
    first_name  varchar        not null,
    last_name   varchar        not null,
    department varchar,
    salary numeric(10, 2) not null
);

create sequence employee_seq;

drop table if exists payments cascade;

create table payments
(
    id          integer not null
        constraint payments_pk
            primary key,
    amount numeric(10, 2),
    comment     varchar,
    id_employee integer
        constraint payments_fk
            references employees
);

create sequence payment_seq;