create database bootcamp_2508_final_project;

create table if not exists sp500_symbols (
    symbol VARCHAR(10) primary key UNIQUE NOT NULL
);

create table if not exists sp500_info (
    symbol 					VARCHAR(10) 	primary key NOT NULL UNIQUE,
    security 				VARCHAR(255) 	NOT NULL,
    gics_sector 			VARCHAR(255) 	NOT NULL,
    gics_sub_industry 		VARCHAR(255) 	NOT NULL,
    headquarters_location 	VARCHAR(255) 	NOT NULL,
    date_added 				DATE 			NOT NULL,
    cik 					VARCHAR(20) 	NOT NULL,
    founded 				TEXT 			NOT NULL
);

drop table IF EXISTS sp500_finnhub_profiles;
create table IF NOT EXISTS sp500_finnhub_profiles (
    symbol                				VARCHAR(10) PRIMARY KEY,
    country               				VARCHAR(100),
    currency             				VARCHAR(10),
    estimate_currency     				VARCHAR(10),
    exchange              				VARCHAR(255),
    main_industry      				    VARCHAR(255),
    floating_share_million      		NUMERIC,
    ipo_date              				DATE,
    logo                  				TEXT,
    market_cap_usd_million          	NUMERIC,
    name                  				VARCHAR(255),
    phone                 				VARCHAR(50),
    share_outstanding_million     		NUMERIC,
    ticker                				VARCHAR(10),
    weburl                				TEXT,
    raw_json              				JSONB
);

create index idx_symbol on sp500_finnhub_profiles (symbol);
create index idx_ticker on sp500_finnhub_profiles (ticker);
create index idx_industry on sp500_finnhub_profiles (main_industry);
create index idx_exchange on sp500_finnhub_profiles (exchange);
  

drop table IF EXISTS sp500_historical_data;
create table sp500_historical_data (
    symbol  VARCHAR(10)   NOT NULL,
    date    DATE          NOT NULL
	metric  VARCHAR(6)    NOT NULL CHECK (metric IN ('open','high','low','close','volume')),
    value   DOUBLE PRECISION,
    PRIMARY KEY (symbol, date, metric)
);

create index idx_symbol on sp500_historical_data (symbol);
create index idx_date   on sp500_historical_data (date);

alter table sp500_symbols
drop column company_name;

drop table sp500_symbols;
drop table sp500_info;
drop table sp500_historical_data;

select * from sp500_symbols;

select * from sp500_symbols limit 10;

select count(*) from sp500_symbols;

select * from sp500_info;

select * from sp500_finnhub_profiles;

select * from sp500_historical_data;

-- All AAPL prices
select * from sp500_historical_data where symbol = 'AAPL' order by date;

-- Latest close for every stock (sort by symbol)
select DISTINCT on (symbol) symbol, date, value
from sp500_historical_data
where metric = 'close'
order by symbol, date asc;

-- Latest close for every stock (sort by date)
select DISTINCT on (date) date, symbol, value
from sp500_historical_data
where metric = 'close'
order by date asc, symbol;

-- Close price of AAPL on each date
select symbol, date, value
from sp500_historical_data
where symbol = 'AAPL' and metric = 'close'
order by date asc;

