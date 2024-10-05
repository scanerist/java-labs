create table Owner(
    id int primary key GENERATED ALWAYS AS IDENTITY,
    name varchar(50) not null,
    birthdate date not null
);