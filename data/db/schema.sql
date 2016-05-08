DROP DATABASE IF EXISTS sis;
CREATE DATABASE IF NOT EXISTS sis CHARACTER SET = 'utf8' COLLATE 'utf8_bin';
USE sis;

CREATE TABLE User (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  username        VARCHAR(32)       UNIQUE NOT NULL,
  role            VARCHAR(255)             NOT NULL,
  password        TINYBLOB                 NOT NULL,
  firstName       VARCHAR(64)              NOT NULL,
  lastName        VARCHAR(64)              NOT NULL
);

CREATE TABLE Authentication (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  token           VARCHAR(255)      UNIQUE NOT NULL,
  user            BIGINT                   NOT NULL,

  FOREIGN KEY (user)
  REFERENCES User(id)
    ON DELETE CASCADE
);

