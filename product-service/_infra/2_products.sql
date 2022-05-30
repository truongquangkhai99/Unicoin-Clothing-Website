CREATE TABLE `products` (
                              `PRODUCT_ID` int NOT NULL AUTO_INCREMENT,
                              `PRODUCT_NAME` varchar(255) NOT NULL,
                              `PRODUCT_CODE` VARCHAR(20) NOT NULL ,
                              `SUPPLIER_ID` int not  null,
                              `REGIST_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `UPDATE_USER` INT NOT NULL,
                              `status` INT NOT NULL,
                              constraint `fk_supplier` FOREIGN KEY (`SUPPLIER_ID`) REFERENCES `SUPPLIERS` (`SUPPLIER_ID`),
                              PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci