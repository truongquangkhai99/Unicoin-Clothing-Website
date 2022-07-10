CREATE TABLE `products` (
                              `PRODUCT_ID` int NOT NULL AUTO_INCREMENT,
                              `PRODUCT_NAME` varchar(255),
                              `PRODUCT_CODE` VARCHAR(20),
                              `SUPPLIER_ID` int NOT NULL ,
                              `DESCRIPTION` VARCHAR(3000),
                              `REGIST_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `UPDATE_USER` INT,
                              `status` INT,
                              constraint `fk_supplier_products` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `SUPPLIERS` (`SUPPLIER_ID`),
                              PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci