CREATE DATABASE pdfexp;

DROP TABLE IF EXISTS `pdf_exp`;
CREATE TABLE `pdf_exp`
(
    `id`          bigint      NOT NULL,
    `filename`    varchar(50) NOT NULL,
    `payload`     longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `export_date` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci