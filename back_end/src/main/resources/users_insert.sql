INSERT INTO app_users(id, active, email, password, first_name, last_name, phone_number,
                      repository, roles)
VALUES (1, true, 'test@test', 'user', 'user', 'user', '012345789', 'git@repo', 'ROLE_USER'),
       (2, true, 'admin@admin', 'admin', 'admin', 'admin', '012345789', 'git@repo', 'ROLE_ADMIN');