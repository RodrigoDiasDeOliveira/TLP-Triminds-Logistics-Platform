
create table companies (
    id          bigserial primary key,
    name        varchar(255) not null,
    tenant\_id   varchar(64)  not null unique,
    created\_at  timestamp default now()
);

create table users (
    id          bigserial primary key,
    email       varchar(255) not null unique,
    password    varchar(255) not null,
    role        varchar(32)  not null,
    company\_id  bigint references companies(id)
);

create index idx\_users\_company on users(company\_id);
