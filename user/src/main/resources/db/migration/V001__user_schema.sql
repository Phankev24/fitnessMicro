create table if not exists users(
    internal_user_id bigserial primary key,
    external_user_id uuid not null unique,
    user_name varchar(50),
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50),
    password varchar(50),
    phone_number varchar(50)
)