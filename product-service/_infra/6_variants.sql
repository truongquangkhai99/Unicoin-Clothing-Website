CREATE TABLE `VARIANTS`
(
    `VARIANT_ID` int NOT NULL AUTO_INCREMENT,
    `PRODUCT_ID` INT NOT NULL,
    `SKU_ID` VARCHAR(255) NOT NULL,
    `QTY` INT NOT NULL,
    `PRICE` INT NOT NULL,
    PRIMARY KEY (`VARIANT_ID`),
    CONSTRAINT `FK_PRODUCT` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCTS`(`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci