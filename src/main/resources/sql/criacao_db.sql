CREATE DATABASE employees;

CREATE TABLE employee
(
    email character varying,
    id serial NOT null,
    name character varying(30),
    surname character varying(50),
    nis bigint NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT proper_email CHECK (email::text ~* '^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'::text),
    CONSTRAINT name_size CHECK (char_length(name::text) >= 2 AND char_length(name::text) <= 30),
    CONSTRAINT surname_size CHECK (char_length(surname::text) >= 2 AND char_length(surname::text) <= 50)
)