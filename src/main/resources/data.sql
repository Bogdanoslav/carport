INSERT INTO body_type (name) VALUES
('Coupe'),
('Sedan'),
('Van'),
('Compact'),
('Pickup');

INSERT INTO color (name) VALUES
('White'),
('Black'),
('Gray'),
('Silver'),
('Red'),
('Blue'),
('Brown'),
('Green'),
('Orange'),
('Gold'),
('Yellow'),
('Purple');

INSERT INTO car_brand (name) VALUES
('BMW'),
('Audi'),
('Volkswagen');

INSERT INTO car_model (name, brand_id) VALUES
('X3', 1),
('X4', 1),
('X5', 1),
('X6', 1),
('Q3', 2),
('Q4', 2),
('Q5', 2),
('Q6', 2),
('Golf', 3),
('Passat', 3),
('Tiguan', 3),
('Polo', 3);

INSERT INTO car(model_id, color_id, body_type_id) VALUES
(1, 1, 1),
(2, 2, 2),
(2, 1, 2),
(5, 1, 3),
(2, 3, 2);

INSERT into role(name) VALUES
('ROLE_ADMIN'),
('ROLE_MODERATOR'),
('ROLE_USER');
