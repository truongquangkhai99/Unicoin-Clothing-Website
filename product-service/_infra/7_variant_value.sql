CREATE TABLE `VARIANT_VALUES`
(
    `VARIANT_ID` int NOT NULL,
    `PRODUCT_ID` INT NOT NULL,
    `OPTION_ID` INT NOT NULL,
    `OPTION_VALUE_ID` INT NOT NULL,
    PRIMARY KEY (`VARIANT_ID`, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID),
    CONSTRAINT `FK_PRODUCT_OPTION` FOREIGN KEY (`PRODUCT_ID`, `OPTION_ID`) REFERENCES `PRODUCT_OPTIONS`(`PRODUCT_ID`, `OPTION_ID`),
    CONSTRAINT `FK_VARIANTS` FOREIGN KEY (`VARIANT_ID`) REFERENCES `VARIANTS` (VARIANT_ID),
    CONSTRAINT `FK_OPTION_VALUES` FOREIGN KEY (`OPTION_VALUE_ID`) REFERENCES `OPTION_VALUES` (`OPTION_VALUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci