

create database bootcamp_2508_final_project;

create table if not exists sp500_symbols (
    symbol VARCHAR(10) primary key UNIQUE NOT NULL
);

create table if not exists sp500_info (
    symbol VARCHAR(10) primary key NOT NULL UNIQUE,
    security VARCHAR(255) NOT NULL,
    gics_sector VARCHAR(255) NOT NULL,
    gics_sub_industry VARCHAR(255) NOT NULL,
    headquarters_location VARCHAR(255) NOT NULL,
    date_added DATE NOT NULL,
    cik VARCHAR(20) NOT NULL,
    founded TEXT NOT NULL
);

alter table sp500_symbols
drop column company_name;

drop table sp500_symbols;
drop table sp500_info;

select * from sp500_symbols;

select * from sp500_symbols limit 10;

select count(*) from sp500_symbols;

select * from sp500_info;
