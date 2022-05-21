CREATE TABLE `discount_product` (
                                    `discount_product_id` int NOT NULL AUTO_INCREMENT,
                                    `discount_id` int NOT NULL,
                                    `commodity_id` int NOT NULL,
                                    `discount_start_date` datetime NOT NULL,
                                    `discount_end_date` datetime NOT NULL,
                                    `regist_stamp` varchar(45) NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
                                    `update_user` varchar(45) NOT NULL,
                                    `status` bit(1) NOT NULL,
                                    PRIMARY KEY (`discount_product_id`),
                                    KEY `fk_commodity_idx` (`commodity_id`),
                                    KEY `fk_discount_idx` (`discount_id`),
                                    CONSTRAINT `fk_commodity` FOREIGN KEY (`commodity_id`) REFERENCES `commodities` (`commodity_id`),
                                    CONSTRAINT `fk_discount` FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`discount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci