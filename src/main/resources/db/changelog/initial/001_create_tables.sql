-- liquibase formatted sql

-- changeset Beren:create_tables

CREATE TABLE countries
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT,
    name       VARCHAR(255) UNIQUE   NOT NULL,
    code       VARCHAR(3)            NOT NULL UNIQUE
);

CREATE TABLE income_categories
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT,
    name       VARCHAR(255) UNIQUE   NOT NULL
);

CREATE TABLE tax_rates
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP,
    version            BIGINT,
    country_id         BIGINT                NOT NULL,
    income_category_id BIGINT                NOT NULL,
    rate               DECIMAL(5, 2)         NOT NULL,
    FOREIGN KEY (country_id) REFERENCES countries (id) ON DELETE CASCADE,
    FOREIGN KEY (income_category_id) REFERENCES income_categories (id) ON DELETE CASCADE,
    CONSTRAINT unique_tax_rate UNIQUE (country_id, income_category_id)
);

CREATE TABLE roles
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT,
    role       VARCHAR(255)          NOT NULL
);


CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    version    BIGINT,
    firstname  VARCHAR(255),
    lastname   VARCHAR(255),
    username   VARCHAR(255) UNIQUE   NOT NULL,
    password   VARCHAR(255)          NOT NULL,
    role_id    BIGINT
);

ALTER TABLE users
    ADD CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles (id);


CREATE TABLE user_incomes
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL ,
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP,
    version            BIGINT,
    user_id            BIGINT                NOT NULL,
    income_category_id BIGINT                NOT NULL,
    amount             NUMERIC(15, 2)        NOT NULL CHECK (amount >= 0),
    income_date        DATE                  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (income_category_id) REFERENCES income_categories (id) ON DELETE CASCADE
);

ALTER TABLE user_incomes
    ADD CONSTRAINT unique_user_income UNIQUE (user_id, income_category_id, income_date);
