create database crud_jdbc_project;

create table user
(
    id       int          not null
        primary key,
    username varchar(100) null,
    password varchar(100) null
);