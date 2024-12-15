ALTER TABLE `users-verification-code`
CHANGE user user BIGINT,
ADD CONSTRAINT `fk_users-vericification-code_to_users`
FOREIGN KEY (user) REFERENCES users(id);