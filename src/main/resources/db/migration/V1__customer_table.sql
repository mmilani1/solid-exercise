CREATE TABLE customer (
    id UUID NOT NULL,
    name VARCHAR(180) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(40) NOT NULL,
    city_of_birth VARCHAR(40) NOT NULL
)