
ALTER TABLE `users-verification-code` DROP FOREIGN KEY `fk_users-vericification-code_to_users`,
CHANGE code code VARCHAR(36), CHANGE `user` user_id BIGINT,
ADD CONSTRAINT `fk_user-verification-code_to_users` FOREIGN KEY (user_id) REFERENCES users(id);