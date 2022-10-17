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
