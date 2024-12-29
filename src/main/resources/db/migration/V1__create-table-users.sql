CREATE TABLE users (
    id BIGINT AUTO_INCREMENT UNIQUE,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    confirmed BIT(1),
    sign_up_at DATETIME NOT NULL,

    PRIMARY KEY(id)
)