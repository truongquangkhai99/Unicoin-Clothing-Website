CREATE TABLE `address` (
                           `address_id` int NOT NULL AUTO_INCREMENT,
                           `line` varchar(255) NOT NULL,
                           `user_id` int NOT NULL,
                           `status` bit(1) NOT NULL DEFAULT b'1',
                           `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`address_id`),
                           KEY `user_address_idx` (`user_id`),
                           CONSTRAINT `user_address` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci