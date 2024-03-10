--Follow these steps for manual setup:
--\c postgres;
--DROP DATABASE IF EXISTS jamboree;
--CREATE DATABASE jamboree;
--\connect jamboree;
--DROP TABLE IF EXISTS party;

CREATE TABLE IF NOT EXISTS party (
id SERIAL PRIMARY KEY NOT NULL,
name TEXT,
location TEXT,
party_time TIMESTAMP WITH TIME ZONE,
created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW());