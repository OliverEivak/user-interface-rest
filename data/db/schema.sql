DROP DATABASE IF EXISTS sis;
CREATE DATABASE IF NOT EXISTS sis CHARACTER SET = 'utf8' COLLATE 'utf8_bin';
USE sis;

CREATE TABLE User (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  username           VARCHAR(32)       UNIQUE NOT NULL,
  role               VARCHAR(255)             NOT NULL,
  password           TINYBLOB                 NOT NULL,
  firstName          VARCHAR(64)              NOT NULL,
  lastName           VARCHAR(64)              NOT NULL,
  studentGroup       INT             NOT NULL DEFAULT 0
);

CREATE TABLE Authentication (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  token              VARCHAR(255)      UNIQUE NOT NULL,
  user               BIGINT                   NOT NULL,

  FOREIGN KEY (user)
  REFERENCES User(id)
    ON DELETE CASCADE
);

CREATE TABLE GradeGroup (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  name               VARCHAR(255)             NOT NULL,
  description        VARCHAR(255)             NOT NULL,
  solo               BOOLEAN        NOT NULL DEFAULT 0
);

CREATE TABLE Grade (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  name               VARCHAR(255)             NOT NULL,
  minValue           INT            NOT NULL DEFAULT 0,
  `maxValue`         INT            NOT NULL DEFAULT 0,
  linkAllowed        BOOLEAN        NOT NULL DEFAULT 1,
  gradeGroup         BIGINT                   NOT NULL,

  FOREIGN KEY (gradeGroup)
  REFERENCES GradeGroup(id)
  ON DELETE RESTRICT
);

CREATE TABLE StudentGrade (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  user               BIGINT                   NOT NULL,
  value              INT            NOT NULL DEFAULT 0,
  grade              BIGINT                   NOT NULL,
  comment            VARCHAR(255)             NOT NULL,

  FOREIGN KEY (user)
  REFERENCES User(id)
    ON DELETE RESTRICT,

  FOREIGN KEY (grade)
  REFERENCES Grade(id)
    ON DELETE RESTRICT
);

CREATE TABLE Link (
  id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
  grade              BIGINT                   NOT NULL,
  user               BIGINT                   NOT NULL,
  url                VARCHAR(2048)            NOT NULL,

  FOREIGN KEY (grade)
  REFERENCES Grade(id)
    ON DELETE RESTRICT,

  FOREIGN KEY (user)
  REFERENCES User(id)
    ON DELETE RESTRICT
);

