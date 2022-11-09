-- liquibase formatted sql

-- changeset Kohovich Dmitry:1
CREATE TABLE IF NOT EXISTS users
(
    id   BIGSERIAL      NOT NULL,
    name VARCHAR(100)   NOT NULL UNIQUE,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- changeset Kohovich Dmitry:2
CREATE TABLE IF NOT EXISTS orders
(
    id                  BIGSERIAL        NOT NULL,
    cost                NUMERIC(10, 2)   NOT NULL DEFAULT 0,
    purchase_timestamp  TIMESTAMP        NOT NULL,
    user_id             BIGSERIAL        NOT NULL,
    gift_certificate_id BIGSERIAL        NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE,
    CONSTRAINT orders_gift_certificate_id_fkey FOREIGN KEY (gift_certificate_id)
        REFERENCES gift_certificate (id)
        ON DELETE CASCADE
);
