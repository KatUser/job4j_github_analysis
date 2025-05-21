CREATE TABLE commit
(
    id SERIAL PRIMARY KEY,
    message TEXT NOT NULL,
    author VARCHAR NOT NULL,
    date TIMESTAMP NOT NULL,
    repository_id INT
        REFERENCES repository(id)
        ON DELETE CASCADE NOT NULL
);
