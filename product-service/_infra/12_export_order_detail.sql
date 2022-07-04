CREATE TABLE `export_order_detail` (
                                       `export_order_detail_id` int NOT NULL AUTO_INCREMENT,
                                       `goods_issue_id` int DEFAULT NULL,
                                       `variant_id` int DEFAULT NULL,
                                       `quantity` int DEFAULT NULL,
                                       `price` double DEFAULT NULL,
                                       PRIMARY KEY (`export_order_detail_id`),
                                       KEY `fk_export_orders_idx` (`goods_issue_id`),
                                       CONSTRAINT `fk_export_orders` FOREIGN KEY (`goods_issue_id`) REFERENCES `export_orders` (`goods_issue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci