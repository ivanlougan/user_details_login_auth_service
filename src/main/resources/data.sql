INSERT INTO
    application_user(first_name, last_name, email, password)
VALUES
    -- adminjiebiepski@example.com / hard
    ('Mati', 'Jebiepski', 'adminjebiepski@example.com', '{bcrypt}$2a$10$O2M1YEPhjCDsy/whzM1XXu3P0Naf2hHq62rjYiFUqQ0GQagZJs8SC'),
    -- johnweak@example.com / weak
    ('John', 'Weak', 'johnweak@example.com', '{argon2}$argon2id$v=19$m=16384,t=2,p=1$R9WR7YoP9ps8j4MBTvoPsA$wtbGGgbAkVF8lxXzUTU1iyH7yFO6XTuLys+/qyxhtV4');

INSERT INTO
    user_role (name, description)
VALUES
    ('ADMIN', 'Full access'),
    ('USER', 'Basic access');



INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2);