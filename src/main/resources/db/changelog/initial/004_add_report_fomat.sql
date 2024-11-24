--liquibase formatted sql
--changeset beren:alter_table_and_insert_data_of_income_user


ALTER TABLE countries
    ADD COLUMN report_format varchar(255) ;

UPDATE countries
SET report_format = 'DETAILED'
WHERE code = 'USA';

UPDATE countries
SET report_format = 'SUMMARY'
WHERE name = 'Germany';

UPDATE countries
SET report_format = 'CATEGORY_WITH_SIGNATURE'
WHERE name = 'France';

INSERT INTO user_incomes (created_at, updated_at, version, amount, user_id, income_category_id)
VALUES (CURRENT_TIMESTAMP, NULL, 0, 5000.00,
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM income_categories WHERE name = 'Salary')),
       (CURRENT_TIMESTAMP, NULL, 0, 2000.00,
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM income_categories WHERE name = 'Dividends')),
       (CURRENT_TIMESTAMP, NULL, 0, 1000.00,
        (SELECT id FROM users WHERE username = 'admin'),
        (SELECT id FROM income_categories WHERE name = 'Rent'));
