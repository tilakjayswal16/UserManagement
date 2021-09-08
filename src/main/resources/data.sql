DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id IDENTITY NOT NULL PRIMARY KEY,
  uuid VARCHAR(36) NOT NULL UNIQUE,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(300) NOT NULL,
  user VARCHAR(50),
  email VARCHAR(50),
  phone NUMBER
);