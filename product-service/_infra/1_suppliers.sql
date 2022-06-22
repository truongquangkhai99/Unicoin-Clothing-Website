CREATE TABLE `suppliers` (
                             `SUPPLIER_ID` int NOT NULL AUTO_INCREMENT,
                             `SUPPLIER_CODE` VARCHAR(10),
                             `SUPPLIER_NAME` varchar(255),
                             `PHONE_NUMBER` varchar(15),
                             `EMAIL` varchar(255),
                             `MEMO` varchar(255),
                             `ADDRESS` varchar(255),
                             `REGIST_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `UPDATE_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `UPDATE_USER` INT,
                             `status` INT,
                             PRIMARY KEY (`SUPPLIER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci