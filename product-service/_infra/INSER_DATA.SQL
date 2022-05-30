
# insert table suppliers
INSERT INTO `unicoin_product_service`.`suppliers` (`SUPPLIER_NAME`, `PHONE_NUMBER`, `EMAIL`, `ADDRESS`, `UPDATE_USER`, `status`) VALUES ('LEVI\'S', '012345678', 'levis@gmail.com', 'Anh', 1, 1);
INSERT INTO `unicoin_product_service`.`suppliers` (`SUPPLIER_NAME`, `PHONE_NUMBER`, `EMAIL`, `ADDRESS`, `UPDATE_USER`, `status`) VALUES ('Amazon', '012345678', 'amazon@gmail.com', 'Mỹ', 1, 1);
INSERT INTO `unicoin_product_service`.`suppliers` (`SUPPLIER_NAME`, `PHONE_NUMBER`, `EMAIL`, `ADDRESS`, `UPDATE_USER`, `status`) VALUES ('Nike', '012345678', 'nike@gmail.com', 'Mỹ', 1, 1);
INSERT INTO `unicoin_product_service`.`suppliers` (`SUPPLIER_NAME`, `PHONE_NUMBER`, `EMAIL`, `ADDRESS`, `UPDATE_USER`, `status`) VALUES ('Adidas', '012345678', 'adidas@gmail.com', 'Đức', 1, 1);
INSERT INTO `unicoin_product_service`.`suppliers` (`SUPPLIER_NAME`, `PHONE_NUMBER`, `EMAIL`, `ADDRESS`, `UPDATE_USER`, `status`) VALUES ('May10', '012345678', 'may10@gmail.com', 'Việt Nam', 1, 1);

# insert table products
INSERT INTO `unicoin_product_service`.`products` (`PRODUCT_NAME`, `PRODUCT_CODE`, `SUPPLIER_ID`, `UPDATE_USER`, `status`) VALUES ('Quần bò', 'QB1', '1', '1', '1');
INSERT INTO `unicoin_product_service`.`products` (`PRODUCT_NAME`, `PRODUCT_CODE`, `SUPPLIER_ID`, `UPDATE_USER`, `status`) VALUES ('Quần bò', 'QB2', '2','1', '1');
INSERT INTO `unicoin_product_service`.`products` (`PRODUCT_NAME`, `PRODUCT_CODE`, `SUPPLIER_ID`, `UPDATE_USER`, `status`) VALUES ('Áo sơ mi', 'AM1', '1', '1', '1');
INSERT INTO `unicoin_product_service`.`products` (`PRODUCT_NAME`, `PRODUCT_CODE`, `SUPPLIER_ID`, `UPDATE_USER`, `status`) VALUES ('Áo sơ mi', 'AM1', '1', '1', '1');
INSERT INTO `unicoin_product_service`.`products` (`PRODUCT_NAME`, `PRODUCT_CODE`, `SUPPLIER_ID`, `UPDATE_USER`, `status`) VALUES ('Áo polo', 'AP3', '3', '1', '1');
INSERT INTO `unicoin_product_service`.`products` (`PRODUCT_NAME`, `PRODUCT_CODE`, `SUPPLIER_ID`, `UPDATE_USER`, `status`) VALUES ('Váy', 'VA4', '4', '1', '1');
INSERT INTO `unicoin_product_service`.`products` (`PRODUCT_NAME`, `PRODUCT_CODE`, `SUPPLIER_ID`, `UPDATE_USER`, `status`) VALUES ('Váy', 'VA5', '5', '1', '1');

# insert table options
INSERT INTO `unicoin_product_service`.options (`OPTION_CODE`, `OPTION_NAME`, `status`) VALUES ('SI', 'Size', '1');
INSERT INTO `unicoin_product_service`.options (`OPTION_CODE`, `OPTION_NAME`, `status`) VALUES ('MA', 'Màu', '1');
INSERT INTO `unicoin_product_service`.options (`OPTION_CODE`, `OPTION_NAME`, `status`) VALUES ('CL', 'Chất liệu', '1');
INSERT INTO `unicoin_product_service`.options (`OPTION_CODE`, `OPTION_NAME`, `status`) VALUES ('KI', 'Kiểu', '1');
INSERT INTO `unicoin_product_service`.options (`OPTION_CODE`, `OPTION_NAME`, `status`) VALUES ('GT', 'Giới tính', '1');

# insert table product_options
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (1, 1);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (1, 2);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (1, 3);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (1, 5);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (2, 1);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (2, 2);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (3, 1);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (3, 2);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (3, 5);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (4, 1);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (5, 2);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (6, 5);
insert into `unicoin_product_service`.product_options (PRODUCT_ID, OPTION_ID)  values (7, 4);

# insert table option_values
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (1, 'L', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (1, 'M', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (1, 'S', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (1, 'XL', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (1, 'XXL', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (2, 'Red', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (2, 'Blue', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (2, 'White', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (2, 'Black', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (3, 'Thô', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (3, '100% cotton', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (3, '10% cotton', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (3, 'Lụa', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (3, 'Thô', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (4, 'Bó', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (4, 'Xòe', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (4, 'Dài tay', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (4, 'Cộc tay', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (4, 'Váy zip', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (4, 'Váy dài xòe', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (4, 'Váy dài bó', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (5, 'Nam', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (5, 'Nữ', 1);
insert into `unicoin_product_service`.option_values (OPTION_ID, OPTION_VALUE, STATUS) values (5, 'All', 1);

# insert table variant
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (1, 'QB1-SI1-MA6-CL11-GT22', 99, 100000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (1, 'QB1-SI2-MA6-CL11-GT22', 99, 200000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (1, 'QB1-SI3-MA6-CL11-GT22', 99, 300000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (1, 'QB1-SI4-MA6-CL11-GT22', 99, 400000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (1, 'QB1-SI5-MA6-CL11-GT22', 99, 500000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (1, 'QB1-SI1-MA7-CL11-GT22', 99, 110000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (3, 'AM1-SI2-MA7-GT22', 99, 220000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (4, 'AM5-SI3-MA6-GT22', 99, 330000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (6, 'VA4-GT23', 99, 440000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (7, 'VA5-KI13', 99, 550000);
INSERT INTO `unicoin_product_service`.variants (PRODUCT_ID, SKU_ID, QTY, PRICE) VALUES (7, 'VA5-KI11', 99, 660000);

# insert table VARIANT_VALUES
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (1, 1, 1, 1);
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (1, 1, 2, 6);
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (1, 1, 3, 11);
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (1, 1, 5, 22);
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (2, 1, 1, 2);
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (2, 1, 2, 6);
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (2, 1, 3, 11);
INSERT INTO `unicoin_product_service`.variant_values (VARIANT_ID, PRODUCT_ID, OPTION_ID, OPTION_VALUE_ID) VALUES (2, 1, 5, 22);