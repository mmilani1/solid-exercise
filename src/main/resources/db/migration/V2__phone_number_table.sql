CREATE TABLE phone_number (
    id UUID NOT NULL,
    customer_id UUID NOT NULL,
    alias VARCHAR(50),
    number VARCHAR(13) NOT NULL
)