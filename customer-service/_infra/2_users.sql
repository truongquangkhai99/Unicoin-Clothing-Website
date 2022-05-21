CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `fullname` varchar(45) NOT NULL,
                         `phone_number` varchar(15) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         `address` varchar(255) NOT NULL,
                         `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `update_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `status` bit(1) NOT NULL,
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci