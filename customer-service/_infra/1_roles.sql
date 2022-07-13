CREATE TABLE `roles` (
                         `role_id` int NOT NULL AUTO_INCREMENT,
                         `role_name` varchar(255) NOT NULL,
                         `memo` varchar(255),
                         `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `status` bit(1) NOT NULL DEFAULT b'1',
                         PRIMARY KEY (`role_id`),
                         KEY `IDX1` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci