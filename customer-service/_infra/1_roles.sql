CREATE TABLE `roles` (
                         `role_id` int NOT NULL AUTO_INCREMENT,
                         `role_name` varchar(255) NOT NULL,
                         `memo` varchar(255) DEFAULT NULL,
                         `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `update_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `status` bit(1) NOT NULL,
                         `update_user` int NOT NULL,
                         PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci