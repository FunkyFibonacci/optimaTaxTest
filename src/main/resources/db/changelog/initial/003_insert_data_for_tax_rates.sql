--liquibase formatted sql
--changeset beren:isert_tax_rates_countries_categoriesIncome

INSERT INTO countries (id, created_at, updated_at, version, name, code)
VALUES (1, CURRENT_TIMESTAMP, null, 0, 'United States', 'USA'),
       (2, CURRENT_TIMESTAMP, null, 0, 'Germany', 'DEU'),
       (3, CURRENT_TIMESTAMP, null, 0, 'France', 'FRA');


INSERT INTO income_categories (id, created_at, updated_at, version, name)
VALUES (1, CURRENT_TIMESTAMP, null, 0, 'Salary'),
       (2, CURRENT_TIMESTAMP, null, 0, 'Dividends'),
       (3, CURRENT_TIMESTAMP, null, 0, 'Rent');


INSERT INTO tax_rates (id, created_at, updated_at, version, country_id, income_category_id, rate)
VALUES
    -- для США
    (1, CURRENT_TIMESTAMP, null, 0, 1, 1, 20.00),
    (2, CURRENT_TIMESTAMP, null, 0, 1, 2, 15.00),

    -- для германии
    (3, CURRENT_TIMESTAMP, null, 0, 2, 1, 25.00),
    (4, CURRENT_TIMESTAMP, null, 0, 2, 3, 30.00),

    -- для франции
    (5, CURRENT_TIMESTAMP, null, 0, 3, 1, 30.00),
    (6, CURRENT_TIMESTAMP, null, 0, 3, 2, 20.00),
    (7, CURRENT_TIMESTAMP, null, 0, 3, 3, 35.00);
