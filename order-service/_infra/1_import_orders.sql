CREATE  TABLE `IMPORT_ORDERS`(
                                 `IMPORT_ORDER_ID` int NOT NULL AUTO_INCREMENT,
                                 `USER_ID` int not null ,
                                 `REGIST_STAMP` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
                                 `STATUS` int ,
                                 primary key (`IMPORT_ORDER_ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci