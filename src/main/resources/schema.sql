\c postgres;
DROP DATABASE IF EXISTS jamboree;
CREATE DATABASE jamboree;
\connect jamboree;

CREATE TABLE party (
id SERIAL,
name TEXT,
location TEXT,
party_time TIMESTAMP WITH TIME ZONE,
created_at TIMESTAMP WITH TIME ZONE);

--TEST DATABASE--
DROP DATABASE IF EXISTS jamboreetest;
CREATE DATABASE jamboreetest;
\connect jamboreetest;

CREATE TABLE party (
id SERIAL,
name TEXT,
location TEXT,
party_time TIMESTAMP WITH TIME ZONE,
created_at TIMESTAMP WITH TIME ZONE);
