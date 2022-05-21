CREATE TABLE `manufacturers` (
                                 `manufacturer_id` int NOT NULL AUTO_INCREMENT,
                                 `manufacturer_name` varchar(255) NOT NULL,
                                 `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `update_user` varchar(45) NOT NULL,
                                 `status` bit(1) NOT NULL DEFAULT b'1',
                                 PRIMARY KEY (`manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci