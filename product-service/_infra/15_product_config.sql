CREATE TABLE `config`
(
    `id`    int NOT NULL AUTO_INCREMENT,
    `name`  varchar(50),
    `value` varchar(50),
    `type`  varchar(30),
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci