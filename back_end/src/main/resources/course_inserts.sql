INSERT INTO course(course_title, course_description, is_active)
VALUES ('Test course 1', 'Description for course 1', true),
       ('Test course 2', 'Description for course 2', true),
       ('Test course 3', 'Description for course 3', false),
       ('Test course 4', 'Description for course 4', true),
       ('Test course 5', 'Description for course 5', true),
       ('Test course 6', 'Description for course 6', false),
       ('Test course 7', 'Description for course 7', true);

INSERT INTO course_task_list(task_title, course_course_title, points, task_text)
VALUES ('Test task 1', 'Test course 1', 2.5, 'Do something else'),
       ('Test task 2', 'Test course 1', 2, 'Do something else'),
       ('Test task 3', 'Test course 1', 10, 'Do something else'),
       ('Test task 4', 'Test course 2', 21.5, 'Do something else'),
       ('Test task 5', 'Test course 2', 0.6, 'Do something else'),
       ('Test task 6', 'Test course 2', 4, 'Do something else'),
       ('Test task 7', 'Test course 3', 0.6, 'Do something else'),
       ('Test task 8', 'Test course 3', 15, 'Do something else'),
       ('Test task 9', 'Test course 3', 4.5, 'Do something else'),
       ('Test task 10', 'Test course 4', 8, 'Do something else'),
       ('Test task 11', 'Test course 4', 5.5, 'Do something else'),
       ('Test task 12', 'Test course 5', 7.5, 'Do something else'),
       ('Test task 13', 'Test course 6', 9.5, 'Do something else'),
       ('Test task 14', 'Test course 6', 11.5, 'Do something else'),
       ('Test task 15', 'Test course 7', 13.5, 'Do something else'),
       ('Test task 16', 'Test course 7', 20, 'Do something else');

