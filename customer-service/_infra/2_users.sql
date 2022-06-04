CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `fullname` varchar(125) NOT NULL,
                         `phone_number` varchar(10) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `role_id` int NOT NULL,
                         `status` bit(1) NOT NULL DEFAULT b'1',
                         PRIMARY KEY (`user_id`),
                         KEY `role_user_idx` (`role_id`),
                         CONSTRAINT `role_user` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci