CREATE TABLE `suppliers` (
                             `supplier_id` int NOT NULL AUTO_INCREMENT,
                             `supplier_name` varchar(255) NOT NULL,
                             `phone_number` varchar(15) NOT NULL,
                             `email` varchar(255) NOT NULL,
                             `memo` varchar(255) DEFAULT NULL,
                             `address` varchar(255) NOT NULL,
                             `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `update_user` varchar(45) NOT NULL,
                             `status` bit(1) NOT NULL,
                             PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci