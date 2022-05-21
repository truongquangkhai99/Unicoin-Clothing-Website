CREATE TABLE `size_id` (
                           `size_id` int NOT NULL AUTO_INCREMENT,
                           `size_name` varchar(45) NOT NULL,
                           `regist_stamp` varchar(45) NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
                           `update_user` varchar(45) NOT NULL,
                           `status` bit(1) NOT NULL,
                           PRIMARY KEY (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci