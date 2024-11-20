--liquibase formatted sql
--changeset beren:insert_user_role
INSERT INTO roles (id, created_at, updated_at, version, role)
VALUES
    (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 'USER');

INSERT INTO users (firstname, lastname, username, password, role_id)
VALUES
    ('Beren', 'San', 'admin', '$2a$10$ZZuw8swGza0VNGOebMJ9IeHEPCROynV3YBiaAxEKwlHraDzZeTuAS',  (SELECT id FROM roles WHERE role = 'USER'));
