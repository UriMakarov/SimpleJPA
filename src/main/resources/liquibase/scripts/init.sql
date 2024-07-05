-- liquibase formatted sql

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


INSERT INTO employees (id, first_name, last_name, department, salary) VALUES
(1, 'John', 'Doe', 'IT', 1000.00),
(2, 'Jane', 'Smith', 'HR', 1500.00),
(3, 'Alice', 'Johnson', 'Marketing', 1200.00);

INSERT INTO payments (id, amount, comment, id_employee) VALUES
(20, 1000.00, 'Monthly salary', 1),
(21, 500.00, 'Monthly salary', 2),
(22, 1000.00, 'Monthly salary', 3),
(23, 1500.00, 'Overtime payment', 2),
(24, 1200.00, 'Bonus', 3);

commit;