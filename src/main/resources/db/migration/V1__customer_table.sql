CREATE TABLE customer (
    id UUID NOT NULL,
    name VARCHAR(180) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    cnh VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    city_of_birth VARCHAR(40) NOT NULL,
    birth_date DATE NOT NULL,
    street VARCHAR(120) NOT NULL,
    number VARCHAR(6),
    neighbourhood VARCHAR(60) NOT NULL,
    additional_info VARCHAR(100),
    postal_code VARCHAR(8) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL
)