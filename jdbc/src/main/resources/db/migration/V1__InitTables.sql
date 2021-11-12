CREATE TABLE movie (
	id BIGSERIAL PRIMARY KEY,
	name TEXT NOT NULL unique,
	release_date DATE NOT NULL
);