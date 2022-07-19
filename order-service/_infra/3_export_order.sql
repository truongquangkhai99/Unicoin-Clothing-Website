CREATE TABLE `export_orders` (
                                 `export_order_id` int NOT NULL AUTO_INCREMENT,
                                 `USER_PHONE_NUMBER` VARCHAR(20) not null,
                                 `name_recipient` varchar(45) DEFAULT NULL,
                                 `phone_recipient` varchar(45) DEFAULT NULL,
                                 `address` varchar(255) DEFAULT NULL,
                                 `regist_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `status` int DEFAULT NULL,
                                 PRIMARY KEY (`export_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci