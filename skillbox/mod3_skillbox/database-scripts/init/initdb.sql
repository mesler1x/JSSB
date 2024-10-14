CREATE DATABASE contacts_list;
GRANT ALL PRIVILEGES ON DATABASE contacts_list TO postgres;

CREATE TABLE contacts (
                          id BIGSERIAL PRIMARY KEY,
                          first_name VARCHAR(255),
                          second_name VARCHAR(255),
                          email VARCHAR(255),
                          phone_number VARCHAR(255)
);