CREATE TABLE app_users
(
    id                 int4         NOT NULL,
    active             BOOLEAN      NOT NULL,
    email              VARCHAR(255) NOT NULL,
    encrypted_password VARCHAR(255) NOT NULL,
    first_name         VARCHAR(255) NOT NULL,
    last_name          VARCHAR(255) NOT NULL,
    phone_number       VARCHAR(255),
    repository         VARCHAR(255),
    roles              VARCHAR(255),
    primary key (id)
);

CREATE TABLE course
(
    course_title       VARCHAR(255) NOT NULL,
    course_description VARCHAR(255),
    is_active          BOOLEAN      NOT NULL
        CONSTRAINT course_pkey PRIMARY KEY
);

CREATE TABLE course_task_list
(
    course_course_title VARCHAR(255)     NOT NULL
        CONSTRAINT fkdhxfwkyp5nwoc1hj2lq9ewta8
            REFERENCES course,
    points              DOUBLE PRECISION NOT NULL,
    task_text           VARCHAR(255),
    task_title          VARCHAR(255)
);
