

INSERT INTO person(first_name, last_name, email, age, about) VALUES
('Pawel', 'Polansky', 'paswel1234@mail.com', 28, 'About Pawel'),
('Pontiac', 'Pontonov', 'pontuan@mail.com', 28, 'About Pontiac'),
('Polina', 'Polovina', 'polovina1234@mail.com', 28, 'About Polina'),
('Dmitry', 'Deiteriy', 'deiteriy1234@mail.com', 28, 'About Dmitry'),
('Diana', 'Dodivana', 'divan1234@mail.com', 28, 'About Diana'),
('Denis', 'Deanis', 'deanis1234@mail.com', 28, 'About Denis');

INSERT INTO user_credentials(id, username, password, is_active, deleted_at) VALUES
(1, 'pawel1234', '$2a$10$NCfuvfKv3PtrQFklUAVMxuq6rj19O3LqvMpAIvAo9kXzPf3Ro1Uz.', true, NULL),
(2, 'pontiac1234', '$2a$10$XzN70MYY/XtV9Se0r/A6/u6NHUH2t7JI8csKGLls5saMHNrr5HpZu', true, NULL),
(3, 'polina1234', '$2a$10$BDegyvcNjpAtoXGlMQJ1UePgn07gjNfIuEnwq4k9zRVWpWriBRa/6', true, NULL),
(4, 'dmitryl1234', '$2a$10$R66LynPQP0.siELSvDtPPuUgIW6Da.mXLAEP/9yvzlxU2UbOSdIdu', true, NULL),
(5, 'diana1234', '$2a$10$T1zawn2KyhRlx87Z78i74uTH5B53usMOR7pDhqUAvW9Ew1g.zzwby', true, NULL),
(6, 'denis1234', '$2a$10$aCqX0QDa31R728Xd2onkfeo3ZUPWmWbfd5ioZQl6bJFXNBbD/p9YG', true, NULL);

INSERT INTO driver(id, driving_experience) VALUES
(4, 2),
(5, 1),
(6, 5);

INSERT INTO passenger(id) VALUES
(1),
(2),
(3);


INSERT into role(name) VALUES
('ROLE_ADMIN'),
('ROLE_MODERATOR'),
('ROLE_PASSENGER'),
('ROLE_DRIVER');

INSERT INTO user_credentials_roles(users_id, roles_id) VALUES
(1,3),
(2,3),
(3,3),
(4,4),
(5,4),
(6,4);


INSERT INTO car_brand (name) VALUES
('BMW'),
('Audi'),
('Volkswagen');

--
--INSERT INTO car_model (name, brand_id) VALUES
--('X3', 1),
--('X4', 1),
--('X5', 1),
--('X6', 1),
--('Q3', 2),
--('Q4', 2),
--('Q5', 2),
--('Q6', 2),
--('Golf', 3),
--('Passat', 3),
--('Tiguan', 3),
--('Polo', 3);
--
--INSERT INTO user_description(first_name, last_name, email, about, age) VALUES
--('User', 'One', 'user_role1@mail.com', 'Test User 1', '30'),
--('User', 'Two', 'user_role2@mail.com', 'Test User 2', '40'),
--('Arcadiy', 'Arcadievich', 'arcadiy@mail.com', 'Arcadiy', '30'),
--('Boris', 'Borisovich', 'boris@mail.com', 'Boris', '40'),
--('Deleted', 'User', 'user_deleted@mail.com', 'Deleted User', '27');
--
--insert into users (email,password, role_id,user_description_id, ,is_deleted) VALUES
--('user_role1_D', 1, '$2a$10$CDE.Oxqtt0qhI5FeX4XbBO6RiN9Sd/.imZK8CVJEzjHQi1ZbaCsuy', false),
--('user_role1_P', 1, '$2a$10$CDE.Oxqtt0qhI5FeX4XbBO6RiN9Sd/.imZK8CVJEzjHQi1ZbaCsuy', false),
--('user_role2_D', 2, '$2a$10$Wx4xZ3pW6NBFei4K1ol0QeO2VJ2YQ1xqEc/kw4KGkgBbd8oZys7me', false),
--('user_role2_P', 2, '$2a$10$Wx4xZ3pW6NBFei4K1ol0QeO2VJ2YQ1xqEc/kw4KGkgBbd8oZys7me', false),
--('arcadiy1234_D', 3, '$2a$10$T3OZxIWNzovk3Tu3ztGcTuPVd5TFuzOh756cynMmWoFpXWC6Oxco2', false),
--('arcadiy1234_P', 3, '$2a$10$T3OZxIWNzovk3Tu3ztGcTuPVd5TFuzOh756cynMmWoFpXWC6Oxco2', false),
--('boris1234_D', 4, '$2a$10$AHeoOb2scoCjTmNo7TgUUO4S5XmzlftNzYEi1vDS.6S415pvpF/a2', false),
--('boris1234_P', 4, '$2a$10$AHeoOb2scoCjTmNo7TgUUO4S5XmzlftNzYEi1vDS.6S415pvpF/a2', false),
--('user_deleted_D', 5, '$2a$10$AHeoOb2scoCjTmNo7TgUUO4S5XmzlftNzYEi1vDS.6S415pvpF/a2', true),
--('user_deleted_P', 5, '$2a$10$AHeoOb2scoCjTmNo7TgUUO4S5XmzlftNzYEi1vDS.6S415pvpF/a2', true);
--
--
--INSERT INTO car(model_id, body_type, color, owner_id, status) VALUES
--(1, 'COUPE', 'RED', 1, 'ACTIVE'), --user_role1_D
--(2, 'SEDAN', 'WHITE', 1, 'ACTIVE'), --user_role1_D
--(2, 'PICKUP', 'GREEN', 3, 'ACTIVE'), --user_role2_P
--(5, 'VAN', 'BLUE', 3, 'ACTIVE'), --user_role2_P
--(2, 'COUPE', 'BLACK', 3, 'ACTIVE'), --user_role2_P
--(3, 'VAN', 'GREEN', 5, 'ACTIVE'), --arcadiy1234_D
--(6, 'COMPACT', 'GREEN', 7, 'ACTIVE'), --boris1234_D
--(6, 'CUPE', 'RED', 7, 'ACTIVE'), --boris1234_D
--(6, 'SEDAN', 'RED', 9, 'ACTIVE'),
--(3, 'SEDAN', 'BLACK', 1, 'ACTIVE'); --user_role1_D
--
--INSERT into role(name) VALUES
--('ROLE_ADMIN'),
--('ROLE_MODERATOR'),
--('ROLE_DRIVER'),
--('ROLE_PASSENGER');
--
--insert into user_roles (user_id, role_id) VALUES
--(1, 3),
--(2, 4),
--(3, 3),
--(4, 4),
--(5, 3),
--(6, 4),
--(7, 3),
--(8, 4),
--(9, 3),
--(10, 4);
--
--INSERT INTO address(city, address, latitude, longitude) VALUES
--('Kyiv', 'Heroiv Stalingrada str. 25 ', 50.519433, 30.513256),
--('Lviv', 'Yaponska str. 42 ', 49.833384, 24.010846),
--('Odesa', 'Pushkinskaya str. 15', 46.481243, 30.743010),
--('Mykolaiv', 'Shkilna str. 6 ', 46.304000, 30.652592),
--('Vinnitsya', 'Zamkova str. 69 ', 49.221868, 28.479539),
--('Uman', 'Matrosova str. 9 ', 48.752448, 30.207700),
--('Uman', 'Matrosova str. 9 ', 48.752448, 30.207700),
--('Vinnitsya', 'Zamkova str. 69 ', 49.221868, 28.479539),
--('Mykolaiv', 'Shkilna str. 6 ', 46.304000, 30.652592),
--('Odesa', 'Pushkinskaya str. 15', 46.481243, 30.743010),
--('Mykolaiv', 'Shkilna str. 6 ', 46.304000, 30.652592),
--('Vinnitsya', 'Zamkova str. 69 ', 49.221868, 28.479539);
--
--
--
--INSERT INTO trip(departure_address_id, arrival_address_id,
--                departure_time, arrival_time, car_id,
--                about, total_seats, seats_left, price, status, instant_booking, created_at) VALUES
--(1, 2, '2022-09-01 09:00:00', '2022-09-01 21:30:00', 1, 'test ride 1 COMPLETED PAST', 3, 2, 450, 'COMPLETED', false,'2022-08-25 15:32:00'),
--(3, 4, '2022-09-15 08:00:00', '2022-09-15 20:30:00', 1, 'test ride 2 CANCELED PAST', 4, 1, 250, 'CANCELED', false, '2022-09-14 09:00:00'),
--(5, 6, '2022-11-02 09:00:00', '2022-11-02 21:15:00', 2, 'test ride 3 SCHEDULED FUTURE', 4, 2, 250, 'SCHEDULED', false, '2022-11-01 20:15:00'),
--(7, 8, '2022-12-03 10:00:00', '2022-12-03 15:20:00', 1, 'test ride 4 SCHEDULED FUTURE', 3, 1, 250, 'SCHEDULED', false, '2022-11-28 13:00:00'),
--(9, 10, '2023-10-01 09:00:00', '2022-09-01 20:00:00', 3, 'test ride 5 CANCELED FUTURE', 1, 1, 450, 'CANCELED', false, '2023-09-15 14:31:00'),
--(11, 12, '2023-10-02 12:00:00', '2023-10-02 12:00:00', 4, 'test ride 6 SCHEDULED FUTURE', 3, 0, 100, 'SCHEDULED', false, '2023-10-01 11:00:00');
--
--INSERT INTO booking(user_id, trip_id, status, booked_at) VALUES
--(4, 1, 'WAITING', '2022-08-28 09:00:00'),
--(6, 1, 'APPROVED', '2022-08-01 08:00:00'),
--(6, 2, 'REJECTED', '2022-09-14 08:00:00'),
--(8, 3, 'APPROVED', '2022-11-01 09:00:00'),
--(2, 3, 'APPROVED', '2022-11-01 10:30:00'),
--(4, 4, 'REJECTED', '2022-12-02 10:00:00'),
--(2, 5, 'WAITING', '2023-10-02 08:00:00');
--
--
