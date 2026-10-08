create table if not exists workout(
    internal_workout_id bigserial primary key,
    external_workout_id uuid not null unique,
    user_id uuid not null unique,
    workout_name varchar(255),
    workout_description varchar(255),
    workout_date_time TIMESTAMP WITHOUT TIME ZONE,
    workout_type VARCHAR(255)
)