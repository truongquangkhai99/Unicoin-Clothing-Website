CREATE TABLE `commodities` (
                               `commodity_id` int NOT NULL AUTO_INCREMENT,
                               `commodity_name` varchar(255) NOT NULL,
                               `category_id` int NOT NULL,
                               `description` varchar(255) DEFAULT NULL,
                               `manufacturer_id` int NOT NULL,
                               `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `update_user` varchar(45) NOT NULL,
                               `del_flg` bit(1) NOT NULL,
                               `status` tinyint NOT NULL,
                               PRIMARY KEY (`commodity_id`),
                               KEY `fk_cartegory_commodity_idx` (`category_id`),
                               KEY `fk_manufacturer_commodity_idx` (`manufacturer_id`),
                               CONSTRAINT `fk_cartegory_commodity` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
                               CONSTRAINT `fk_manufacturer_commodity` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`manufacturer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci