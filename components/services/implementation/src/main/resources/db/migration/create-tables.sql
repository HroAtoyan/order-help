CREATE TABLE product.user
(
    user_id    UUID primary key not null,
    first_name varchar(64)      not null,
    last_name  varchar(64)      not null,
    email      varchar(64)      not null unique,
    password   varchar(64),
    account_id UUID,
    address_id UUID
);

CREATE TABLE product.address
(
    address_id UUID primary key not null,
    country    varchar(64)      not null,
    city       varchar(64)      not null,
    street     varchar(64)      not null
);

CREATE TABLE product.account
(

    account_id   UUID primary key not null,
    account_name varchar(64)      not null,
    account_type varchar(64)      not null
);