CREATE TABLE payment (
                         id         BIGSERIAL PRIMARY KEY NOT NULL,
                                      amount FLOAT,                         -- Float → real / float4
                         type VARCHAR(255),                   -- String → varchar
                         balance FLOAT,
                         age INTEGER,
                         time TIME,                           -- LocalTime → SQL TIME
                         coordinates VARCHAR(255),            -- String → геопозиция
                         updated_at TIMESTAMP,
                         created_at TIMESTAMP,
                         version    BIGINT

);
