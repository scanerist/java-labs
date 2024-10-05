create table Cat(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(255) NOT NULL,
    birthdate date NOT NULL,
    breed  varchar(255) NOT NULL,
    color varchar(255) NOT NULL,
    owner_id int REFERENCES Owner(id) ON DELETE SET NULL
);