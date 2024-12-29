CREATE TABLE `user_verification_code` (
    id BIGINT UNIQUE NOT NULL AUTO_INCREMENT,
    code CHAR(36) UNIQUE NOT NULL,
    user BIGINT NOT NULL,
    expires_at DATETIME NOT NULL,
    valid BIT(1) DEFAULT 1,
    PRIMARY KEY(id),
    FOREIGN KEY (user) REFERENCES users(id)
)