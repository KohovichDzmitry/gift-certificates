-- liquibase formatted sql

-- changeset Kohovich Dmitry:1
INSERT INTO gift_certificate (id, name, description, price, duration, create_date, last_update_date)
VALUES (1, 'fitness', 'description', 115.15, 14, current_timestamp(3), current_timestamp(3));
INSERT INTO gift_certificate (id, name, description, price, duration, create_date, last_update_date)
VALUES (2, 'sport', 'favorite description', 75.75, 10, current_timestamp(3), current_timestamp(3));
INSERT INTO gift_certificate (id, name, description, price, duration, create_date, last_update_date)
VALUES (3, 'travel', 'description', 102.05, 7, current_timestamp(3), current_timestamp(3));
INSERT INTO gift_certificate (id, name, description, price, duration, create_date, last_update_date)
VALUES (4, 'dinner', 'description', 55.55, 10, current_timestamp(3), current_timestamp(3));
INSERT INTO gift_certificate (id, name, description, price, duration, create_date, last_update_date)
VALUES (5, 'painting', 'description', 77.65, 21, current_timestamp(3), current_timestamp(3));
SELECT SETVAL('gift_certificate_id_seq', (SELECT MAX(id) FROM gift_certificate));

-- changeset Kohovich Dmitry:2
INSERT INTO tag (id, name)
VALUES (1, 'sport');
INSERT INTO tag (id, name)
VALUES (2, 'fun');
INSERT INTO tag (id, name)
VALUES (3, 'mood');
INSERT INTO tag (id, name)
VALUES (4, 'summer');
INSERT INTO tag (id, name)
VALUES (5, 'nature');
SELECT SETVAL('tag_id_seq', (SELECT MAX(id) FROM tag));

-- changeset Kohovich Dmitry:3
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (1, 1);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (1, 3);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (2, 1);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (2, 2);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (2, 3);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (3, 3);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (3, 5);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (4, 4);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (5, 3);
INSERT INTO gift_certificate_tag (gift_certificate_id, tag_id)
VALUES (5, 5);

-- changeset Kohovich Dmitry:4
INSERT INTO users (id, name)
VALUES (1, 'Qwerty');
INSERT INTO users (id, name)
VALUES (2, 'Solomon');
INSERT INTO users (id, name)
VALUES (3, 'Ronaldo');
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

-- changeset Kohovich Dmitry:5
INSERT INTO orders (id, cost, purchase_timestamp, user_id, gift_certificate_id)
VALUES (1, 115.15, current_timestamp(3), 3, 1);
INSERT INTO orders (id, cost, purchase_timestamp, user_id, gift_certificate_id)
VALUES (2, 75.75, current_timestamp(3), 2, 2);
INSERT INTO orders (id, cost, purchase_timestamp, user_id, gift_certificate_id)
VALUES (3, 102.05, current_timestamp(3), 3, 3);
INSERT INTO orders (id, cost, purchase_timestamp, user_id, gift_certificate_id)
VALUES (4, 55.55, current_timestamp(3), 1, 4);
SELECT SETVAL('orders_id_seq', (SELECT MAX(id) FROM orders));
