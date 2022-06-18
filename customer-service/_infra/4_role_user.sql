CREATE TABLE `user_role`
(
    `user_role_id` int    NOT NULL AUTO_INCREMENT,
    `user_id`      int    NOT NULL,
    `role_id`      int    NOT NULL,
    `status`       bit(1) NOT NULL DEFAULT b'1',
    PRIMARY KEY (`user_role_id`),
    CONSTRAINT `user_role_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `user_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci