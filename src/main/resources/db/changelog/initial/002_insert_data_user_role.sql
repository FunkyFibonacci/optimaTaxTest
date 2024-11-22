--liquibase formatted sql
--changeset beren:insert_user_role
INSERT INTO roles ( created_at, updated_at, version, role)
VALUES ( CURRENT_TIMESTAMP, NULL, 1, 'USER');

INSERT INTO users (created_at,updated_at,version, firstname, lastname, username, password, role_id)
VALUES (CURRENT_TIMESTAMP,null,0 ,'Beren', 'San', 'admin', '$2a$10$ZZuw8swGza0VNGOebMJ9IeHEPCROynV3YBiaAxEKwlHraDzZeTuAS',
        (SELECT id FROM roles WHERE role = 'USER'));
