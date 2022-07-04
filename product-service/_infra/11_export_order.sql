CREATE TABLE `export_orders` (
                                 `goods_issue_id` int NOT NULL AUTO_INCREMENT,
                                 `used_id` int DEFAULT NULL,
                                 `name_recipient` varchar(45) DEFAULT NULL,
                                 `phone_recipient` varchar(45) DEFAULT NULL,
                                 `address` varchar(255) DEFAULT NULL,
                                 `regist_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `status` int DEFAULT NULL,
                                 PRIMARY KEY (`goods_issue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci