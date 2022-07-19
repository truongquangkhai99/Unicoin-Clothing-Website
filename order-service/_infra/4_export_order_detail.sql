CREATE TABLE `export_order_detail` (
                                       `export_order_detail_id` int NOT NULL,
                                       `export_order_id` int DEFAULT NULL,
                                       `variant_id` int DEFAULT NULL,
                                       `variant_name` varchar(200) DEFAULT NULL,
                                       `quantity` int DEFAULT NULL,
                                       `price` int DEFAULT NULL,
                                       PRIMARY KEY (`export_order_detail_id`),
                                       KEY `fk_export_orders_idx` (`export_order_id`),
                                       CONSTRAINT `fk_export_orders` FOREIGN KEY (`export_order_id`) REFERENCES `export_orders` (`export_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci