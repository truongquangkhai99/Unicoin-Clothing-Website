CREATE TABLE `suppliers` (
                             `SUPPLIER_ID` int NOT NULL AUTO_INCREMENT,
                             `SUPPLIER_NAME` varchar(255) NOT NULL,
                             `PHONE_NUMBER` varchar(15) NOT NULL,
                             `EMAIL` varchar(255) NOT NULL,
                             `MEMO` varchar(255) DEFAULT NULL,
                             `ADDRESS` varchar(255) NOT NULL,
                             `REGIST_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `UPDATE_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `UPDATE_USER` INT NOT NULL,
                             `status` INT NOT NULL,
                             PRIMARY KEY (`SUPPLIER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci