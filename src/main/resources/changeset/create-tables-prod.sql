-- liquibase formatted sql

-- changeset Kohovich Dmitry:1
CREATE TABLE IF NOT EXISTS gift_certificate
(
    id               BIGSERIAL      NOT NULL,
    name             VARCHAR(100)   NOT NULL UNIQUE,
    description      VARCHAR(100)   NOT NULL,
    price            NUMERIC(10, 2) NOT NULL DEFAULT 0,
    duration         INTEGER        NOT NULL DEFAULT 0,
    create_date      TIMESTAMP      NOT NULL,
    last_update_date TIMESTAMP      NOT NULL,
    CONSTRAINT gift_certificate_pkey PRIMARY KEY (id)
);

-- changeset Kohovich Dmitry:2
CREATE TABLE IF NOT EXISTS tag
(
    id   BIGSERIAL    NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT tag_pkey PRIMARY KEY (id)
);

-- changeset Kohovich Dmitry:3
CREATE TABLE IF NOT EXISTS gift_certificate_tag
(
    gift_certificate_id BIGSERIAL NOT NULL,
    tag_id              BIGSERIAL NOT NULL,
    CONSTRAINT gift_certificate_tag_pkey PRIMARY KEY (gift_certificate_id, tag_id),
    CONSTRAINT gift_certificate_tag_gift_certificate_id_fkey FOREIGN KEY (gift_certificate_id)
        REFERENCES gift_certificate (id)
        ON DELETE CASCADE,
    CONSTRAINT gift_certificate_tag_tag_id_fkey FOREIGN KEY (tag_id)
        REFERENCES tag (id)
        ON DELETE CASCADE
);

-- changeset Kohovich Dmitry:4
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('fitness', 'description', 115, 14, '2022-10-01', '2022-10-15');
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('sport', 'description', 75, 10, '2022-10-01', '2022-10-11');
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('travel', 'description', 102.5, 7, '2022-10-01', '2022-10-08');
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('dinner', 'description', 55, 10, '2022-10-01', '2022-10-11');
INSERT INTO gift_certificate (name, description, price, duration, create_date, last_update_date)
VALUES ('painting', 'description', 77.5, 21, '2022-10-01', '2022-10-22');

-- changeset Kohovich Dmitry:5
INSERT INTO tag (name)
VALUES ('sport');
INSERT INTO tag (name)
VALUES ('fun');
INSERT INTO tag (name)
VALUES ('mood');
INSERT INTO tag (name)
VALUES ('summer');
INSERT INTO tag (name)
VALUES ('nature');

-- changeset Kohovich Dmitry:6
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
