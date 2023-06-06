create table public.plans
(
    id            serial
        constraint plans_pk
            primary key,
    start_date    date default now() not null,
    total_days    integer            not null,
    meals_per_day integer            not null,
    closed        boolean            not null
);

create table public.special_days
(
    id    serial
        constraint special_days_pk
            primary key,
    date  date    not null,
    meals integer not null
);

