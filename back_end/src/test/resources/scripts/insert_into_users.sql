
INSERT INTO app_users (id, active, email, password, first_name, last_name, phone_number,
                       repository, role)
VALUES (1, true, 'test@test', '$2a$10$3qdZ8jcbc1QOXciltw5Mv.J4zZhjYeus8DeMfB/kSTfzIIaexhV3y',
        'user', 'user', '0123456789', 'https://github.com/test/test', 'ROLE_ADMIN');
INSERT INTO app_users (id, active, email, password, first_name, last_name, phone_number,
                              repository, role)
VALUES (2, true, 'user@user', '$2a$10$R5yW8A4nX2Qt5zldwMBOPed9SOTBOPzY/lx1EtZt/I19BWgMz46fm',
        'user', 'user', '0123456987', 'https://github.com/test/test', 'ROLE_USER');