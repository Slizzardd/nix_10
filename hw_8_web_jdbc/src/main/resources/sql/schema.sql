create database taxi_bd;
create table drivers
(
    id         bigint auto_increment
        primary key,
    created    datetime(6) null,
    updated    datetime(6) null,
    visible    bit null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    balance integer,
    notes  varchar(300)
);

create table cars
(
    id               bigint auto_increment
        primary key,
    created          datetime(6)  null,
    updated          datetime(6)  null,
    visible          bit          null,
    cars_name        varchar(255) not null,
    image_url        varchar(255),
    color        varchar(255) not null,
    years_of_issue int          not null,
    engine_of_capacity double 		not null
);

create table driver_car
(
    driver_id bigint not null,
    car_id   bigint auto_increment
        not null,
    primary key (driver_id, car_id),
    foreign key (driver_id) references drivers (id) on delete cascade,
    foreign key (car_id) references cars (id)
);