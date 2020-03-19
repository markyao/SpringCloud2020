-- ----------------------------
-- Table structure for payment
-- ----------------------------
USE cloud2020;
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serial` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付流水号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付表' ROW_FORMAT = Dynamic;

INSERT INTO `payment` VALUES (1, '尚硅谷111');
INSERT INTO `payment` VALUES (2, 'atguigu002');
INSERT INTO `payment` VALUES (3, 'atguigu002');
INSERT INTO `payment` VALUES (4, 'atguigu002');