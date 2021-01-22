CREATE DATABASE IF NOT EXISTS uybv_bid;
USE uybv_bid;
CREATE TABLE IF NOT EXISTS `member` (
  `id` int NOT NULL,
  `created_at` bigint DEFAULT NULL,
  `email` varchar(128) NOT NULL,
  `facebook` varchar(255) DEFAULT NULL,
  `last_updated_at` bigint DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gc3jmn7c2abyo3wf6syln5t2i` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `product` (
  `id` int NOT NULL,
  `created_at` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_time` int NOT NULL,
  `last_updated_at` bigint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `owner_id` int NOT NULL,
  `price_max` int DEFAULT NULL,
  `price_min` int NOT NULL,
  `price_step` int DEFAULT NULL,
  `start_time` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `media` (
  `id` int NOT NULL,
  `bid_id` int NOT NULL,
  `link` varchar(255) NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
