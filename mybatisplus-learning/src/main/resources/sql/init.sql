-- MyBatis Plus 学习示例数据库初始化脚本

-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS `mybatisplus-learning` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `mybatisplus-learning`;

-- 创建用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `email` VARCHAR(100) COMMENT '邮箱',
  `age` INT COMMENT '年龄',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` INT DEFAULT 0 COMMENT '逻辑删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 创建订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `order_no` VARCHAR(50) NOT NULL COMMENT '订单号',
  `amount` DECIMAL(10,2) NOT NULL COMMENT '订单金额',
  `status` INT DEFAULT 0 COMMENT '订单状态：0-待支付，1-已支付，2-已取消',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- 插入测试数据
INSERT INTO `user` (`username`, `email`, `age`, `deleted`) VALUES
('admin', 'admin@example.com', 30, 0),
('user1', 'user1@example.com', 25, 0),
('user2', 'user2@example.com', 28, 0),
('user3', 'user3@example.com', 32, 0);

-- 插入订单测试数据
INSERT INTO `order` (`user_id`, `order_no`, `amount`, `status`) VALUES
(1, 'ORD20240101001', 100.00, 1),
(1, 'ORD20240101002', 200.00, 0),
(2, 'ORD20240101003', 150.00, 1),
(2, 'ORD20240101004', 300.00, 1),
(3, 'ORD20240101005', 250.00, 0);

