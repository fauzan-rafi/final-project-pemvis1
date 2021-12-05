CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `rule` enum('admin','user') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT (NOW())
);

CREATE TABLE `transactions` (
  `code` bigint PRIMARY KEY AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `cateogries_id` bigint NOT NULL,
  `type` enum('income','outcome') NOT NULL,
  `date` date NOT NULL DEFAULT (NOW()),
  `value` bigint NOT NULL,
  `description` text NOT NULL
);

CREATE TABLE `categories` (
  `id` bigint PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `type` enum('income','outcome') NOT NULL
);

ALTER TABLE `transactions` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `transactions` ADD FOREIGN KEY (`cateogries_id`) REFERENCES `categories` (`id`);
