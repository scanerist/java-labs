create table cat_friend(
    id int primary key generated always as identity,
    cat_id int NOT NULL ,
    friend_id int NOT  NULL
);