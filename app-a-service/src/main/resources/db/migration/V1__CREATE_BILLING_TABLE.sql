create schema if NOT EXISTS test_schema;


CREATE TABLE test_schema.payment_information (
    id INT PRIMARY KEY,
    payment_method VARCHAR(255) NOT NULL
);