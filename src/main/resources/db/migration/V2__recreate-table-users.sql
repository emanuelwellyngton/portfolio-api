DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT UNIQUE,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL UNIQUE,
    fristName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    confirmed BIT(1) DEFAULT 0,
    signUpAt DATETIME DEFAULT NOW(),

    PRIMARY KEY(id)
);

CREATE TABLE `users-verification-code` (
    code VARCHAR(255) UNIQUE NOT NULL,
    user VARCHAR(255) NOT NULL,
    expiresAt DATETIME NOT NULL,
    valid BIT(1) DEFAULT 1,
    PRIMARY KEY(code)
)