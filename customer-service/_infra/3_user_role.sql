CREATE TABLE `user_role` (
                             `user_role_id` int NOT NULL AUTO_INCREMENT,
                             `user_name` varchar(255) NOT NULL,
                             `role_id` int NOT NULL,
                             `status` bit(1) NOT NULL,
                             PRIMARY KEY (`user_role_id`),
                             KEY `fk_user_user_role_idx` (`user_name`),
                             KEY `fk_role_userole_idx` (`role_id`),
                             CONSTRAINT `fk_role_userole` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
                             CONSTRAINT `fk_user_user_role` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci