CREATE TABLE users (
    id serial primary key,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    enabled INT NOT NULL
);

CREATE TABLE authorities (
    id serial primary key,
    username varchar(45) NOT NULL,
    authority varchar(45) NOT NULL
);

INSERT INTO users (username, password, enabled) VALUES ( 'happy', '12345', '1');
INSERT INTO authorities (username, authority) VALUES ( 'happy', 'write');

SELECT * FROM users;
SELECT * FROM authorities;

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    email varchar(45) NOT NULL,
    pwd varchar(200) NOT NULL,
    role varchar(45) NOT NULL
);

INSERT INTO customer (email, pwd, role)
VALUES ('johndoe@example.com', '54321', 'admin');

SELECT * FROM customer;