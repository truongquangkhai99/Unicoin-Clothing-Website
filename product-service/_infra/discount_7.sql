CREATE TABLE `discounts` (
                             `discount_id` int NOT NULL AUTO_INCREMENT,
                             `discount_name` varchar(45) NOT NULL,
                             `percent` int NOT NULL,
                             `memo` varchar(255) DEFAULT NULL,
                             `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `udpate_user` varchar(45) NOT NULL,
                             `status` bit(1) NOT NULL,
                             PRIMARY KEY (`discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci