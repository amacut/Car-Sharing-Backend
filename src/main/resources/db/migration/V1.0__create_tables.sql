DROP TABLE IF EXISTS users, user_company_details, users_wallet, user_driving_license, vehicle_types, vehicle_models, vehicles, rentals_history, promotions;

CREATE TABLE users
(
    id               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name       VARCHAR(32)  NOT NULL,
    last_name        VARCHAR(32)  NOT NULL,
    gender           VARCHAR(32)  NOT NULL,
    email            VARCHAR(64)  NOT NULL UNIQUE,
    phone_number     VARCHAR(12)  NOT NULL,
    password         VARCHAR(32)  NOT NULL,
    birth_date       DATE         NOT NULL,
    country          VARCHAR(32)  NOT NULL,
    street           VARCHAR(32)  NOT NULL,
    house_no_flat_no VARCHAR(9)   NOT NULL,
    postcode         VARCHAR(6)   NOT NULL,
    city             VARCHAR(32)  NOT NULL,
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    deleted_at       TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE user_company_details
(
    id               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id          INT UNSIGNED NOT NULL,
    company_name     VARCHAR(32),
    nip_number       VARCHAR(10),
    country          VARCHAR(32),
    street           VARCHAR(32),
    house_no_flat_no VARCHAR(9),
    postcode         VARCHAR(6),
    city             VARCHAR(32),

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE users_wallet
(
    id       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id  INT UNSIGNED NOT NULL,
    currency VARCHAR(3),
    value    DOUBLE,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE user_driving_license
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    photo   VARCHAR(100),

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE vehicle_types
(
    id              INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name            VARCHAR(32)  NOT NULL,
    driving_price   DOUBLE       NOT NULL,
    stop_over_price DOUBLE       NOT NULL,
    distance_price  DOUBLE       NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE vehicle_models
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    type_id INT UNSIGNED NOT NULL,
    brand   VARCHAR(32)  NOT NULL,
    model   VARCHAR(32)  NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (type_id) REFERENCES vehicle_types (id)
);

CREATE TABLE vehicles
(
    id            INT UNSIGNED NOT NULL AUTO_INCREMENT,
    model_id      INT UNSIGNED NOT NULL,
    registration  VARCHAR(12)  NOT NULL,
    max_fuel      DOUBLE       NOT NULL,
    current_fuel  DOUBLE       NOT NULL,
    max_range     DOUBLE       NOT NULL,
    current_range DOUBLE       NOT NULL,
    latitude      VARCHAR(9)   NOT NULL,
    longitude     VARCHAR(9)   NOT NULL,
    status        VARCHAR(8)   NOT NULL,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    deleted_at    TIMESTAMP,

    PRIMARY KEY (id),
    FOREIGN KEY (model_id) REFERENCES vehicle_models (id)
);

CREATE TABLE rentals_history
(
    id              INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id         INT UNSIGNED NOT NULL,
    vehicle_id      INT UNSIGNED NOT NULL,
    rent_date       TIMESTAMP,
    return_date     TIMESTAMP,
    driving_time    TIME,
    driving_price   DOUBLE,
    distance        DOUBLE,
    distance_price  DOUBLE,
    stop_over_time  TIME,
    stop_over_price DOUBLE,
    total_price     DOUBLE,
    is_active       BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
);

CREATE TABLE promotions
(
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name        VARCHAR(32)  NOT NULL,
    code        VARCHAR(32)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    deleted_at  TIMESTAMP,
    PRIMARY KEY (id)
);
