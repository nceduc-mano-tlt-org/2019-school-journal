INSERT INTO projects(id, name, description, created_date, modified_date) VALUES
  (random_uuid(), 'Default', 'Default description', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (random_uuid(), 'Foreign language courses', 'Courses for different age groups', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO usr (id, username, password, created_date, modified_date, active, project_id) VALUES
  -- password: 'admin'
  (random_uuid(), 'admin', '$2a$08$.OTxetRFa4QXVrgibkt3MelvABbJBQxRGe2SJgvaudIr9S9b/NUWu', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,
    (SELECT id FROM projects WHERE name = 'Default')),
  -- password:  'user'
  (random_uuid(), 'user', '$2a$08$6ztqYrhYvO8P2mFJhTxkNOyaoGcsgcLTnI4Ia5qgWk51p06rlwd1a', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), true,
    (SELECT id FROM projects WHERE name = 'Foreign language courses'));

-- sections for account with username = 'user'
INSERT INTO sections (id, name, description, created_date, modified_date, project_id) VALUES
  (random_uuid(), 'English', 'Section for English learners', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
    (SELECT project_id FROM usr WHERE username = 'user')),
  (random_uuid(), 'Spanish', 'Section for Spanish learners', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
    (SELECT project_id FROM usr WHERE username = 'user')),
  (random_uuid(), 'French', 'Section for French learners', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
    (SELECT project_id FROM usr WHERE username = 'user'));

-- groups for first section
INSERT INTO groups (id, name, description, start_date, cost, section_id, created_date, modified_date) VALUES
  (random_uuid(), 'Beginner', 'Group for beginner level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 1000,
    (SELECT id FROM sections WHERE name = 'English'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (random_uuid(), 'Intermediate', 'Group for intermediate level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 1500,
    (SELECT id FROM sections WHERE name = 'English'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (random_uuid(), 'Advanced', 'Group for advanced level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 2000,
    (SELECT id FROM sections WHERE name = 'English'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- groups for second section
INSERT INTO groups (id, name, description, start_date, cost, section_id, created_date, modified_date) VALUES
  (random_uuid(), 'Beginner', 'Group for beginner level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 1000,
    (SELECT id FROM sections WHERE name = 'Spanish'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (random_uuid(), 'Intermediate', 'Group for intermediate level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 1500,
    (SELECT id FROM sections WHERE name = 'Spanish'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (random_uuid(), 'Advanced', 'Group for advanced level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 2000,
    (SELECT id FROM sections WHERE name = 'Spanish'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- groups for third section
INSERT INTO groups (id, name, description, start_date, cost, section_id, created_date, modified_date) VALUES
  (random_uuid(), 'Beginner', 'Group for beginner level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 1000,
    (SELECT id FROM sections WHERE name = 'French'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (random_uuid(), 'Intermediate', 'Group for intermediate level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 1500,
    (SELECT id FROM sections WHERE name = 'French'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (random_uuid(), 'Advanced', 'Group for advanced level students', parsedatetime('01-05-2019 10:00', 'dd-MM-yyyy hh:mm'), 2000,
    (SELECT id FROM sections WHERE name = 'French'), CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- teachers for English beginner group
INSERT INTO teachers (id, first_name, last_name, created_date, modified_date, group_id) VALUES
  (random_uuid(), 'Ekaterina', 'Lavrova', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
    (SELECT id FROM groups WHERE name = 'Beginner' and section_id = (SELECT id FROM sections WHERE name = 'English')));

-- students for English beginner group
INSERT INTO students (id, first_name, last_name, created_date, modified_date, group_id) VALUES
  (random_uuid(), 'Vyacheslav', 'Slavov', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
    (SELECT id FROM groups WHERE name = 'Beginner' and section_id = (SELECT id FROM sections WHERE name = 'English')));

-- wallets for students
INSERT INTO wallets (id, student_id, balance, created_date, modified_date) VALUES
  (random_uuid(), (SELECT id FROM students WHERE first_name = 'Vyacheslav'), 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());