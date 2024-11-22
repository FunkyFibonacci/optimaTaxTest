--liquibase formatted sql
--changeset beren:isert_tax_rates_countries_categoriesIncome

INSERT INTO countries ( created_at, updated_at, version, name, code)
VALUES ( CURRENT_TIMESTAMP, null, 0, 'United States', 'USA'),
       ( CURRENT_TIMESTAMP, null, 0, 'Germany', 'DEU'),
       ( CURRENT_TIMESTAMP, null, 0, 'France', 'FRA');


INSERT INTO income_categories ( created_at, updated_at, version, name)
VALUES (CURRENT_TIMESTAMP, null, 0, 'Salary'),
       ( CURRENT_TIMESTAMP, null, 0, 'Dividends'),
       ( CURRENT_TIMESTAMP, null, 0, 'Rent');


INSERT INTO tax_rates (created_at, updated_at, version, country_id, income_category_id, rate)
VALUES
    -- для США
    ( CURRENT_TIMESTAMP, null, 0, 1, 1, 20.00),
    (CURRENT_TIMESTAMP, null, 0, 1, 2, 15.00),

    -- для германии
    ( CURRENT_TIMESTAMP, null, 0, 2, 1, 25.00),
    ( CURRENT_TIMESTAMP, null, 0, 2, 3, 30.00),

    -- для франции
    ( CURRENT_TIMESTAMP, null, 0, 3, 1, 30.00),
    ( CURRENT_TIMESTAMP, null, 0, 3, 2, 20.00),
    ( CURRENT_TIMESTAMP, null, 0, 3, 3, 35.00);
