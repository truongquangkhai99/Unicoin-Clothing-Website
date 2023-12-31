CREATE TABLE `image` (
                         `image_id` int NOT NULL AUTO_INCREMENT,
                         `product_id` int NOT NULL,
                         `image_url` varchar(10000) NOT NULL,
                         `image_type` varchar(20),
                         `regist_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `status` bit(1),
                         PRIMARY KEY (`image_id`),
                         KEY `fk_product_image_idx` (`product_id`),
                         CONSTRAINT `fk_product_image` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci