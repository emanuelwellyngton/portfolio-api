CREATE TABLE profiles (
    id BIGINT UNIQUE NOT NULL AUTO_INCREMENT,
    user BIGINT NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    bio VARCHAR(255),
    about_me TEXT,
    localization VARCHAR(100),
    role VARCHAR(100),
    avatar VARCHAR(255),
    show_blog BIT(1) DEFAULT 1,

    PRIMARY KEY(id),
    FOREIGN KEY (user) REFERENCES users(id)
)