DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, to_timestamp('30.05.2015 10:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Завтрак', 500);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, to_timestamp('30.05.2015 13:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Обед', 1000);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, to_timestamp('30.05.2015 20:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Ужин', 500);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, to_timestamp('31.05.2015 10:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Завтрак', 1000);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, to_timestamp('31.05.2015 13:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Обед', 500);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100000, to_timestamp('31.05.2015 20:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Ужин', 510);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100001, to_timestamp('01.06.2015 14:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Админ ланч', 510);
INSERT INTO meals (user_id, datetime, description, calories)
VALUES (100001, to_timestamp('01.06.2015 21:00:00', 'dd.MM.yyyy hh24:mi:ss'), 'Админ ужин', 1500);