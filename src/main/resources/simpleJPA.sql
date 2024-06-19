
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