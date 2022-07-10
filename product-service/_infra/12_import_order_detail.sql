CREATE TABLE `IMPORT_ORDER_DETAIL`(
    `IMPORT_ORDER_DETAIL_ID` int NOT NULL AUTO_INCREMENT,
    `VARIANT_ID` INT NOT NULL ,
    `QUANTITY` INTEGER ,
    `COST` INTEGER,
    `IMPORT_ORDER_ID` INT NOT NULL,
    CONSTRAINT `FK_IMPORT_ORDERS_IMPORT_ORDER_DETAIL` FOREIGN KEY (`IMPORT_ORDER_ID`) REFERENCES  `IMPORT_ORDERS` (`IMPORT_ORDER_ID`),
    CONSTRAINT `FK_VARIANTS_IMPORT_ORDER_DETAIL` FOREIGN KEY (`VARIANT_ID`) REFERENCES `VARIANTS` (`VARIANT_ID`),
    PRIMARY KEY (`IMPORT_ORDER_DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci