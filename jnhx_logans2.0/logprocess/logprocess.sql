/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.100.81
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 192.168.100.81:3306
 Source Schema         : logprocess

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 21/12/2020 10:56:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for analysis_object
-- ----------------------------
DROP TABLE IF EXISTS `analysis_object`;
CREATE TABLE `analysis_object`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `object_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象名称',
  `object_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对象类型',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `is_bind` int(4) NULL DEFAULT NULL COMMENT '分析对象是否绑定 0否 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of analysis_object
-- ----------------------------
INSERT INTO `analysis_object` VALUES ('03a11322-d482-4f70-8253-0365d60c9ce5', 'lin_ana_1124_5', 'email', '', '管理员', '2020-11-12 10:37:20', '2020-11-24 17:41:32', 'lin_ana_1124_5', 1);
INSERT INTO `analysis_object` VALUES ('27e1524f-f590-4187-add4-3a5f14a1723a', 'weichai_ana_access_1119', 'email', '', '管理员', '2020-11-19 14:22:33', '2020-11-19 14:43:21', 'weichai_ana_access_1119', 1);
INSERT INTO `analysis_object` VALUES ('30f04e04-fa9a-4a5d-9ce6-2f0a795908b3', 'eYou', 'email', '', '管理员', '2020-11-21 13:58:11', NULL, 'eYou', 1);
INSERT INTO `analysis_object` VALUES ('378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_ana_1119_1', 'email', '', '管理员', '2020-11-19 11:37:34', '2020-11-19 11:47:30', 'weichai_ana_1119_1', 1);
INSERT INTO `analysis_object` VALUES ('3d9daabc-6121-4adb-8df4-3805761e6c78', 'lin_ana_1112_2', 'email', '', '管理员', '2020-11-12 10:37:36', '2020-11-12 10:38:29', 'lin_ana_1112_2', 1);
INSERT INTO `analysis_object` VALUES ('4faca7ac-9976-4151-bf2c-8a80bf307a59', 'iis', 'email', '', '管理员', '2020-11-26 17:40:45', NULL, 'iis', 1);
INSERT INTO `analysis_object` VALUES ('7e041e1f-4886-4b49-9a64-58431788c11c', 'exchange', 'email', '', '管理员', '2020-11-21 13:58:48', NULL, 'exchange', 1);
INSERT INTO `analysis_object` VALUES ('81fe2eeb-f111-4350-b9e2-918b6c780eb6', 'lin_ana_1126', 'email', '', '管理员', '2020-11-26 11:22:02', NULL, 'lin_ana_1126', 1);
INSERT INTO `analysis_object` VALUES ('9e9b18d7-2cf8-464d-ae17-0a479b92d87d', 'test_coremail_ana', 'email', '', '管理员', '2020-11-25 16:25:24', '2020-11-25 16:32:17', 'test_coremail_ana', 1);
INSERT INTO `analysis_object` VALUES ('bd632025-834b-4833-b4fa-e8a88bec778f', 'nginx', 'web', '', '管理员', '2020-09-22 16:57:07', NULL, 'nginx', 1);
INSERT INTO `analysis_object` VALUES ('ceea0874-2b89-467d-b6c7-418bc14eca81', 'coremail', 'web', '', '管理员', '2020-09-08 09:46:25', '2020-11-11 11:29:35', 'coremail', 1);
INSERT INTO `analysis_object` VALUES ('d063300e-a089-443e-a354-b50297e34a6c', 'nginx_ana', 'email', '', '管理员', '2020-11-26 17:45:00', NULL, 'nginx_ana', 1);

-- ----------------------------
-- Table structure for basic_regex
-- ----------------------------
DROP TABLE IF EXISTS `basic_regex`;
CREATE TABLE `basic_regex`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正则名称',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正则分类',
  `regex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正则表达式',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `is_enabled` int(4) NULL DEFAULT NULL COMMENT '是否启用 1启用 0未启用',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of basic_regex
-- ----------------------------
INSERT INTO `basic_regex` VALUES ('021734ff-b092-47b5-bb75-0f01d6e0fd2c', 'SPACE_DATA', '数据', '[\\S\\s]*?', '提取带空格回车等的长数据', 1, '管理员', '2020-09-08 14:34:11', '2020-09-08 14:34:11');
INSERT INTO `basic_regex` VALUES ('2b42e85e-f069-4930-88a7-d03eb4293452', 'DATE', '日期', '\\d{4}[-/]\\d{1,2}[-/]\\d{1,2}', '提取日期类数据', 1, '管理员', NULL, NULL);
INSERT INTO `basic_regex` VALUES ('2e22e4f3-c6a5-41c6-8994-ac343ec9a5a0', 'LEVEL', '等级', '[a-zA-Z0-9._-]+', '提取等级类信息', 1, '管理员', '2020-09-08 14:33:03', '2020-09-08 14:33:03');
INSERT INTO `basic_regex` VALUES ('37064c5d-beb5-467c-a469-d1e1b6d2cd45', 'ALLDATA', '数据', '.*', '贪婪型提取数据', 1, '管理员', '2020-09-08 14:34:45', '2020-09-08 14:34:45');
INSERT INTO `basic_regex` VALUES ('49dc75e5-4484-464d-a9c4-2d373f0aad34', 'DATA', '数据', '.*?', '提取数据类信息', 1, '管理员', '2020-09-08 14:33:36', '2020-09-08 14:33:36');
INSERT INTO `basic_regex` VALUES ('711972af-cbea-4075-a78e-36aa057d114a', 'TEST', '测试', '[a-zA-Z0-9._-]+', '', 1, '管理员', '2020-10-28 15:31:28', '2020-10-28 15:31:28');
INSERT INTO `basic_regex` VALUES ('9fb60b79-dd89-455d-9fb7-672dbdb1a95b', 'EMAIL', '邮箱', '^[A-Z]+$', '', 1, '管理员', '2020-10-26 14:22:52', '2020-10-26 14:22:52');
INSERT INTO `basic_regex` VALUES ('b969d221-a714-4a79-978c-6dbc4e345b3f', 'IPV4', 'IP', '(?<![0-9])(?:(?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])[.](?:[0-1]?[0-9]{1,2}|2[0-4][0-9]|25[0-5]))(?![0-9])', '提取IPV4地址', 1, '管理员', '2020-09-08 14:35:10', '2020-09-08 14:35:10');
INSERT INTO `basic_regex` VALUES ('dc246686-4cc3-4f14-afee-4de6b8b8ff11', 'PORT', '端口正则', '\\d{1,5}', '', 1, '管理员', '2020-10-28 15:21:12', '2020-10-28 15:21:12');
INSERT INTO `basic_regex` VALUES ('dd0773f9-da3e-4676-837f-281ae41aaf8c', 'TIME', '时间', '\\d{1,2}[:]\\d{1,2}[:]\\d{1,2}', '提取时间类信息', 1, '管理员', '2020-09-08 14:31:13', '2020-09-08 14:31:13');
INSERT INTO `basic_regex` VALUES ('ee2fa3b3-e838-4aa7-9276-126361c478da', 'USER', '数据', '[a-zA-Z0-9._-]+', '提取用户类信息', 1, '管理员', '2020-09-08 14:31:57', '2020-09-08 14:31:57');

-- ----------------------------
-- Table structure for built_function
-- ----------------------------
DROP TABLE IF EXISTS `built_function`;
CREATE TABLE `built_function`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内置函数名称',
  `input_params` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_params` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of built_function
-- ----------------------------
INSERT INTO `built_function` VALUES ('a645bf94-5aed-4935-b3c4-2829077d76dc', 'url:$getURL>>url', 'url=\"www.baidu.com/%E6%9D%8E%E7%99%BD\"', 'url=\"www.baidu.com/李白\"', '解url编码', NULL, '2020-12-15 21:20:04', '2020-12-15 21:20:04');
INSERT INTO `built_function` VALUES ('inner_func1', 'ip:$getGeo>>country,city', 'ip=\"123.114.46.197\"', 'country=\"china\";city=\"beijing\";ip=\"123.114.46.197\"', '从ip中解析出country(国家)和city(城市)', NULL, NULL, NULL);
INSERT INTO `built_function` VALUES ('inner_func2', 'str:$addStr(\'xxx.com\')>>str', 'str=\"liu\"', 'str=\"liu@xxx.com\"', 'str后拼接“xxx.com”', NULL, NULL, NULL);
INSERT INTO `built_function` VALUES ('inner_func3', 'mdate:$parseDate(\'%Y%m%d\')>>mdate', 'mdate=\"20201023\"', 'mdate=\"2020-10-23\"', '转换时间格式', NULL, NULL, NULL);
INSERT INTO `built_function` VALUES ('inner_func4', 'mdate:$parseDate2(\'%Y%m%d\')>>mdate', 'mdate=\"15/May/2019\"', 'mdate=\"2019-05-15\"', '转换时间格式', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `object_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分析对象id',
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频道中文名',
  `ename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频道英文名',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of channel
-- ----------------------------
INSERT INTO `channel` VALUES ('033f7d00-c941-4bff-8936-1b891240ef7d', '30f04e04-fa9a-4a5d-9ce6-2f0a795908b3', 'eyou_channel', 'eyou_channel', '', '', '2020-11-26 17:51:51', '2020-11-27 14:59:16');
INSERT INTO `channel` VALUES ('25320e2f-0788-47e9-b820-e8ceb0c217ca', 'd063300e-a089-443e-a354-b50297e34a6c', 'aaa', 'aa', '', '管理员', '2020-11-27 14:59:25', NULL);
INSERT INTO `channel` VALUES ('5c24da08-4ec0-4f43-b129-e9050741ee9b', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'coremail_channel', 'coremail_channel', 'coremail数据', '管理员', '2020-09-08 14:35:54', '2020-11-27 14:52:50');
INSERT INTO `channel` VALUES ('5c4debc2-0765-4d72-b894-6c48c0766617', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weicha_channel_1119_imapsvr', 'weicha_channel_1119_imapsvr', '', '管理员', '2020-11-19 11:46:21', NULL);
INSERT INTO `channel` VALUES ('6426df7b-78ac-4087-89e8-af6b25e460c9', '3d9daabc-6121-4adb-8df4-3805761e6c78', 'aac', 'ca', '', '管理员', '2020-11-27 15:10:08', NULL);
INSERT INTO `channel` VALUES ('6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'bd632025-834b-4833-b4fa-e8a88bec778f', 'nginx', 'nginx', '', '管理员', '2020-09-22 16:57:19', '2020-11-04 09:32:08');
INSERT INTO `channel` VALUES ('8257319f-5be0-4da7-8c4d-b18ffb2a0e25', '4faca7ac-9976-4151-bf2c-8a80bf307a59', 'iis_channel', 'iis_channel', '', '', '2020-11-26 17:41:18', NULL);
INSERT INTO `channel` VALUES ('8d1df46f-9356-416a-a182-28154e3c02b5', '7e041e1f-4886-4b49-9a64-58431788c11c', 'exchange_channel', 'exchange_channel', '', '', '2020-11-26 17:24:57', NULL);
INSERT INTO `channel` VALUES ('c0e25be4-5860-4dd2-9d66-2026788c7cc7', '9e9b18d7-2cf8-464d-ae17-0a479b92d87d', 'test_coremail_channel', 'test_coremail_channel', '', '', '2020-11-25 16:32:01', NULL);
INSERT INTO `channel` VALUES ('c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', '27e1524f-f590-4187-add4-3a5f14a1723a', 'weichai_access_channel_1119', 'weichai_access_channel_1119', '', '', '2020-11-19 14:42:58', NULL);
INSERT INTO `channel` VALUES ('c499adfe-5951-4189-a796-39bcbefcf15d', '03a11322-d482-4f70-8253-0365d60c9ce5', 'channel_test_1116', 'channel_test_1116', '', '管理员', '2020-11-13 14:21:29', '2020-11-17 10:11:25');
INSERT INTO `channel` VALUES ('d19d1acd-3b88-4e95-adbc-d461793772f8', '81fe2eeb-f111-4350-b9e2-918b6c780eb6', 'lin_channel_1126', 'lin_channel_1126', '', '', '2020-11-26 11:24:48', NULL);

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `is_enabled` int(4) NULL DEFAULT NULL,
  `is_deleted` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for field
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `channel_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频道id',
  `ename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段中文名',
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段英文名',
  `component_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件类型',
  `findex` int(255) NULL DEFAULT NULL COMMENT '字段排序',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段数据类型',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_enabled` int(4) NULL DEFAULT NULL COMMENT '是否启用',
  `category` int(4) NULL DEFAULT NULL COMMENT '0 是string/int 1是日期 2是多值',
  `is_search` int(4) NULL DEFAULT NULL COMMENT '是否检索 0否 1是',
  `search_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检索类型',
  `default_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '默认值',
  `is_more_value` int(255) NULL DEFAULT NULL COMMENT '是否多值',
  `is_front_component` int(255) NULL DEFAULT NULL COMMENT '是否开启前置',
  `rules` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字段规则',
  `options` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉框字段配置',
  `select_component_data_type` int(255) NULL DEFAULT NULL COMMENT '下拉组件配置选择是手动输入1 还是接口api 0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES ('00145f51-c92d-4b8c-b79c-055e7b32a787', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'time', '时间', 'input', 1, 'string', '管理员', '2020-10-20 14:23:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('0116fd17-d852-403d-a0e3-0d75aa25dd82', '6b348582-ff3a-4312-b4fe-b6feac129842', 'referer', '引用', 'input', 10, 'string', '管理员', '2020-10-27 11:45:47', '2020-10-28 11:50:31', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('020fab4d-7803-40f2-8155-a1d490295aff', '033f7d00-c941-4bff-8936-1b891240ef7d', 'time', '时间', 'date', 1, 'string', '管理员', '2020-09-16 15:13:27', '2020-10-26 17:04:37', 1, 1, 0, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('0223c50f-e895-481c-9937-0a5c4821f345', '8d1df46f-9356-416a-a182-28154e3c02b5', 'realuser', '真实用户', 'input', 5, 'string', '管理员', '2020-11-25 16:43:13', '2020-11-26 17:32:21', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('05b5acd0-d3b3-4240-bf71-1bfa65b72c0f', '44b17190-bce7-4a9a-bf99-8363164523d9', 'mdate', '日期', 'daterange', 11, 'string', '管理员', '2020-10-26 17:04:26', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('065d3287-db23-46d9-96ca-29620a5c2dd9', '033f7d00-c941-4bff-8936-1b891240ef7d', 'mdate', '日期', 'daterange', 0, 'string', '管理员', '2020-10-26 17:04:26', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('0a9de64b-7e01-4622-b1d3-8d25766e4070', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'result_var', '返回值', 'input', 5, 'string', '管理员', '2020-11-25 16:43:13', '2020-11-26 14:49:52', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('0accc166-e431-4b92-bcca-fcf3faf99fba', '8d1df46f-9356-416a-a182-28154e3c02b5', 'log_status', '登录状态', 'input', 7, 'string', '管理员', '2020-11-25 16:11:38', '2020-11-26 17:33:02', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('0c2ca401-19eb-4013-82bf-b403f3b700ef', '033f7d00-c941-4bff-8936-1b891240ef7d', 'to_real_acct_name', 'to_real_acct_name', 'input', 2, 'string', '管理员', '2020-09-16 15:12:57', '2020-11-27 14:36:38', 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('119342e5-b573-47e6-aa0e-eecd82781d1b', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'source_log', '原始日志', 'input', 0, 'string', '管理员', '2020-11-26 17:34:15', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('11ac95c5-ae97-4bfe-bd33-04c0629eb92b', '8d1df46f-9356-416a-a182-28154e3c02b5', 'city', '城市', 'm-select', 9, 'string', '管理员', '2020-09-17 15:47:59', '2020-10-26 17:53:34', 1, 2, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('1313e758-935e-4566-960c-e78177369a75', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'user_agent', 'user_agent', 'input', 11, 'string', '管理员', '2020-11-26 17:43:56', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('131d551c-933e-49be-9f90-66b26ea086fc', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'mdate', '日期', 'day', 0, 'string', '管理员', '2020-11-19 11:48:02', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('1457e0aa-f41e-480d-b242-061a725911c7', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'req_ip', '请求ip', 'input', 1, 'string', '管理员', '2020-11-02 16:25:31', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('1544bc18-5475-4b57-ac86-ca42b148fc5a', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'city', '城市', 'm-select', 5, 'string', '管理员', '2020-11-24 16:32:07', NULL, 1, NULL, 1, 'term', '', 1, 1, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('17044ce8-b392-4e79-b0dc-eb14dc0247cf', '2294da7a-b607-4632-89b1-2e94b253482b', 'type', '协议', 'input', 7, 'string', '管理员', '2020-11-11 10:16:11', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('1858f35a-787f-4d4b-bc2b-b03f63c7c841', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'mdate', '日期', 'daterange', 2, 'string', '管理员', '2020-10-27 17:44:34', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('19d71c7d-289b-4884-8934-c68aec5c5ad3', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'req_ip', '请求ip', 'input', 1, 'string', '管理员', '2020-11-02 16:25:31', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('1a316d59-cc3d-4cb4-a464-c8344daf5e0b', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'time', '时间', 'daterange', 1, 'string', '管理员', '2020-11-19 11:48:14', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('1a7adcd4-ac7e-472a-8339-5aabe9552a4a', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'city', '城市', 'm-select', 5, 'string', '管理员', '2020-10-21 09:37:02', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('1cb703ca-a970-4e13-9136-7ec319da9461', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'source_log', '原始日志', 'input', 7, 'string', '管理员', '2020-11-24 16:26:57', '2020-11-25 09:39:33', 1, NULL, 1, 'fuzzy', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('1d2529a0-4c99-4059-b32e-779b8403e3c9', '6b348582-ff3a-4312-b4fe-b6feac129842', 'country', '国家', 'm-select', 15, 'string', '管理员', '2020-10-28 10:41:11', '2020-10-29 15:30:46', 1, NULL, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('1d9ecde3-a9b6-4553-9453-ac7a04b50521', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'time', '时间', 'date', 2, 'string', '管理员', '2020-09-16 15:13:27', '2020-10-26 17:04:37', 1, 1, 0, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('1f0059a1-702b-4806-a5e1-50aaeb92f40a', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'time', '时间', NULL, NULL, 'string', '管理员', '2020-09-22 17:25:56', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('1f4d293e-6c73-48a8-afb7-50ff191caa57', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'mdate', '日期', 'day', 2, 'string', '管理员', '2020-11-02 16:26:08', '2020-11-04 16:45:03', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('20422535-8f97-4a4c-b5d1-636ed14a79cf', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'req_ip', '请求ip', 'input', 1, 'string', '管理员', '2020-11-02 16:25:31', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('211b1745-4343-44cd-bfd7-e7a28da3500b', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'to_size', 'to_size', 'input', 15, 'string', '管理员', '2020-11-26 17:44:20', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('215ea282-1ca2-4df3-a551-c0d58ea19268', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'ip', '请求ip', 'input', 3, 'string', '管理员', '2020-11-19 11:49:04', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('22fe6a23-a31b-481a-8bfd-cdee0bb3b9ed', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'result', '操作结果', NULL, NULL, 'string', '管理员', '2020-09-22 17:25:16', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('23a8d573-3117-4950-85c3-2434cf1da8fe', '44b17190-bce7-4a9a-bf99-8363164523d9', 'fulltime', '完整时间', 'daterange', 5, 'string', '管理员', '2020-09-16 15:13:48', '2020-10-26 17:39:01', 1, 1, 1, 'term', '', 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('23f7eada-752d-4716-bb28-5f445621e8d5', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'country', '国家', 'm-select', 5, 'string', '管理员', '2020-09-17 15:47:41', '2020-10-27 09:08:17', 1, 2, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('243cd4d7-1772-4cb6-9d3e-8cef7c54f194', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'username', '用户名', 'input', 2, 'string', '管理员', '2020-10-21 09:36:13', '2020-10-23 11:13:05', 1, NULL, 1, 'term', '', 1, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('2475510e-e913-4e56-ac9b-3156c70dc128', '033f7d00-c941-4bff-8936-1b891240ef7d', 'from_email', 'from_email', 'input', 13, 'string', '管理员', '2020-11-26 17:57:15', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('2521fe74-8942-47c4-b4c0-120442816bd6', '033f7d00-c941-4bff-8936-1b891240ef7d', 'from_digest', 'from_digest', 'input', 4, 'string', '管理员', '2020-11-25 16:43:13', '2020-11-27 14:36:47', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('26f31e4c-66b0-4ff7-b2e0-5eaac025e200', '033f7d00-c941-4bff-8936-1b891240ef7d', 'to_size', 'to_size', 'input', 22, 'string', '管理员', '2020-11-27 14:44:58', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('26f8348d-567f-492a-8d1d-3775705beb8c', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'username', 'username', 'input', 2, 'string', '管理员', '2020-11-24 16:31:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('274b9457-54c4-4f7c-beab-0b7bc138c99f', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'username', '用户名', NULL, NULL, 'string', '管理员', '2020-09-22 17:24:47', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('27f3af7a-e7a9-4042-a75d-efea137d7742', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'time', 'time', 'daterange', 1, 'string', '管理员', '2020-11-17 10:13:32', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('292f0ac9-4247-4848-944b-6d185d879217', '44b17190-bce7-4a9a-bf99-8363164523d9', 'asfasf', '1', 'input', 12, 'string', '管理员', '2020-11-13 09:42:36', '2020-11-13 09:44:59', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('2b98854d-0c1a-475a-8011-f224e3388643', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'req_ip', '请求ip', 'input', 3, 'string', '管理员', '2020-10-21 09:36:28', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('2cfc7696-52b1-42fe-b71c-a5f2d6a17c1a', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'city', '城市', 'm-select', 7, 'string', '管理员', '2020-11-02 16:27:17', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('2fdeb2db-ca20-4016-9bd3-c080b055b9db', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'url_content', '请求内容', 'input', 9, 'string', '管理员', '2020-11-19 14:44:04', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('30021180-e4ac-4dc0-ac44-8b28d3b9c78b', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'response', 'response', 'input', 6, 'string', '管理员', '2020-11-25 16:10:24', '2020-11-26 17:43:29', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('315bc08e-a4ca-49d5-b078-dc66cf45ec41', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'req_ip', '请求ip', 'input', 3, 'string', '管理员', '2020-10-20 14:23:31', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('31f2d688-2b22-4e76-becd-8e561dc5f3ff', '033f7d00-c941-4bff-8936-1b891240ef7d', 'source_log', '原始日志', 'input', 17, 'string', '管理员', '2020-11-26 17:34:15', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('336aab3f-afd5-42c3-ae3c-3392d195d1ce', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'status', 'status', 'input', 12, 'string', '管理员', '2020-11-26 17:44:01', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('34e90574-e68f-418a-a4a7-20e582e005fc', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'opt', '操作类型', 'input', 7, 'string', '管理员', '2020-10-27 17:46:19', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('3586c5dc-cb77-4e17-8671-676b9dbacca8', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'time', 'time', 'daterange', 1, 'string', '管理员', '2020-11-17 10:13:32', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('370e30ed-8dfd-4a99-b82a-e0b92f70aff6', '5c4debc2-0765-4d72-b894-6c48c0766617', 'mdate', '日期', 'day', 0, 'string', '管理员', '2020-11-19 11:48:02', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('390e5027-a120-4411-89a9-9b0611480f54', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'mdate', '日期', 'day', 2, 'string', '管理员', '2020-11-02 16:26:08', '2020-11-04 16:45:03', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('39600c08-8466-46b6-9013-a96880a8e4d3', '6b348582-ff3a-4312-b4fe-b6feac129842', 'opt', '操作类型', 'input', 17, 'string', '管理员', '2020-10-28 10:46:23', '2020-10-28 11:50:04', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('3a274ff2-5e28-4553-ae91-15e58f166db9', '6b348582-ff3a-4312-b4fe-b6feac129842', 'username', '用户名', 'input', 7, 'string', '管理员', '2020-10-27 11:44:33', '2020-10-29 15:25:08', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('3a76749b-5633-446c-bc34-9ede18ec1e03', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'username', '用户名', 'input', 0, 'string', '管理员', '2020-11-02 16:25:19', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('3c9336ec-ee3b-4471-a6dd-79a5863e69f8', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'mdate', '日期', 'daterange', 1, 'string', '管理员', '2020-10-26 17:04:26', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('3dc673a5-1fed-4d01-91ea-90eaf48ecc5e', '033f7d00-c941-4bff-8936-1b891240ef7d', 'country', '国家', 'm-select', 7, 'string', '管理员', '2020-09-17 15:47:41', '2020-10-27 09:08:17', 1, 2, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('3e2ee93e-aa60-44fa-98ff-a0bf5ea97394', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'func', 'func', 'input', 17, 'string', '管理员', '2020-11-25 16:11:49', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('3e4f134e-968a-42c6-ad92-761141b1247d', '6b348582-ff3a-4312-b4fe-b6feac129842', 'mdate', '日期', 'daterange', 0, 'string', '管理员', '2020-10-27 11:42:18', '2020-10-27 16:04:07', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('3f6b99b6-533e-4dbc-bfb5-ed2393be1085', '6b348582-ff3a-4312-b4fe-b6feac129842', 's_port', '端口', 'input', 6, 'string', '管理员', '2020-10-27 11:44:18', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('40cfaeb3-b479-43eb-897e-3212fa2b74fc', '033f7d00-c941-4bff-8936-1b891240ef7d', 'city', '城市', 'm-select', 8, 'string', '管理员', '2020-09-17 15:47:59', '2020-10-26 17:53:34', 1, 2, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('42e61f59-5f37-462f-b613-8ef249cb38d2', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'city', '城市', 'm-select', 9, 'string', '管理员', '2020-09-17 15:47:59', '2020-10-26 17:53:34', 1, 2, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('42f47559-9599-48cf-8498-9bbf0f648051', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'country', '国家', 'm-select', 8, 'string', '管理员', '2020-11-02 16:27:34', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('438f0510-f033-4ac2-9d65-a018e6c9234b', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'uid', 'uid', 'input', 15, 'string', '管理员', '2020-11-25 16:11:30', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('45052097-8e32-4f95-8dde-36e8471f6b62', '033f7d00-c941-4bff-8936-1b891240ef7d', 'to_digest', 'to_digest', 'input', 5, 'string', '管理员', '2020-11-25 16:10:24', '2020-11-27 14:36:52', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('459f0a5c-ffc8-4f41-b7df-5c3ce7c94976', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'city', '城市', 'm-select', 10, 'string', '管理员', '2020-09-17 15:47:59', '2020-10-26 17:53:34', 1, 2, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('474fe33d-100a-4b8d-9d92-5bcec17f42b4', '033f7d00-c941-4bff-8936-1b891240ef7d', 'umod', 'umod', 'input', 15, 'string', '管理员', '2020-11-26 17:59:03', '2020-11-27 14:38:03', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('4ac77691-937c-4a09-a7a7-ca2e1b44a40f', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'mdate', '日期', 'day', 2, 'string', '管理员', '2020-11-02 16:26:08', '2020-11-04 16:45:03', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('4baadf95-6565-4781-b4b4-71b28d215155', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'result', '操作结果', 'input', 3, 'string', '管理员', '2020-09-16 15:12:57', NULL, 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('4e42634c-fb0a-4caa-a101-27d47a6d5614', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'fulltime', '完整时间', 'daterange', 5, 'string', '管理员', '2020-09-16 15:13:48', '2020-10-26 17:39:01', 1, 1, 1, 'term', '', 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('4e4b4323-c4cc-4073-b99f-4209eb2d55df', '8d1df46f-9356-416a-a182-28154e3c02b5', 'country', '国家', 'm-select', 8, 'string', '管理员', '2020-09-17 15:47:41', '2020-10-27 09:08:17', 1, 2, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('50cbe6b5-735b-4f10-a7b3-9221cf4edebb', '2294da7a-b607-4632-89b1-2e94b253482b', 'mdate', '日期', 'day', 0, 'string', '管理员', '2020-11-11 10:13:39', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('5140f121-0738-4afb-9b71-6a152a399280', '033f7d00-c941-4bff-8936-1b891240ef7d', 'client_ip', 'client_ip', 'input', 10, 'string', '管理员', '2020-11-26 17:54:00', '2020-11-27 14:37:19', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('52c87bc1-a2af-454b-9823-29521f171bc8', '8d1df46f-9356-416a-a182-28154e3c02b5', 'time', '时间', 'date', 2, 'string', '管理员', '2020-09-16 15:13:27', '2020-10-26 17:04:37', 1, 1, 0, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('55621953-436b-4f1a-b8d6-544f9c7ea619', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'city', '城市', 'm-select', 7, 'string', '管理员', '2020-11-02 16:27:17', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('5578fff1-1ed9-4879-a5a7-a95b71635028', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'type', 'type', 'input', 4, 'string', '管理员', '2020-11-25 16:11:30', '2020-11-26 17:43:09', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('5782f1cc-7014-4b3c-8e5a-1bf25e94e113', '5c4debc2-0765-4d72-b894-6c48c0766617', 'opt', '操作类型', 'input', 8, 'string', '管理员', '2020-11-19 11:50:50', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('593ae429-5ca6-4b20-a111-8736c0d64bc2', '2294da7a-b607-4632-89b1-2e94b253482b', 'req_ip', '请求ip', 'input', 3, 'string', '管理员', '2020-11-11 10:14:38', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('599fb261-2c94-4d7b-ae54-0b310ff27139', '6b348582-ff3a-4312-b4fe-b6feac129842', 'cs_method', '请求方法', 'input', 3, 'string', '管理员', '2020-10-27 11:43:30', '2020-10-27 16:34:39', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('5a0a3f77-b4d5-4596-82e3-cb0b1644b534', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'opt', '操作类型', 'input', 5, 'string', '管理员', '2020-11-02 16:26:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('5ba30c31-2353-4dfa-a321-9e1b3bd14651', '44b17190-bce7-4a9a-bf99-8363164523d9', 'username', '用户名', 'input', 2, 'string', '管理员', '2020-09-16 15:11:12', NULL, 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('5c3759f4-d1e1-4703-8614-048f283e7edd', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'time', '时间', 'date', 6, 'string', '管理员', '2020-09-16 15:13:27', '2020-10-26 17:04:37', 1, 1, 0, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('5c6752c4-6834-43c7-a7d9-edc5601ca002', '6b348582-ff3a-4312-b4fe-b6feac129842', 'cs_uri_stem', '客户端stem', 'input', 4, 'string', '管理员', '2020-10-27 11:43:51', '2020-10-27 16:39:25', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('5d1ac39c-a46e-42fc-a56e-188dd934d3f3', '5c4debc2-0765-4d72-b894-6c48c0766617', 'time', '时间', 'daterange', 1, 'string', '管理员', '2020-11-19 11:48:14', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('5f373453-57fc-4b9a-8ea4-b382cb811b6f', '2294da7a-b607-4632-89b1-2e94b253482b', 'opt', '操作类型', 'input', 6, 'string', '管理员', '2020-11-11 10:15:58', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('5f686a65-f170-487f-86aa-0b6122faf55b', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'result', '操作结果', 'input', 6, 'string', '管理员', '2020-11-02 16:26:57', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('6088b0ac-1a84-42f7-b95c-d1f131d90f7a', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'time', '时间', 'daterange', 3, 'string', '管理员', '2020-11-02 16:26:18', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('61430ce5-f89c-49be-98df-317575dab558', '6b348582-ff3a-4312-b4fe-b6feac129842', 'sc_substatus', '监控状态', 'input', 12, 'string', '管理员', '2020-10-27 11:46:03', '2020-10-28 10:35:50', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('631e62d6-9c44-4dda-9835-55db2929402a', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'result', '操作结果', 'input', 6, 'string', '管理员', '2020-10-27 17:46:05', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('636d70b9-7759-4e1c-bc1a-26423e4c401b', '033f7d00-c941-4bff-8936-1b891240ef7d', 'ctstr', 'ctstr', 'input', 14, 'string', '管理员', '2020-11-26 17:58:57', '2020-11-27 14:37:57', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('653b5a96-29c3-4cf1-ae54-97a3bd176ff6', '6b348582-ff3a-4312-b4fe-b6feac129842', 's_ip', '服务端ip', 'input', 2, 'string', '管理员', '2020-10-27 11:43:11', '2020-10-27 16:39:07', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('661e3089-61d8-4837-9ce3-b37cfedfec5e', '033f7d00-c941-4bff-8936-1b891240ef7d', 'auth_type', 'auth_type', 'input', 11, 'string', '管理员', '2020-11-26 17:55:37', '2020-11-27 14:37:46', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('68556d4c-09a2-40c4-b8b2-845aa6e55744', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'country', '国家', 'm-select', 8, 'string', '管理员', '2020-11-02 16:27:34', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('68c2e5ec-46ed-420a-88e6-5880cb91a364', '6b348582-ff3a-4312-b4fe-b6feac129842', 'user_agent', '代理用户', 'input', 9, 'string', '管理员', '2020-10-27 11:45:41', '2020-10-28 11:50:37', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('69ee0eea-7e81-460f-a08c-1e5e5c61ecb5', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'remote', '源地址', 'input', 3, 'string', '管理员', '2020-11-25 16:10:24', '2020-11-26 13:09:50', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('6b0d1dd4-1109-4acf-9627-52a33c61b84e', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'mdate', '日期', 'daterange', 11, 'string', '管理员', '2020-10-26 17:04:26', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('6be78acd-24d0-484c-9a4f-c0b0ef56af30', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'opt', '操作类型', 'input', 8, 'string', '管理员', '2020-11-19 11:50:50', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('6ed0e3b3-8871-4a8c-86fd-444715974072', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'opt', '操作类型', 'select', 6, 'string', '管理员', '2020-10-21 09:37:14', '2020-10-21 10:01:01', 1, NULL, 1, 'term', '', 0, 0, 'no', '[{\"value\":\"login\",\"label\":\"login\"}]', 1);
INSERT INTO `field` VALUES ('7010f427-5019-4923-aca2-2a369c15c1c4', '033f7d00-c941-4bff-8936-1b891240ef7d', 'module_type', 'module_type', 'input', 9, 'string', '管理员', '2020-11-26 17:53:53', '2020-11-27 14:37:12', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('71119153-c9a9-48cb-b378-0e7a7bf7fbcf', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'result', '是否成功', 'input', 7, 'string', '管理员', '2020-09-16 15:12:57', '2020-11-26 14:48:49', 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('7120a219-5181-4906-99b6-f4da7acd5b73', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'action_type', 'action_type', 'input', 13, 'string', '管理员', '2020-11-25 16:11:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('72beab62-f19e-468a-bf46-3dd77a88ddde', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'country', '国家', 'm-select', 4, 'string', '管理员', '2020-10-27 17:45:10', '2020-10-27 18:04:55', 1, NULL, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('72c9ba5e-4fdc-41d2-8b39-f66f9dff4077', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'country', '国家', 'm-select', 4, 'string', '管理员', '2020-10-20 14:28:01', '2020-10-26 10:29:05', 1, NULL, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('738796f5-c68d-456c-afed-c377d61c69b0', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'client', '客户端', 'input', 9, 'string', '管理员', '2020-11-11 09:13:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('757e5e3a-8337-4757-9d57-f336001bda6e', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'result', '操作结果', 'input', 6, 'string', '管理员', '2020-11-02 16:26:57', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('76cf20cb-b6df-4f68-bdbd-bb12dd82af42', '2294da7a-b607-4632-89b1-2e94b253482b', 'country', '国家', 'select', 4, 'string', '管理员', '2020-11-11 10:15:22', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('789ecffa-87dc-4108-922e-83574a5e8a1e', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'result', '操作结果', 'input', 6, 'string', '管理员', '2020-11-02 16:26:57', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('78ee03a5-a4ca-4102-a79a-38db4e748eb6', '2294da7a-b607-4632-89b1-2e94b253482b', 'username', '用户名', 'input', 2, 'string', '管理员', '2020-11-11 10:14:27', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('78f7d5a1-7bf7-442d-b54a-2290968567e9', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'req_url', '请求url', NULL, NULL, 'string', '管理员', '2020-09-22 17:23:49', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('790db423-8644-4557-a626-1e28c0e407ff', '8d1df46f-9356-416a-a182-28154e3c02b5', 'domain', 'domain', 'input', 3, 'string', '管理员', '2020-09-16 15:12:57', '2020-11-26 17:32:46', 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('79bc3ae9-dacd-48a8-95a0-599f15dcfebd', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'country', '国家', NULL, NULL, 'string', '管理员', '2020-09-22 17:26:41', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('7ad57a09-64a4-4685-b502-4c9f973ffd31', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'time', '时间', 'daterange', 1, 'string', '管理员', '2020-10-21 09:35:50', '2020-10-21 10:10:10', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('7b226c4d-4b4b-44b0-95ae-1080d7eac491', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'page', 'page', 'input', 16, 'string', '管理员', '2020-11-25 16:11:38', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('7d38c2eb-7e0a-4239-8e6f-3bb396258483', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'city', '城市', 'm-select', 9, 'string', '管理员', '2020-09-17 15:47:59', '2020-10-26 17:53:34', 1, 2, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('7dd8a4b0-400e-4394-9c0b-84977fbb629a', '5c4debc2-0765-4d72-b894-6c48c0766617', 'source_log', '原始日志', 'input', 9, 'string', '管理员', '2020-11-24 10:37:00', '2020-11-25 09:27:05', 1, NULL, 1, 'fuzzy', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('7ed4177d-6c22-492f-9212-0996967d1faa', '5c4debc2-0765-4d72-b894-6c48c0766617', 'type', '协议', 'input', 7, 'string', '管理员', '2020-11-19 11:50:33', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('805afd44-773f-40c5-b674-f9e5d74cd1bf', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'opt', '操作类型', 'input', 5, 'string', '管理员', '2020-11-02 16:26:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('809d3ede-8b21-44e5-a6e5-d38fb20c22a3', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'username', '用户名', 'input', 0, 'string', '管理员', '2020-11-02 16:25:19', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('81d65794-0dcf-40d6-ad26-fa9d29f702cb', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'req_ip', 'req_ip', 'input', 3, 'string', '管理员', '2020-11-24 16:31:24', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('84118ab8-b220-4b50-ac44-af61e9f0edd6', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'mdate', '日期', 'day', 2, 'string', '管理员', '2020-11-02 16:26:08', '2020-11-04 16:45:03', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('85a6c90b-6071-4880-a38f-f5d6f5d597d8', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'username', '用户名', 'input', 2, 'string', '管理员', '2020-11-19 11:48:28', '2020-11-24 11:30:14', 1, NULL, 1, 'term', '', 1, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('86a02029-97df-48cb-becd-b036eb33c572', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'source_log', '原日志', 'input', 14, 'string', '管理员', '2020-11-26 08:57:39', '2020-11-26 13:11:31', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('86ca6236-f231-4e9f-b68e-aa4cc3f7070a', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'fulltime', '完整时间', NULL, NULL, 'string', '管理员', '2020-09-22 17:26:08', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('8a656734-9f43-4589-87a5-089e2dc385d2', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'time_zone', '区号', 'input', 4, 'string', '管理员', '2020-11-02 16:26:35', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('8aa5dbdb-85d2-4c0e-99a1-c1b47234c29b', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'type', '协议', 'input', 7, 'string', '管理员', '2020-11-19 11:50:33', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('8ab0ba47-b282-4d09-93f7-02a3c9d854ad', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'mdate', '日期', 'daterange', 0, 'string', '管理员', '2020-10-26 17:04:26', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('8dff2127-f3c5-42b7-90ef-13b3e2d3f8dc', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'country', '国家', 'select', 4, 'string', '管理员', '2020-11-24 16:31:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('8e552020-dd37-4a46-871a-a0b1f20f28d5', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'username', '用户名', 'input', 2, 'string', '管理员', '2020-09-16 15:11:12', NULL, 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('8eb9d4b7-18c9-4bb0-be2f-e98a3d325aad', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'req_ip', 'req_ip', 'input', 3, 'string', '管理员', '2020-11-24 16:31:24', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('8eca5165-a64c-4dd7-b15e-4089ff7b10fa', '6b348582-ff3a-4312-b4fe-b6feac129842', 'sc_status', '状态', 'input', 11, 'string', '管理员', '2020-10-27 11:45:55', '2020-10-27 16:53:12', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('902b47e8-257e-44f3-adbb-f767d62df6ac', '5c4debc2-0765-4d72-b894-6c48c0766617', 'result', '结果', 'input', 6, 'string', '管理员', '2020-11-19 11:50:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('90b889cc-4b6b-41b6-b06f-00d56f9b0a14', '033f7d00-c941-4bff-8936-1b891240ef7d', 'useragent', 'useragent', 'input', 23, 'string', '管理员', '2020-11-27 14:45:08', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('918009f4-7942-4db4-b363-2b1d62936025', '5c4debc2-0765-4d72-b894-6c48c0766617', 'city', '城市', 'select', 5, 'string', '管理员', '2020-11-19 11:49:40', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('91895419-60f5-4a12-bd8b-d880c649b574', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'time', '时间', 'daterange', 3, 'string', '管理员', '2020-11-02 16:26:18', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('929c2070-88b4-4540-8d1c-60dcf8678a7c', '8d1df46f-9356-416a-a182-28154e3c02b5', 'ip', 'ip', 'input', 6, 'string', '管理员', '2020-11-25 16:10:24', '2020-11-26 17:31:43', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('93c4ed54-118d-46ab-a138-e692fb3a42ae', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'action_target', 'action_target', 'input', 14, 'string', '管理员', '2020-11-25 16:11:17', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('9bfe4907-c8cf-43e2-918f-1f75e6504f2c', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'req_ip', '请求ip', NULL, NULL, 'string', '管理员', '2020-09-22 16:58:01', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('9d30ec4e-118d-476f-a3f1-73fe6ce3ff62', '44b17190-bce7-4a9a-bf99-8363164523d9', 'opt', '操作类型', 'input', 1, 'string', '管理员', '2020-09-16 15:12:25', NULL, 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('9d97d872-743e-4502-96d3-dfc263a3948d', '6b348582-ff3a-4312-b4fe-b6feac129842', 'cs_uri_query', '客户端查询参数', 'input', 5, 'string', '管理员', '2020-10-27 11:44:09', '2020-10-28 14:55:22', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('9d9dc216-7da7-40ac-9086-b16a5853aa4e', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'opt', '操作类型', 'input', 5, 'string', '管理员', '2020-11-02 16:26:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('9f920e9d-b2ab-4073-9d22-b5fd45455362', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'opt', 'opt', 'input', 6, 'string', '管理员', '2020-11-24 16:55:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('a02189bf-4e21-4781-8d62-ce5f46064464', '033f7d00-c941-4bff-8936-1b891240ef7d', 'ip', 'ip', 'input', 19, 'string', '管理员', '2020-11-27 14:43:16', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('a3476a21-4b1f-4694-9ee5-44174fe92055', '6b348582-ff3a-4312-b4fe-b6feac129842', 'result', '操作结果', 'input', 18, 'string', '管理员', '2020-10-28 10:46:36', '2020-10-28 14:21:39', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('a4fc7308-3a3f-4521-9762-43d50dfa33fd', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'result', '操作结果', 'input', 7, 'string', '管理员', '2020-10-21 09:37:27', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('a6f6a0f0-db76-40e4-993d-36c152a7fee7', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'country', '国家', 'm-select', 4, 'string', '管理员', '2020-11-19 11:49:23', '2020-11-24 16:38:53', 1, NULL, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('a7b862e1-905b-474c-849f-c6e3522bc9d0', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'mdate', 'mdate', 'day', 0, 'string', '管理员', '2020-11-17 10:13:22', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('a7fc7664-9aab-4e82-bb81-2eb84181eca6', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'city', '城市', 'select', 5, 'string', '管理员', '2020-11-19 11:49:40', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('a9a556aa-daf6-48ef-9df6-6a6ca9e91380', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'uid', 'uid', 'input', 7, 'string', '管理员', '2020-11-25 16:11:38', '2020-11-26 17:43:40', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('a9fb4a41-f5ff-4a1d-9875-c4a870770ffe', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'mdate', '日期', 'daterange', 0, 'string', '管理员', '2020-10-20 14:22:59', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ac6dc5d8-cab7-4d81-b64a-9559ba91b221', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'status', '状态', 'input', 10, 'string', '管理员', '2020-11-11 09:13:19', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('adb5c913-99ab-45a2-b482-6911da91fbdf', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'country', '国家', 'm-select', 4, 'string', '管理员', '2020-10-21 09:36:46', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('ae0fb60c-efba-4c03-b09d-0d642ddb6209', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'client_ip', 'client_ip', 'input', 10, 'string', '管理员', '2020-11-26 17:43:50', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('af3667da-190d-467d-9d09-7d95a0177af8', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'server_ip', 'server_ip', 'input', 3, 'string', '管理员', '2020-09-16 15:12:57', '2020-11-26 17:43:00', 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('afc515d2-8838-4f12-946a-eee4dab3d8bc', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'time', '时间', 'daterange', 3, 'string', '管理员', '2020-10-27 17:44:44', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('b02627c6-f4cf-415b-92c8-d89a72b5001c', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'username', 'username', 'input', 2, 'string', '管理员', '2020-11-24 16:31:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('b0e58481-6ce1-457e-a92f-caaabfd28e92', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'city', '城市', NULL, NULL, 'string', '管理员', '2020-09-22 17:26:51', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('b11f59f6-6f2f-4bea-bb18-a3a347d157ee', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'req_ip', '请求ip', 'input', 1, 'string', '管理员', '2020-11-02 16:25:31', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('b23d5989-dfab-41a4-b968-9dae8a32f288', '3e17b4ae-22ba-4f9e-8bde-19020bb8265f', 'mdate', '日期', 'daterange', 0, 'string', '管理员', '2020-10-21 09:35:38', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('b2410f67-672a-4cdb-8113-7e81bf7f6a5b', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'opt', '操作类型', 'input', 1, 'string', '管理员', '2020-09-16 15:12:25', NULL, 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('b323825d-2ad8-4b77-8b0b-6fb3dd075a71', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'username', '用户名', 'input', 0, 'string', '管理员', '2020-11-02 16:25:19', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('b3a349af-cd76-4db6-8a03-11339eaed75a', '44b17190-bce7-4a9a-bf99-8363164523d9', 'time', '时间', 'date', 6, 'string', '管理员', '2020-09-16 15:13:27', '2020-10-26 17:04:37', 1, 1, 0, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('b403999b-9797-45c0-9fba-99dcb8a75a56', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'func', '操作', 'input', 2, 'string', '管理员', '2020-11-25 16:11:49', '2020-11-26 13:09:43', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('b80d3463-fa72-4401-9524-6f9269e20fb7', '033f7d00-c941-4bff-8936-1b891240ef7d', 'subject_digest', 'subject_digest', 'input', 6, 'string', '管理员', '2020-11-25 16:11:38', '2020-11-27 14:36:57', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('bd0436d5-e15a-47ae-9747-066089d9d59c', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'country', '国家', 'select', 4, 'string', '管理员', '2020-11-24 16:31:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('be8beff6-14b0-42cb-ace1-9f9c4033427f', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'remote111', '请求ip', 'input', 0, 'string', '管理员', '2020-09-16 15:10:58', '2020-11-27 14:53:16', 1, 0, 1, 'fuzzy', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('bf37658c-0b46-43dd-a6f9-80939a6fbb0d', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'opt', '操作类型', 'input', 5, 'string', '管理员', '2020-11-02 16:26:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('c07a5804-57ba-419c-9c7e-b8da46e5d352', '2294da7a-b607-4632-89b1-2e94b253482b', 'city', '城市', 'select', 5, 'string', '管理员', '2020-11-11 10:15:46', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('c0ead8a7-3b70-4d79-8a88-0cf8eace5c9d', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'city', '城市', 'm-select', 5, 'string', '管理员', '2020-11-24 16:32:07', NULL, 1, NULL, 1, 'term', '', 1, 1, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('c14fb4da-8d3f-4a57-a55c-b3cf8c12e3e7', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'req_ip', '请求ip', 'input', 0, 'string', '管理员', '2020-10-27 17:44:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('c1c6d97e-f087-4083-9568-cea8dd96382d', '033f7d00-c941-4bff-8936-1b891240ef7d', 'url', 'url', 'input', 21, 'string', '管理员', '2020-11-27 14:44:34', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('c2196b1e-3a66-4fa6-b8cc-0b4a11537b20', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'opt', '操作', NULL, NULL, 'string', '管理员', '2020-09-22 17:25:04', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('c515ca2d-91b1-4ec5-9b52-5897640ccd05', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'to_page', '操作页', 'input', 11, 'string', '管理员', '2020-11-25 16:11:38', '2020-11-27 14:07:43', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('c55697d6-3cc7-4b0c-bbbd-11afbb407c6b', '44b17190-bce7-4a9a-bf99-8363164523d9', 'req_ip', '请求ip', 'input', 0, 'string', '管理员', '2020-09-16 15:10:58', NULL, 1, 0, 1, 'fuzzy', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('c733d56d-be3e-45dc-8ae8-561228c9cd31', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'client', '客户端', 'input', 9, 'string', '管理员', '2020-11-11 09:13:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('c75da204-a25c-43bc-abae-f85173a85e9f', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'city', '城市', 'm-select', 5, 'string', '管理员', '2020-10-20 14:28:23', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('c7bfdf8c-3196-4e4f-a5e1-98703026db47', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'time_zone', '区号', 'input', 8, 'string', '管理员', '2020-10-27 17:48:22', '2020-10-27 17:59:55', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ca2f6728-8741-496c-bd36-d583d6214ccd', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'time_zone', '区号', 'input', 4, 'string', '管理员', '2020-11-02 16:26:35', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('cb08ac56-f035-4fa1-9969-fdb23c4e3a48', '6b348582-ff3a-4312-b4fe-b6feac129842', 'time_taken', 'taken时间', 'input', 14, 'string', '管理员', '2020-10-27 11:46:19', '2020-10-28 10:35:42', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ce1aa2c5-b372-47ac-a3fd-040619ef6c24', '2294da7a-b607-4632-89b1-2e94b253482b', 'time', '时间', 'daterange', 1, 'string', '管理员', '2020-11-11 10:13:52', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ced099ca-056c-4445-827f-6b837ea95c85', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'city', '城市', 'm-select', 7, 'string', '管理员', '2020-11-02 16:27:17', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('cf86d432-afb1-4217-bf80-8b4fde91ddc2', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'param1', 'param1', 'input', 13, 'string', '管理员', '2020-11-26 17:44:07', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('d00f36ec-7ec6-4d19-92d4-2fb705fb3a08', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'result', '结果', 'input', 6, 'string', '管理员', '2020-11-19 11:50:09', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('d0997927-8e6d-43b8-b30c-50b157378589', 'c1b02f01-bf32-454f-a9bc-1214c6ef0bbc', 'phone_flag', 'phone_flag', 'input', 10, 'string', '管理员', '2020-11-19 14:48:15', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('d120c8c7-23f2-4589-82dd-3dffb5caaeb3', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'opt', 'opt', 'input', 6, 'string', '管理员', '2020-11-24 16:55:48', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('d1afd020-8dc3-477a-99a2-53037fbc0e61', 'c499adfe-5951-4189-a796-39bcbefcf15d', 'source_log', '原始日志', 'input', 7, 'string', '管理员', '2020-11-24 16:26:57', '2020-11-25 09:39:33', 1, NULL, 1, 'fuzzy', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('d3937c92-409c-419d-b198-f7abd4d75691', '033f7d00-c941-4bff-8936-1b891240ef7d', 'response', 'response', 'input', 20, 'string', '管理员', '2020-11-27 14:44:07', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('d49b7659-c58f-465b-8647-c479394f9f55', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'time', '时间', 'date', 1, 'string', '管理员', '2020-09-16 15:13:27', '2020-10-26 17:04:37', 1, 1, 0, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('d69e0086-561c-4368-88b6-83f65640de2e', '5c4debc2-0765-4d72-b894-6c48c0766617', 'ip', '请求ip', 'input', 3, 'string', '管理员', '2020-11-19 11:49:04', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('d78edbd6-acfb-48f6-b49d-bd61e5837c56', '5c24da08-4ec0-4f43-b129-e9050741ee9b', 'remote', 'remote', 'input', 12, 'string', '管理员', '2020-11-25 16:10:24', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('d9473c30-e4d8-4c9b-a338-316a060763bc', '6b348582-ff3a-4312-b4fe-b6feac129842', 'city', '地区', 'm-select', 16, 'string', '管理员', '2020-10-28 10:41:34', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('da1c97b2-08c2-437b-a2cb-b18298267af8', '6b348582-ff3a-4312-b4fe-b6feac129842', 'time', '时间', 'input', 1, 'string', '管理员', '2020-10-27 11:42:42', '2020-10-27 16:04:13', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('db37b981-21ad-4679-8863-d6e52cff87c1', '8d1df46f-9356-416a-a182-28154e3c02b5', 'source_log', '原始日志', 'input', 0, 'string', '管理员', '2020-11-26 17:34:15', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('db3abfd8-c4c5-4b28-8e73-4d8ca48e81e8', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'result', '操作结果', 'input', 6, 'string', '管理员', '2020-11-02 16:26:57', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('dbc26370-7971-4269-8167-ec71f9d8a716', '6b348582-ff3a-4312-b4fe-b6feac129842', 'sc_win32_status', 'win32状态', 'input', 13, 'string', '管理员', '2020-10-27 11:46:10', '2020-10-28 10:35:45', 1, NULL, 0, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('dc42307e-30c4-40a9-ac1e-c11535808e22', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'country', '国家', 'm-select', 8, 'string', '管理员', '2020-11-02 16:27:34', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('dd2dc2bd-6113-48fc-9ec2-9edcf2426a30', 'd19d1acd-3b88-4e95-adbc-d461793772f8', 'mdate', 'mdate', 'day', 0, 'string', '管理员', '2020-11-17 10:13:22', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('de9ed29c-8bd5-4821-a1a5-24e86287961a', '5c4debc2-0765-4d72-b894-6c48c0766617', 'country', '国家', 'select', 4, 'string', '管理员', '2020-11-19 11:49:23', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('e04d1c66-dd80-4b1a-a6b8-5a6cbd017198', '6b348582-ff3a-4312-b4fe-b6feac129842', 'req_ip', '客户端ip', 'input', 8, 'string', '管理员', '2020-10-27 11:45:33', '2020-10-28 10:54:48', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e1a5e6f2-1820-4ec1-974e-158cf4bfaa8e', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'city', '城市', 'm-select', 7, 'string', '管理员', '2020-11-02 16:27:17', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('e232c864-f8ed-47b2-871d-8e7d0a143b92', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'username', '用户名', 'input', 1, 'string', '管理员', '2020-10-27 17:44:21', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e249682c-4d7a-4571-bb3c-23ff2a334dd2', '9b12cd56-d1ec-4a3b-8a53-7bca16f7dfde', 'status', '状态', 'input', 10, 'string', '管理员', '2020-11-11 09:13:19', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e2609cda-6c18-4152-aca8-4da38e582136', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'action_type', '类型', 'input', 13, 'string', '管理员', '2020-11-25 16:11:09', '2020-11-26 13:12:11', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e63abd0d-d7ce-4fc2-8ac5-1931fa361579', '44b17190-bce7-4a9a-bf99-8363164523d9', 'city', '城市', 'm-select', 10, 'string', '管理员', '2020-09-17 15:47:59', '2020-10-26 17:53:34', 1, 2, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('e6de0555-39f5-4c68-8ee1-5b3df25785e5', '2d27da23-9af3-45f4-b8d1-7edb8aa4867e', 'time', '时间', 'daterange', 3, 'string', '管理员', '2020-11-02 16:26:18', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e7834183-a85a-4c55-b213-280d1f9a7e7a', '033f7d00-c941-4bff-8936-1b891240ef7d', 'del_type', 'del_type', 'input', 12, 'string', '管理员', '2020-11-26 17:56:59', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e79b3f14-d554-48e1-840a-fc50fa2f8142', '8d1df46f-9356-416a-a182-28154e3c02b5', 'mdate', '日期', 'daterange', 1, 'string', '管理员', '2020-10-26 17:04:26', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e7c75353-9ea4-441b-8c48-cc9136830e45', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'uid', '用户', 'input', 10, 'string', '管理员', '2020-11-25 16:11:30', '2020-11-26 14:52:57', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('e90b7133-7041-4ecb-a3a2-77230c2c6b35', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'country', '国家', 'm-select', 8, 'string', '管理员', '2020-11-02 16:27:34', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('ea34affe-0667-4e7d-98aa-08fe616faf38', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'param2', 'param2', 'input', 14, 'string', '管理员', '2020-11-26 17:44:13', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ea59df0b-ab9b-4556-8a59-aa0493274c17', '6bf00a95-73e1-4d3a-abec-2bfefc9100f2', 'mdate', '日期', NULL, NULL, 'string', '管理员', '2020-09-22 17:25:44', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `field` VALUES ('eab9540a-147e-4337-9d44-2d5164c34234', '44b17190-bce7-4a9a-bf99-8363164523d9', 'result', '操作结果', 'input', 3, 'string', '管理员', '2020-09-16 15:12:57', NULL, 1, 0, 1, 'term', NULL, 0, 0, 'no', NULL, 1);
INSERT INTO `field` VALUES ('ec742b9a-1a70-4cc9-967a-d521aca73fc2', '44b17190-bce7-4a9a-bf99-8363164523d9', 'country', '国家', 'm-select', 5, 'string', '管理员', '2020-09-17 15:47:41', '2020-10-27 09:08:17', 1, 2, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('ecb939e4-2d7f-408c-bcd9-26201c1893e8', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'log_api', 'log_api', 'input', 5, 'string', '管理员', '2020-11-25 16:43:13', '2020-11-26 17:43:20', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ed5b8373-f299-44fd-9651-9d45babb8203', '8257319f-5be0-4da7-8c4d-b18ffb2a0e25', 'country', '国家', 'm-select', 8, 'string', '管理员', '2020-09-17 15:47:41', '2020-10-27 09:08:17', 1, 2, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('ee8d5aad-6c94-4d8f-8617-a467318441f5', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'opt', '操作类型', 'input', 6, 'string', '管理员', '2020-10-20 14:40:28', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('f1ac9d95-d47d-4b14-a80d-cacd2e0b848b', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'result', '操作结果', 'input', 7, 'string', '管理员', '2020-10-20 14:40:46', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('f322181c-f8e4-45af-8604-0ac3d960f34f', 'dcedcbdc-c334-4a14-89d2-a2510454e70c', 'city', '城市', 'm-select', 5, 'string', '管理员', '2020-10-27 17:45:40', NULL, 1, NULL, 1, 'term', '', 1, 0, 'no', '城市数据', 2);
INSERT INTO `field` VALUES ('f5edc745-3f30-42b5-a1cc-b7c760033dc4', 'bf01504e-0c2b-46fd-8cb1-49a6015b98e1', 'username', '用户名', 'input', 0, 'string', '管理员', '2020-11-02 16:25:19', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('f8cb3826-8b1e-43bd-9656-49af50b514bd', '8d1df46f-9356-416a-a182-28154e3c02b5', 'uid', '用户', 'input', 4, 'string', '管理员', '2020-11-25 16:11:30', '2020-11-26 14:52:57', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('fadb9a03-eb05-4499-b282-6a9d5c80737b', '65f9ec29-2a7c-400e-a832-0c1577b0da5e', 'username', '用户名', 'input', 2, 'string', '管理员', '2020-10-20 14:23:21', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('fbc4b008-8cfd-47af-94c3-f6a8f8330273', '033f7d00-c941-4bff-8936-1b891240ef7d', 'urnam', 'urnam', 'input', 16, 'string', '管理员', '2020-11-26 17:59:10', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('fde0c900-f67c-4df4-b2d9-054c5cc796a8', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'action_target', '目标', 'input', 13, 'string', '管理员', '2020-11-25 16:11:17', '2020-11-26 13:12:02', 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('fed26d18-edfe-4865-b5b3-ce8ad61c5174', '033f7d00-c941-4bff-8936-1b891240ef7d', 'real_acct_name', 'real_acct_name', 'input', 18, 'string', '管理员', '2020-11-27 14:40:56', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ff5b1092-debc-45b2-9f9b-744474c45f1a', '333aa3ac-eafd-480f-b334-205f42e70f1b', 'time', '时间', 'daterange', 3, 'string', '管理员', '2020-11-02 16:26:18', NULL, 1, NULL, 1, 'term', '', 0, 0, 'no', '', 1);
INSERT INTO `field` VALUES ('ff6a6504-097f-44e1-b077-b3610d3d467b', 'c0e25be4-5860-4dd2-9d66-2026788c7cc7', 'country', '国家', 'm-select', 8, 'string', '管理员', '2020-09-17 15:47:41', '2020-10-27 09:08:17', 1, 2, 1, 'term', '', 1, 1, 'no', '国家数据', 2);
INSERT INTO `field` VALUES ('ffad714e-1d42-4e36-a1ec-0b364525fb11', '5c4debc2-0765-4d72-b894-6c48c0766617', 'username', '用户名', 'input', 2, 'string', '管理员', '2020-11-19 11:48:28', '2020-11-24 11:28:38', 1, NULL, 1, 'term', '', 1, 0, 'no', '', 1);

-- ----------------------------
-- Table structure for import_file
-- ----------------------------
DROP TABLE IF EXISTS `import_file`;
CREATE TABLE `import_file`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `project_analysis_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目分析对象关系id',
  `node_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作节点',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理文件',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `is_all_file` int(4) NULL DEFAULT NULL COMMENT '是否全部写入 1是 0否',
  `offset` int(11) NULL DEFAULT NULL COMMENT '偏移量',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `rule_group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则组id',
  `priority_file_total` int(64) NULL DEFAULT NULL COMMENT '优先文件总量',
  `priority_file_read` int(64) NULL DEFAULT NULL COMMENT '优先已读文件',
  `remain_file_total` int(64) NULL DEFAULT NULL COMMENT '文件总量',
  `remain_file_read` int(64) NULL DEFAULT NULL COMMENT '文件已读'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for import_statistics
-- ----------------------------
DROP TABLE IF EXISTS `import_statistics`;
CREATE TABLE `import_statistics`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_analysis_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目分析对象关系id',
  `node_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点ip',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `import_bytes` int(11) NULL DEFAULT NULL COMMENT '导入空间量',
  `import_count` int(11) NULL DEFAULT NULL COMMENT '导入条数',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for interface
-- ----------------------------
DROP TABLE IF EXISTS `interface`;
CREATE TABLE `interface`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  `is_enabled` int(4) NULL DEFAULT NULL,
  `is_deleted` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface
-- ----------------------------
INSERT INTO `interface` VALUES ('98e1e5da-f36f-4e46-a6fb-cfe0366a7822', '国家数据', 'post', '/server/search/getCountryNum', '{}', '管理员', '2020-10-20 14:26:39', '2020-10-20 14:26:39', 1, 1);
INSERT INTO `interface` VALUES ('c8ed31bf-181c-4ae1-879e-ca7f6abe4ed8', '城市数据', 'post', '/server/search/getCity', '{}', '管理员', '2020-10-20 14:27:00', '2020-10-20 14:27:00', 1, 1);

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node`  (
  `node_id` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否启用',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `node_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '节点ip',
  `node_config` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点配置',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_enabled` int(4) NULL DEFAULT NULL COMMENT '是否启用 1启用 0未启用',
  PRIMARY KEY (`node_id`, `node_ip`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for node_status
-- ----------------------------
DROP TABLE IF EXISTS `node_status`;
CREATE TABLE `node_status`  (
  `node_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '节点ip',
  `is_alive` int(4) NULL DEFAULT NULL COMMENT '节点是否存活',
  `cpu_rate` decimal(10, 0) NULL DEFAULT NULL COMMENT 'cpu占有率',
  `avalible_dist` decimal(10, 0) NULL DEFAULT NULL COMMENT '可用空间',
  `all_dist` decimal(10, 0) NULL DEFAULT NULL COMMENT '存储空间',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for offline_task
-- ----------------------------
DROP TABLE IF EXISTS `offline_task`;
CREATE TABLE `offline_task`  (
  `id` varchar(60) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '离线任务名称',
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '离线任务状态 0 进行中 1完成 2失败',
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `sizes` bigint(100) NULL DEFAULT NULL COMMENT '文件大小',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '结束时间',
  `create_user` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of offline_task
-- ----------------------------
INSERT INTO `offline_task` VALUES ('0bdfaefd-f9e5-4578-97c2-3c63b54c848d', '20201127124237离线任务', '1', '36db5e56-c963-483f-8909-071d39312e62.csv', 8284165, '2020-11-27 12:42:37', '2020-11-27 12:48:51.956', NULL, '2020-11-27 12:42:37', NULL, '2020-11-27 12:48:52');
INSERT INTO `offline_task` VALUES ('1a13b3c1-c463-431e-af7b-bcdee8876892', '20201127092047离线任务', '1', '04845088-71d0-42e6-98a4-34de8241798f.csv', 131, '2020-11-27 09:20:47', '2020-11-27 09:20:47.656', NULL, '2020-11-27 09:20:47', NULL, '2020-11-27 09:20:48');
INSERT INTO `offline_task` VALUES ('3109dac7-e088-4e7c-9337-84a3fe77c665', '20201214031144离线任务', '1', '17e3949b-4fc2-4237-aea6-444765495e75.csv', 25, '2020-12-14 03:11:44', '2020-12-14 03:11:46.798', NULL, '2020-12-14 03:11:44', NULL, '2020-12-14 03:11:47');
INSERT INTO `offline_task` VALUES ('319f1b42-e95f-42ef-a0ad-fe60851fa33b', '20201214050311离线任务', '1', 'f405bd7a-66b5-473a-a8c3-521dae5f5dc0.csv', 825328, '2020-12-14 05:03:11', '2020-12-14 05:04:03.311', NULL, '2020-12-14 05:03:11', NULL, '2020-12-14 05:04:03');
INSERT INTO `offline_task` VALUES ('34798dc2-a97f-46f6-9b6d-6fe12a52abb7', '20201126162521离线任务', '1', '08843fe7-dbc1-4fba-80bb-613b2a709269.csv', 23092, '2020-11-26 16:25:22', '2020-11-26 16:25:23.345', NULL, '2020-11-26 16:25:22', NULL, '2020-11-26 16:25:23');
INSERT INTO `offline_task` VALUES ('3bdb93c0-3ed7-40a1-a676-58438ee7c910', '20201214031205离线任务', '1', 'f7a07b74-243b-4f13-bc1c-9eca266bd84a.csv', 6, '2020-12-14 03:12:05', '2020-12-14 03:12:06.403', NULL, '2020-12-14 03:12:05', NULL, '2020-12-14 03:12:06');
INSERT INTO `offline_task` VALUES ('41ce20ad-7a6c-4ae8-8a0d-48cfb905036d', '20201127100456离线任务', '1', 'b19136d1-42af-4a21-a52f-f5950b28bc5a.csv', 131, '2020-11-27 10:04:57', '2020-11-27 10:04:57.243', NULL, '2020-11-27 10:04:57', NULL, '2020-11-27 10:04:57');
INSERT INTO `offline_task` VALUES ('4485adf0-9de8-434d-a36b-f11594229e72', '20201127133301离线任务', '1', '2cdf48a4-db47-40db-9ff0-e14bb75f4fc3.csv', 8284165, '2020-11-27 13:33:01', '2020-11-27 13:39:13.089', NULL, '2020-11-27 13:33:01', NULL, '2020-11-27 13:39:13');
INSERT INTO `offline_task` VALUES ('5eb7a9a0-6f2d-4586-b71f-60fe69678b2d', '20201214212533离线任务', '1', '4385c9a8-e0c5-4015-9063-fe5811174f6d.csv', 7, '2020-12-14 21:25:33', '2020-12-14 21:25:34.84', NULL, '2020-12-14 21:25:33', NULL, '2020-12-14 21:25:35');
INSERT INTO `offline_task` VALUES ('6fc5e201-f633-4ed4-b064-d9ffb7d06592', '20201126160349离线任务', '1', '37295e9e-ba8f-4cc1-88c6-3272bdac0040.csv', 23092, '2020-11-26 16:03:49', '2020-11-26 16:03:51.143', NULL, '2020-11-26 16:03:49', NULL, '2020-11-26 16:03:51');
INSERT INTO `offline_task` VALUES ('74ad7ed5-28ec-495c-8277-ba5ad93f8d78', '20201126160416离线任务', '1', '3d52b872-587e-4a70-aa1c-2b74bd696e66.csv', 23092, '2020-11-26 16:04:17', '2020-11-26 16:04:18.495', NULL, '2020-11-26 16:04:17', NULL, '2020-11-26 16:04:18');
INSERT INTO `offline_task` VALUES ('87dbec51-23ff-4908-b852-310415c460aa', '20201126160412离线任务', '1', 'df4696bc-4138-4d8b-96c3-f4729dd23b48.csv', 23092, '2020-11-26 16:04:13', '2020-11-26 16:04:14.38', NULL, '2020-11-26 16:04:13', NULL, '2020-11-26 16:04:14');
INSERT INTO `offline_task` VALUES ('8b5b2278-84f8-4efa-a710-a1a1efdfb9c0', '20201127092220离线任务', '1', 'd73d5b65-5346-4621-943c-e0280fc837fe.csv', 8284165, '2020-11-27 09:22:20', '2020-11-27 09:28:29.886', NULL, '2020-11-27 09:22:20', NULL, '2020-11-27 09:28:30');
INSERT INTO `offline_task` VALUES ('94c99408-cae1-43fa-bbc1-29868f2c44d1', '20201127134424离线任务', '1', '4ccf006a-db10-4426-9bdc-b4538d8141c5.csv', 100719, '2020-11-27 13:44:24', '2020-11-27 13:44:28.868', NULL, '2020-11-27 13:44:24', NULL, '2020-11-27 13:44:29');
INSERT INTO `offline_task` VALUES ('9ff337b5-becb-4817-9645-1c26b0acf4c8', '20201127112821离线任务', '1', '736df2e7-740e-4ea6-ac65-3146b8a98e96.csv', 8284165, '2020-11-27 11:28:21', '2020-11-27 11:34:32.59', NULL, '2020-11-27 11:28:21', NULL, '2020-11-27 11:34:33');
INSERT INTO `offline_task` VALUES ('a5a93fa0-8246-4c23-9e8d-81244a3b48f5', '20201126184106离线任务', '1', '3e0292d8-a0d5-4342-859a-dd4e284b9146.csv', 23092, '2020-11-26 18:41:07', '2020-11-26 18:41:08.649', NULL, '2020-11-26 18:41:07', NULL, '2020-11-26 18:41:09');
INSERT INTO `offline_task` VALUES ('a923c9b8-63a7-405f-af27-8635032bbc80', '20201127111548离线任务', '1', '41b826c8-aeb6-4ef3-8418-da27d2526e3e.csv', 8284165, '2020-11-27 11:15:48', '2020-11-27 11:22:02.795', NULL, '2020-11-27 11:15:48', NULL, '2020-11-27 11:22:03');
INSERT INTO `offline_task` VALUES ('b4c91db0-84ec-4f83-8cc2-8b90df9ce257', '20201214212459离线任务', '1', '94b586df-da13-4df3-ac2e-9c3c48d34008.csv', 25, '2020-12-14 21:25:00', '2020-12-14 21:25:02.175', NULL, '2020-12-14 21:25:00', NULL, '2020-12-14 21:25:02');
INSERT INTO `offline_task` VALUES ('ce0a4cd4-a184-44e3-8d04-5ce324b52308', '20201214211755离线任务', '1', '3ab6faad-1576-4aee-826b-a8754fecd21a.csv', 4307676, '2020-12-14 21:17:56', '2020-12-14 21:23:09.885', NULL, '2020-12-14 21:17:56', NULL, '2020-12-14 21:23:10');
INSERT INTO `offline_task` VALUES ('d5624f82-696b-43b1-a074-894e6c6d8bcf', '20201127092129离线任务', '1', 'f20446c8-059a-4dc3-86d6-35d46e52e883.csv', 23092, '2020-11-27 09:21:29', '2020-11-27 09:21:30.758', NULL, '2020-11-27 09:21:29', NULL, '2020-11-27 09:21:31');
INSERT INTO `offline_task` VALUES ('dcecb56f-06ab-4444-9bfa-0e93d0626a8e', '20201126184058离线任务', '1', '5eba2fa6-b79e-4cd1-92c4-ad20a926edd0.csv', 23092, '2020-11-26 18:40:59', '2020-11-26 18:41:00.233', NULL, '2020-11-26 18:40:59', NULL, '2020-11-26 18:41:00');
INSERT INTO `offline_task` VALUES ('e26c2821-146a-4e66-b1e3-047d1b833ad2', '20201126160421离线任务', '1', '7e13c794-521d-4c6b-b9ab-7249b550ce35.csv', 23092, '2020-11-26 16:04:21', '2020-11-26 16:04:23.203', NULL, '2020-11-26 16:04:21', NULL, '2020-11-26 16:04:23');
INSERT INTO `offline_task` VALUES ('ed0c789d-00ff-4f74-9759-2f8340a3b136', '20201126172942离线任务', '1', '01e0a00b-ae21-48a7-8630-549bb5fe513d.csv', 23092, '2020-11-26 17:29:42', '2020-11-26 17:29:43.962', NULL, '2020-11-26 17:29:42', NULL, '2020-11-26 17:29:44');
INSERT INTO `offline_task` VALUES ('f04369e6-c322-4978-a62d-8c00f90b5628', '20201127093424离线任务', '1', '5c9f312d-3434-4bb6-8f75-1d683eb853b2.csv', 8284165, '2020-11-27 09:34:25', '2020-11-27 09:40:35.263', NULL, '2020-11-27 09:34:25', NULL, '2020-11-27 09:40:35');
INSERT INTO `offline_task` VALUES ('fd46fa07-44a1-4d84-b58a-6f2d3229458a', '20201127134513离线任务', '1', '899f499b-7c69-43f7-93d3-75fbd74e840e.csv', 8284165, '2020-11-27 13:45:13', '2020-11-27 13:51:24.479', NULL, '2020-11-27 13:45:13', NULL, '2020-11-27 13:51:24');
INSERT INTO `offline_task` VALUES ('fdc7d66c-a37f-4528-8545-808bce5bfb49', '20201214212347离线任务', '1', 'd801a14b-3f41-4d26-8c86-111b711a9eca.csv', 5, '2020-12-14 21:23:48', '2020-12-14 21:24:01.577', NULL, '2020-12-14 21:23:48', NULL, '2020-12-14 21:24:02');

-- ----------------------------
-- Table structure for prgram_config
-- ----------------------------
DROP TABLE IF EXISTS `prgram_config`;
CREATE TABLE `prgram_config`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `keyes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置内容',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gwt_create` datetime(0) NULL DEFAULT NULL,
  `gwt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for progress
-- ----------------------------
DROP TABLE IF EXISTS `progress`;
CREATE TABLE `progress`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `use_rule_group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_total` int(255) NULL DEFAULT NULL COMMENT '文件总量',
  `primary_file_total` int(255) NULL DEFAULT NULL COMMENT '优先文件总量',
  `normal_file_handle` int(255) NULL DEFAULT NULL COMMENT '已处理文件总量',
  `primary_file_handle` int(255) NULL DEFAULT NULL COMMENT '优先已处理文件总量',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of progress
-- ----------------------------
INSERT INTO `progress` VALUES ('18dfd895-e98a-4783-aa24-9e7421d797d6', '18dfd895-e98a-4783-aa24-9e7421d797d6', 81, 81, 0, 81, NULL, '2020-09-19 13:51:42', '2020-09-19 13:51:49');
INSERT INTO `progress` VALUES ('1bb29854-dc70-4b38-97a0-1017674d167e', '1bb29854-dc70-4b38-97a0-1017674d167e', 181, 181, 0, 724, NULL, NULL, NULL);
INSERT INTO `progress` VALUES ('2706007e-2f16-40d4-9992-143dd5c16231', '2706007e-2f16-40d4-9992-143dd5c16231', 181, 181, 0, 181, NULL, NULL, NULL);
INSERT INTO `progress` VALUES ('3b79fa18-dd95-4bce-b1a9-75cc9099365a', '3b79fa18-dd95-4bce-b1a9-75cc9099365a', 162, 162, 0, 81, NULL, '2020-09-19 13:54:50', '2020-09-19 13:54:58');
INSERT INTO `progress` VALUES ('3f3f5c18-dd5a-42dc-9e37-7dc79cd3d8d4', '3f3f5c18-dd5a-42dc-9e37-7dc79cd3d8d4', 181, 181, 0, 81, NULL, NULL, NULL);
INSERT INTO `progress` VALUES ('55d944e8-5133-4ba4-93e8-b24a5c02abfd', '55d944e8-5133-4ba4-93e8-b24a5c02abfd', 61, 61, 0, 122, NULL, '2020-10-20 17:35:29', '2020-10-20 17:53:54');
INSERT INTO `progress` VALUES ('5c5b1abf-83a1-4e1b-9d64-21c53d7b6ce4', '5c5b1abf-83a1-4e1b-9d64-21c53d7b6ce4', 243, 243, 0, 81, NULL, '2020-09-19 13:59:18', '2020-09-19 13:59:24');
INSERT INTO `progress` VALUES ('7b927e0e-66aa-46c0-bfcd-88dda27d3bb1', '7b927e0e-66aa-46c0-bfcd-88dda27d3bb1', 181, 181, 0, 181, NULL, NULL, NULL);
INSERT INTO `progress` VALUES ('d6c8e17d-e233-428d-a199-fd0da749149b', 'd6c8e17d-e233-428d-a199-fd0da749149b', 181, 181, 0, 181, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `clues` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优先筛选字段',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `unit_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位id',
  `status` int(4) NULL DEFAULT NULL COMMENT '0默认， 1等待开始 10运行中 100完成 -1失败 -10取消 -11取消成功',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `step` int(4) NULL DEFAULT NULL COMMENT '步骤',
  `reasons` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败原因',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1c586338-8155-41c5-a71f-280b53cac421', '', 'coremail_imapsvr', NULL, '5e2828fe-ef72-4458-8525-849d89118e83', 100, '管理员', '2020-12-10 21:25:23', '2020-12-10 21:25:41', 2, '', '2020-12-10 21:40:01', '2020-12-10 21:47:00');
INSERT INTO `project` VALUES ('3818018f-ab63-4a91-8d16-19ddfd99f72f', '', 'coremail_36_11_imapsvr', NULL, '5e2828fe-ef72-4458-8525-849d89118e83', 100, '管理员', '2020-12-10 21:42:48', '2020-12-10 21:43:28', 2, '', '2020-12-10 21:47:00', '2020-12-10 21:52:24');
INSERT INTO `project` VALUES ('3f072a9e-4299-4933-b6bd-8805014933db', '', '1', NULL, '5e2828fe-ef72-4458-8525-849d89118e83', 0, '管理员', '2020-12-13 21:31:16', '2020-12-13 21:31:16', 1, NULL, NULL, NULL);
INSERT INTO `project` VALUES ('4a1064e2-eab7-42e8-90ad-4a8eb5d62b45', '', 'test_import', NULL, '2317bc45-ddcb-493f-b0a0-5a231df8c90e', 10, '管理员', '2020-12-14 01:12:36', '2020-12-14 01:15:13', 2, NULL, '2020-12-14 01:15:13', NULL);
INSERT INTO `project` VALUES ('59c81131-eaaf-44b2-b7c3-927fdc430dbf', '', 'coremail_6_25_imapsvr', NULL, '5e2828fe-ef72-4458-8525-849d89118e83', 100, '管理员', '2020-12-10 21:45:05', '2020-12-10 21:45:18', 2, '', '2020-12-10 21:52:25', '2020-12-10 21:59:29');
INSERT INTO `project` VALUES ('a96df254-1813-4034-b8a4-4ad21575558b', '', 'test2', NULL, '2317bc45-ddcb-493f-b0a0-5a231df8c90e', 1, '管理员', '2020-12-14 01:18:28', '2020-12-14 02:53:45', 2, '', '2020-12-14 02:53:45', '2020-12-14 02:02:57');

-- ----------------------------
-- Table structure for project_analysis_mapper
-- ----------------------------
DROP TABLE IF EXISTS `project_analysis_mapper`;
CREATE TABLE `project_analysis_mapper`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目id',
  `object_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分析对象id',
  `object_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分析对象名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分析对象唯一标识',
  `status` int(6) NULL DEFAULT NULL COMMENT '运行状态',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int(10) NULL DEFAULT NULL COMMENT '伪删除 1删除 0未删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_analysis_mapper
-- ----------------------------
INSERT INTO `project_analysis_mapper` VALUES ('0757042b-e076-4b69-894a-87b8ea120209', 'eb3235e4-abb4-4fbd-a777-de7e9d12f72e', '9e9b18d7-2cf8-464d-ae17-0a479b92d87d', 'test_coremail_ana', 'test_coremail_ana', 0, '2020-11-25 17:11:33', '2020-11-25 17:11:33', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('19588981-59bb-490b-a646-82c1b3735db5', '1c586338-8155-41c5-a71f-280b53cac421', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_ana_1119_1', 'weichai_ana_1119_1', 0, '2020-12-10 21:25:38', '2020-12-10 21:25:38', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('3a57cad3-fa4c-4fec-9737-5333af2dfedf', '37e11f71-3aa7-47c8-baac-74ade5af8c31', '147a0310-c7eb-4644-9552-31b216101044', 'eap_ana1', 'eap_ana1', 0, '2020-10-20 17:34:45', '2020-10-20 17:34:45', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('5f0ee01c-469a-4921-9382-9d22f50c7afd', '6b85b0fb-7030-48bc-b01d-99d09f057319', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'coremail', 'coremail', 0, '2020-09-19 13:54:08', '2020-09-19 13:54:08', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('74825323-e794-4ac8-a343-5cebfdb5eb35', 'a96df254-1813-4034-b8a4-4ad21575558b', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_ana_1119_1', 'weichai_ana_1119_1', 0, '2020-12-14 01:18:53', '2020-12-14 14:35:15', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('aee6ca07-35f8-4158-8960-fab0d4d46a4f', '3818018f-ab63-4a91-8d16-19ddfd99f72f', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_ana_1119_1', 'weichai_ana_1119_1', 0, '2020-12-10 21:43:24', '2020-12-10 21:43:24', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('c6731fcf-f7f2-4a95-b8e8-616e156febf2', '4a1064e2-eab7-42e8-90ad-4a8eb5d62b45', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_ana_1119_1', 'weichai_ana_1119_1', 0, '2020-12-14 01:15:02', '2020-12-14 01:15:11', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('c97638ab-c16b-49e9-a86a-24a6f2addaab', '4a4e6a8a-319e-4cd8-b064-aefe1a55835d', '9e55951b-4ae2-48b9-9058-880e8b6793df', 'weichaiana', 'weichaiana', 0, '2020-11-03 14:15:36', '2020-11-03 14:15:36', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('d9d8caed-067c-4529-a5ac-42fa9408d9ca', '59c81131-eaaf-44b2-b7c3-927fdc430dbf', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_ana_1119_1', 'weichai_ana_1119_1', 0, '2020-12-10 21:45:15', '2020-12-10 21:45:15', NULL);
INSERT INTO `project_analysis_mapper` VALUES ('edb4c2e8-230a-4a0b-8e23-8478ab5b38b9', '4a4e6a8a-319e-4cd8-b064-aefe1a55835d', '1634fb58-0a76-4dbf-ae7d-413438588afb', 'haihangnan', 'haihangnan', 0, '2020-11-03 14:15:36', '2020-11-03 14:15:36', NULL);

-- ----------------------------
-- Table structure for rule
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `rule_group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_data` varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志数据',
  `log_feature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志特征',
  `extract_rule` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提取规则',
  `switch_rule` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转换规则',
  `replace_rule` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '替换规则',
  `supplement_rule` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '补充规则',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `is_enabled` int(4) NULL DEFAULT NULL COMMENT '是否启用',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule
-- ----------------------------
INSERT INTO `rule` VALUES ('0c4fa9a1-5a76-4283-adef-f22567d80a5a', 'lin_r_1112_2', 'd2747bbb-7b43-46b6-9c0a-435e2e0d95a6', '20150901-00:41:44 U:t_sunrui M:login C:113.57.191.75 172.16.88.1 S:0 A:{Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36}', '', '%{DATA>>mdate}-%{TIME>>time}\\sU:%{DATA>>username}\\s...C:%{DATA>>req_ip}\\s', 'mdate:$parseDate(\'%Y%m%d\')>>mdate;req_ip:$getGeo>>country,city;username:$addStr(\'@jiuzhou.com.cn\')>>username', '', '{ \"opt\": \"login\",\"result\":\"success\",\"type\":\"HTTP\" }', 'login', '', 1, '管理员', '2020-11-12 11:44:55', NULL);
INSERT INTO `rule` VALUES ('0c81c8c4-e6cd-49b1-a8e9-19f6c1ec142e', 'imap', '817e4fcf-198c-40c2-8831-f30d1121bf5d', 'T:3564160768(00:00:08)[root-I023De4BAIjvP1+Z1qUA:Info] User yuxiaoguang01@weichai.com from [117.136.9.178] login success', 'login', '...\\(%{TIME>>time}\\)...\\s*User\\s%{DATA>>username}\\s...\\[%{IPV4>>req_ip}\\]\\s...\\s%{ALLDATA>>result}', 'req_ip:$getGeo>>country,city', '', '{ \"opt\": \"login\", \"type\": \"IMAP\"}', 'login', '', 1, '管理员', '2020-09-08 11:55:06', '2020-12-10 21:01:08');
INSERT INTO `rule` VALUES ('200e72e7-9dd6-4ae9-867e-b13b8971c054', 'pop3', 'bc0820bc-4715-4293-bc20-eed07c52e163', NULL, 'retr msg', '...\\(%{TIME>>time}\\)...\\s*User\\s%{DATA>>username}\\s...\\[%{IPV4>>req_ip}\\].*', 'req_ip:$getGeo>>country,city', '', '{ \"opt\": \"login\", \"type\": \"POP\", \"result\": \"success\" }', '登录成功', '', 1, '管理员', '2020-09-08 11:56:53', '2020-09-19 13:43:57');
INSERT INTO `rule` VALUES ('2753756a-981f-4cd4-b1b0-e409edf04851', 'lin_r_1112_1', 'd9f25923-140f-404f-9aa9-b3b227a36c16', '20150901-00:41:44 U:t_sunrui M:login C:113.57.191.75 172.16.88.1 S:0 A:{Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36}', '', '%{DATA>>mdate}-%{TIME>>time}\\sU:%{DATA>>username}\\s...C:%{DATA>>req_ip}\\s', 'mdate:$parseDate(\'%Y%m%d\')>>mdate;req_ip:$getGeo>>country,city;username:$addStr(\'@jiuzhou.com.cn\')>>username', '', '{ \"opt\": \"login\" }', 'login', '', 1, '管理员', '2020-11-13 17:40:57', '2020-11-24 16:56:23');
INSERT INTO `rule` VALUES ('2ea08e42-b65f-4e91-9b67-d026659085d8', 'lin_rule_1112', 'd9f25923-140f-404f-9aa9-b3b227a36c16', 'T:2558260992(00:00:07)[root-I011mXkAAIdfwV6dtj0A:Info] User djyzhixab@weichai.com from [112.224.69.113] login fail', 'login', '.*\\(%{TIME>>time}...User\\s%{DATA>>username}\\sfrom\\s\\[%{IPV4>>ip}...login\\s%{ALLDATA>>result}', 'ip:$getGeo>>country,city', '', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', 'login', '', 1, '管理员', '2020-11-24 16:19:47', NULL);
INSERT INTO `rule` VALUES ('475c4763-3ab8-4d06-8c4a-5fb7551bf6f4', 'exchange_ecp_rule', '9f340582-b57d-415d-91f3-4d33e26632fe', '2020-07-24T02:43:29.156Z,7ce6274f-14c9-43b1-b82c-3a194b68bab2,15,0,1497,0,,Ecp,mail.dfmc.com.cn,/ecp/Performance/ClientPerformance.svc/ReportWatson,,FBA,true,DFMC\\wangchunguang,,Sid~S-1-5-21-4223072487-2266339643-505866507-36509,Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML  like Gecko) Chrome/78.0.3904.108 Safari/537.36,183.93.161.242,DFMC-DMZCAS-02,200,200,,POST,Proxy,dfmc-mbx-02.dfmc.com.cn,15.00.1497.000,IntraForest,WindowsIdentity,Database~333539ff-a190-443a-ac0d-66591a10c1c1~~2020-08-23T02:43:28,,,2441,133,1,,0,1,,0,,0,,0,0,,0,10,0,0,0,0,6,0,0,0,0,0,9,1,6,4,4,4,10,,?msExchEcpCanary=QsI3_r1jLEeNNJ1R7Q_HMKWutjl7L9gIzLms_edEyvwKb--F4YPlqTmRn1FqwxOy04dMumG9tKM.,,BeginRequest=2020-07-24T02:43:29.146Z;CorrelationID=<empty>;ProxyState-Run=None;FEAuth=BEVersion-1941997017;BeginGetRequestStream=2020-07-24T02:43:29.146Z;OnRequestStreamReady=2020-07-24T02:43:29.146Z;BeginGetResponse=2020-07-24T02:43:29.146Z;OnResponseReady=2020-07-24T02:43:29.156Z;EndGetResponse=2020-07-24T02:43:29.156Z;ProxyState-Complete=ProxyResponseData;EndRequest=2020-07-24T02:43:29.156Z;,', 'Ecp', '%{DATE>>mdate}T%{TIME>>time}...,...,..,...,...,...,...,...,%{DATA>>domain},...,...,...,...,%{DATA>>uid},...,...,...,%{DATA>>ip},...,%{DATA>>log_status},...', 'ip:$getGeo>>country,city', '', '', 'ecp访问日志', 'mdate\ntime\ndomain\nuser\nip\nlogstatus\ncountry\ncity', 1, '管理员', '2020-11-25 10:23:29', '2020-11-26 17:36:33');
INSERT INTO `rule` VALUES ('58cada17-b960-4643-8a59-bc8b404c6c26', 'test_ana_1126_rg_rule', '5ba02513-f2be-4b09-8684-9769baaa6224', '20150901-00:41:44 U:t_sunrui M:login C:113.57.191.75 172.16.88.1 S:0 A:{Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36}', '', '%{DATA>>mdate}-%{TIME>>time}\\sU:%{DATA>>username}\\s...C:%{DATA>>req_ip}\\s', 'mdate:$parseDate(\'%Y%m%d\')>>mdate;req_ip:$getGeo>>country,city;username:$addStr(\'@jiuzhou.com.cn\')>>username', '', '{ \"opt\": \"login\" }', 'login', '', 1, '管理员', '2020-11-26 11:24:19', NULL);
INSERT INTO `rule` VALUES ('5be2f8a1-08b1-4d58-90ff-5e67fc1e1d85', 'coremail_wmsvr_rule', 'ab82c65e-a34c-4ca6-b23e-daaa1930d417', '23:59:00 INFO    [tid:140,Q:W173AJHYBcRUbZqEXEBZ] [OP]func=notify:notifyEvent,remote=127.0.0.1,sid=GACbFissDCZtEAfylzssZURFfkKqtIQI,user=yanruojing@mail.iap.ac.cn,requestVar={eventTime:20190729085000,eventType:10,extra:\'\',sender:yanruojing@mail.iap.ac.cn},result=S_OK,reqTime=0,opTime=12,respTime=0,requestURL=http://127.0.0.1/coremail/s', '', '%{TIME>>time}\\s...func=%{DATA>>func},remote=%{IPV4>>remote}...user=%{DATA>>to_user},requestVar=\\{%{DATA>>result_var}\\},result=%{DATA>>result},...', 'remote:$getGeo>>country,city', '', '', 'wmsvr邮箱操作日志', 'time\nfunc\nremote\nto_user\nresult_var\nresult\ncountry\ncity', 1, '管理员', '2020-11-25 16:37:00', '2020-11-27 14:48:44');
INSERT INTO `rule` VALUES ('70e700e5-cd54-42da-b6dc-46a59d2102d4', 'iis_rule', 'cfca3898-3b74-47a9-ab06-8d15c3520468', '2020-07-08 12:09:43 10.5.42.239 GET /owa/manifests/appCacheManifestHandler.ashx manifest=0&layout=mouse&UA=0&CorrelationID=<empty>;&ClientId=GESUWA0LUCUQNPW&cafeReqId=74de217e-4274-4073-90b3-5f0a32eeb4c9; 443 adadmin.oszy 103.75.190.254 Mozilla/5.0+(Windows+NT+6.3;+Win64;+x64)+AppleWebKit/537.36+(KHTML,+like+Gecko)+Chrome/83.0.4103.97+Safari/537.36 https://autodiscover.sinopec.com/owa/ 200 0 0 15', '', '%{DATE>>mdate}\\s%{TIME>>time}\\s%{IPV4>>server_ip}\\s%{DATA>>type}\\s\\/%{DATA>>log_api}\\/...;...;\\s%{DATA>>response}\\s%{DATA>>uid}\\s%{IPV4>>client_ip}\\s%{DATA>>user_agent}\\s...\\s%{DATA>>status}\\s%{DATA>>param1}\\s%{DATA>>param2}\\s%{ALLDATA>>to_size}', 'client_ip:$getGeo>>country,city', '', '', 'iis访问日志', 'mdate\ntime\nserver_ip\ntype\nlog_api\nresponse\nuser\nclient_ip\nuser_agent\nstatus\nparam1\nparam2\nsize\ncountry\ncity', 1, '管理员', '2020-11-25 11:17:59', '2020-11-26 17:42:24');
INSERT INTO `rule` VALUES ('78f2b143-5c9d-4ab0-8548-0993b8857631', 'weichai_rg_1119_imapsvr_1', 'dfdcf2ff-6986-4043-b935-98b68721e276', 'T:2558260992(00:00:07)[root-I011mXkAAIdfwV6dtj0A:Info] User djyzhixab@weichai.com from [112.224.69.113] login fail', 'login', '.*\\(%{TIME>>time}...User\\s%{DATA>>username}\\sfrom\\s\\[%{IPV4>>ip}...login\\s%{ALLDATA>>result}', 'ip:$getGeo>>country,city', '', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', 'login', '', 1, '管理员', '2020-11-19 11:40:49', NULL);
INSERT INTO `rule` VALUES ('89fc8ae0-a87b-4338-bff5-f2c4094aec1c', 'coremai_webadmin_rule', 'bb46f15a-9594-4d9c-b64f-e42c66d1991e', '10:13:13 INFO [tid:59,Q:A171BtKoBcQqGIlqqYCI] func=cma.org.EditAction,remote=172.30.0.19,sid=BAmKcWQQDOvqPvOXrzQQKyPGgFgOoaZv,uid=zyh2014@cstnet.cn,role=SA,page=/WEB-INF/jsp/org/mod_edit.jsp,action.type=Edit,action.target=Org:qaii.ac.cn,resultOK,opTime=613,logDbTime=9,requestURL=https://159.226.251.21/webadmin/~BAmKcWQQDOvqPvOXrzQQKyPGgFgOoaZv/~/org/mod_edit.jsp', 'action.target', '%{TIME>>time}\\s...func=%{DATA>>func},remote=%{IPV4>>remote}...uid=%{DATA>>uid},page=%{DATA>>to_page},action.type=%{DATA>>action_type},action.target=%{DATA>>action_target},%{DATA>>result},...', 'remote:$getGeo>>country,city', '', '', 'web登录日志', 'time\nfunc\nremote\nuid\nto_page\naction_type\naction_target\nresult\ncountry\ncity', 1, '管理员', '2020-11-25 16:39:36', '2020-11-27 13:38:06');
INSERT INTO `rule` VALUES ('8db7783c-2712-429f-8698-5e0d0dceae64', 'nginx_access_rule', 'bee22b3e-86e1-4baf-a27f-50ec413d0b3e', '192.168.9.121 - - [15/May/2019:15:20:32 +0800]  200 \"POST /webadm/?q=base.zr&zid=2449fc5da387f23275ec71497c81f6c9 HTTP/1.1\" 124 \"http://192.168.9.121/webadm/?q=base&zid=2449fc5da387f23275ec71497c81f6c9\" \"Mozilla/5.0 (X11; Linux x86_64; rv:45.0) Gecko/20100101 Firefox/45.0\" \"-\"', '', '%{IPV4>>ip}...\\[%{DATA>>mdate}\\:%{TIME>>time}\\s...\\s\\s%{DATA>>response}\\s...\\s%{DATA>>url}\\s...\\s%{DATA>>to_size}\\s...\\s\\\"%{DATA>>useragent}\\\"', 'ip:$getGeo>>country,city', '', '', 'nginx访问日志', 'ip\nmdate\ntime\nresponse\nurl\nto_size\nuseragent\ncountry\ncity', 1, '管理员', '2020-11-24 19:20:43', '2020-11-27 14:35:52');
INSERT INTO `rule` VALUES ('9511cfec-38d1-4732-b8c2-a92854d04ce2', 'weichai_rule', '025c01c3-e992-4ac7-913d-85af0a9e80f1', NULL, '', '%{IPV4>>req_ip}\\s.%{DATA>>mdate}:%{DATA>>time}\\s[\\S]{1,6}\\s%{ALLDATA>>username}', 'req_ip:$getGeo>>country,city;mdate:$parseDateweichai(\'%Y%m%d\')>>mdate', '', '{\"opt\":\"login\",\"result\":\"success\",\"type\":\"http\"}', 'pop', '', 1, '管理员', '2020-10-30 16:34:53', '2020-11-02 10:50:27');
INSERT INTO `rule` VALUES ('978aeb35-dfe4-47b3-81c4-7cc0be7f25de', 'weichai_access_r1_1119', 'e4b79b7c-09bd-4991-b7c4-0e4c8d3585f4', '223.97.166.74 - weichai.com\\x5Climinglei [03/Jul/2020:00:00:33 +0800] \"POST /Microsoft-Server-ActiveSync?Cmd=Provision&User=weichai.com%5Climinglei&DeviceId=androidc947596446&DeviceType=Android HTTP/1.1\" 403 5 \"-\" \"Android/8.0.0-EAS-2.0\" \"-\" 0.004 0.004 ', '', '%{IPV4>>ip}\\s-\\s%{DATA>>username}\\s...2020:%{TIME>>time}\\s...\\\"%{DATA>>result}\\s%{DATA>>url_content}\\\"\\s...\\s...\\s...\\s\\\"%{DATA>>phone_flag}\\\"', 'ip:$getGeo>>country,city', '', '{ \"opt\":\"POST\",\"type\": \"HTTP\" }', 'login', '对每条日志进行读取', 1, '管理员', '2020-11-19 14:30:48', '2020-11-24 11:53:43');
INSERT INTO `rule` VALUES ('a57826ec-a8c1-440c-9c88-056213c2acd2', 'eyou_del_mail_rule', '4de3d49c-16c4-404b-8558-ad91b82e7b20', '2019-05-21T23:58:27+08:00 1558454307.00704700 localhost INFO (6) [23040]: del_type:[web_user], acct_id:[141], mail_id:[57120], domain_id:[2], acct_type:[0], domain_type:[0], acct_name:[jiang], real_acct_name:[jiang], domain_name:[iapcm.ac.cn], real_domain_name:[iapcm.ac.cn], admin_type:[0], op_acct_id:[141], op_domain_id:[2], op_acct_type:[0], op_domain_type:[0], op_acct_name:[jiang], op_real_acct_name:[jiang], op_domain_name:[iapcm.ac.cn], op_real_domain_name:[iapcm.ac.cn], op_admin_type:[0], deliver_type:[web_user], from_email:[journals@mail.elsevier.com], subject_digest:[Song Jiang, your work has been cited], size:[24277], has_attach:[0], index_time:[1558454213], file_id:[EA/00/00/05/28/MSE5CE41E284FA8A2224AE/014-EA/00/00/05/28/MSE5CE41E284FA8A2224AE/015], mail_unique:[1ea67b2351f57f5716268fddb7fb1a50], client_ip:[116.136.19.118], server_name:[localhost]', '', '%{DATE>>mdate}.%{TIME>>time}...del_type..%{DATA>>del_type}\\]...real_acct_name..%{DATA>>real_acct_name}\\]...from_email..%{DATA>>from_email}..subject_digest..%{DATA>>subject_digest}\\]\\,...client_ip..%{DATA>>client_ip}\\]...', 'client_ip:$getGeo>>country,city', '', '', 'del_mail邮件删除日志', 'mdate\ntime\ndel_type\nreal_acct_name\nfrom_email\nsubject_digest\nclient_ip\ncountry\ncity', 1, '管理员', '2020-11-24 14:05:39', '2020-11-27 14:30:51');
INSERT INTO `rule` VALUES ('b254b944-c8af-4b98-95a7-757c181150d4', 'eyou_auth_role', 'd3110eeb-fe40-4288-aaee-fe5b25075f5c', '2019-05-21 03:41:04 [10759]: auth_type:[smtp], extra_type:[], acct_id:[33], domain_id:[2], acct_type:[0], domain_type:[0], acct_name:[chen_jing], real_acct_name:[chen_jing], domain_name:[iapcm.ac.cn], real_domain_name:[iapcm.ac.cn], admin_type:[0], client_ip:[127.0.0.1], server_name:[localhost], reault:[Auth Failed: [password failed]]', '', '%{DATE>>mdate}.%{TIME>>time}...auth_type..%{DATA>>auth_type}\\]...real_acct_name..%{DATA>>real_acct_name}\\]....client_ip..%{DATA>>client_ip}\\]...', 'client_ip:$getGeo>>country,city', '', '', 'auth邮箱登录日志', 'mdate\ntime\nauth_type\nreal_acct_name\nclient_ip\ncountry\ncity', 1, '管理员', '2020-11-24 14:44:12', '2020-11-27 14:32:29');
INSERT INTO `rule` VALUES ('b9bdc232-b5a9-4721-a983-203b470f6f99', 'eyou_analytics_rule', '474194b9-3daf-48fc-b454-e0b40894ad03', '2019-05-16T23:45:19+08:00 1558021519.63746900 localhost INFO (6) [18246]: {\"ip\":\"123.114.46.197\",\"ctime\":\"1558021524\",\"ctstr\":\"2019-5-16 23:45:24 8\",\"ctyea\":\"2019\",\"ctmon\":\"5\",\"ctday\":\"16\",\"cthou\":\"23\",\"ctmin\":\"45\",\"ctsec\":\"24\",\"ctmse\":\"915\",\"ctzon\":\"8\",\"cswid\":\"1920\",\"cshei\":\"1080\",\"bdet\":\"ierv:0, iev:0\",\"bext\":\"fla:20,coo:1\",\"ua\":\"Mozilla\\/5.0 (Windows NT 10.0; WOW64) AppleWebKit\\/537.36 (KHTML, like Gecko) Chrome\\/49.0.2623.75 Safari\\/537.36 LBBROWSER\",\"uah\":\"942ae1a5b761a27f8b72a18d7775da64\",\"henc\":\"gzip, deflate\",\"hlang\":\"zh-CN,zh;q=0.8\",\"hchar\":\"\",\"umod\":\"listmail\",\"uid\":\"254\",\"uname\":\"ma_guicun\",\"urnam\":\"ma_guicun\",\"did\":\"2\",\"dname\":\"iapcm.ac.cn\",\"drnam\":\"iapcm.ac.cn\",\"stime\":1558021519,\"smtim\":\"63765900\",\"sname\":\"localhost\"}', '', '%{DATE>>mdate}.%{TIME>>time}...%{IPV4>>ip}...ctstr\":\"%{DATA>>ctstr}\"...umod\":\"%{DATA>>umod}\"...urnam\":\"%{DATA>>urnam}\"...', 'ip:$getGeo>>country,city', '', '', 'analytics邮箱操作投递日志', 'mdate\ntime\nip\nctstr\numod\nurnam\ncountry\ncity', 1, '管理员', '2020-11-24 13:43:50', '2020-11-27 14:33:48');
INSERT INTO `rule` VALUES ('c14987ae-cd79-43f5-9c43-8475d46314bb', 'wms login DAV', '9b345b92-a2a3-43bd-867b-57dd1cc1ee03', NULL, 'login for DAV', '%{TIME>>time}\\s%{LEVEL>>level}\\s*?\\[tid:%{DATA>>tid}\\]...DAV\\s%{DATA>>result},%{ALLDATA>>add_data}', 'add_data:$splitParams(\',\',\'=\')>>username,,req_ip,,req_url;req_ip:$getGeo>>country,city', 'result==succeed>>result=success;result==failed>>result=fail', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', 'login', '', 1, '管理员', '2020-09-08 10:21:16', '2020-09-19 13:43:57');
INSERT INTO `rule` VALUES ('da6ca5fb-3902-4454-b45f-6b954c36e1bc', 'exchange_ews_rule', '52b50052-6a70-4fd1-a98d-c090b3a7f59c', '2020-08-19T13:59:59.879Z,afa449d1-b15e-441b-b1c6-ec660f710c36,15,0,1497,6,,Ews,mail.dfmc.com.cn,/EWS/Exchange.asmx,,Negotiate,true,DFMC\\fanglz,dfmc.com.cn,Smtp~fanglz@dfmc.com.cn,OC/16.0.4717.1000 (Skype for Business),171.113.194.144,DFMC-DMZCAS-02,200,200,,POST,Proxy,dfmc-mbx-05.dfmc.com.cn,15.00.1497.000,IntraForest,AnchorMailboxHeader-SMTP,Database~a13d944e-5e2e-407a-a208-dc276f9d6107~~2020-09-18T13:59:59,,,536,6704,1,,0,0,,0,,0,,0,0,,0,179,0,111,0,0,63,1,0,0,0,0,178,1,65,4,5,5,179,,,,BeginRequest=2020-08-19T13:59:59.699Z;CorrelationID=<empty>;ProxyState-Run=None;FEAuth=BEVersion-1941997017;BeginGetRequestStream=2020-08-19T13:59:59.699Z;OnRequestStreamReady=2020-08-19T13:59:59.699Z;BeginGetResponse=2020-08-19T13:59:59.809Z;OnResponseReady=2020-08-19T13:59:59.879Z;EndGetResponse=2020-08-19T13:59:59.879Z;ProxyState-Complete=ProxyResponseData;EndRequest=2020-08-19T13:59:59.879Z;S:ServiceCommonMetadata.Cookie=b9fa36b7ef6046c180ed87b27f510a88,', 'Ews', '%{DATE>>mdate}T%{TIME>>time}...,...,..,...,...,...,...,...,%{DATA>>domain},...,...,...,...,%{DATA>>uid},...,Smtp~%{DATA>>realuser},...,%{DATA>>ip},...,%{DATA>>log_status},...', 'ip:$getGeo>>country,city', '', '', 'ews访问日志', 'mdate\ntime\ndomain\nuser\nrealuser\nip\nlogstatus\ncountry\ncity', 1, '管理员', '2020-11-25 10:54:05', '2020-11-26 17:30:03');
INSERT INTO `rule` VALUES ('f147cd7b-6a37-4530-a904-3bdb110733f7', 'eyou_deliver_rule', 'd482c007-7005-41df-9654-cdd7d4bf0643', '2019-05-24 08:11:27 from_acct_id:[0] from_domain_id:[0] from_acct_type:[0] from_domain_type:[0] from_acct_name:[mailer-daemon] from_real_acct_name:[mailer-daemon] from_domain_name:[iapcm.ac.cn] from_real_domain_name:[iapcm.ac.cn] to_acct_id:[136] to_domain_id:[2] to_acct_type:[0] to_domain_type:[0] to_acct_name:[iccp7] to_real_acct_name:[iccp7] to_domain_name:[iapcm.ac.cn] to_real_domain_name:[iapcm.ac.cn] from_digest:[mailer-daemon@iapcm.ac.cn] to_digest:[iccp7@iapcm.ac.cn] subject_digest:[failure notice] size:[3967] file_id:[] current_unique:[e96cdbabee4d9e6f739cfc1ac5954678] assoc_unique:[4d0a22cc0680fa4113d4b844fc564678] module_type:[smtpd] deliver_type:[in_queue] is_finished:[0] retry_times:[1] result:[0] response:[write in queue success] client_ip:[127.0.0.1] server_name:[localhost] log_time:[1558656687]', '', '%{DATE>>mdate}.%{TIME>>time}...to_real_acct_name..%{DATA>>to_real_acct_name}\\]...from_digest..%{DATA>>from_digest}\\]...to_digest..%{DATA>>to_digest}\\]...subject_digest..%{DATA>>subject_digest}\\]...module_type..%{DATA>>module_type}\\]...client_ip..%{DATA>>client_ip}\\]...', 'client_ip:$getGeo>>country,city', '', '', 'deliver邮件投递日志', 'mdate\ntime\nto_real_acct_name\nfrom_digest\nto_digest\nsubject_digest\nmodule_type\nclient_ip\ncountry\ncity', 1, '管理员', '2020-11-24 18:52:53', '2020-11-27 14:39:17');
INSERT INTO `rule` VALUES ('fbf594d4-799f-4def-9610-dba3f02b9f59', 'exchange_owa_rule', 'eec24794-8276-4a05-9836-6e2562dce4fb', '2020-07-30T01:59:59.340Z,b56ed1cf-ad5e-4aed-aa8f-f18ebd521c2a,15,0,1497,6,,Owa,mail.dfmwl.com,/owa/service.svc/s/GetFileAttachment,,FBA,true,DFMC\\zhonghuoxiong,,Sid~S-1-5-21-4223072487-2266339643-505866507-28265,Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML  like Gecko) Chrome/78.0.3904.108 Safari/537.36,183.40.239.89,DFMC-DMZCAS-02,200,200,,GET,Proxy,dfmc-mbx-03.dfmc.com.cn,15.00.1497.000,IntraForest,WindowsIdentity,Database~4cc8e612-9450-4f3b-a079-fedfd1508f13~~2020-08-`	29T01:59:56,,,0,18809,2,,0,1,,0,,0,,0,0,,0,23,0,,,,18,1,0,0,0,0,22,1,19,4,5,5,23,,?id=AQMkADYyOWJjMmUwLWNiMzQtNDU5Yy05NTQ1LTM4M2I1MDNhYjQ5MgBGAAADTLcs370%2FVE%2BHwnGcN2yvBAcAa31yO%2Fx7T0yynfcBEICJkQAAAgEMAAAAa31yO%2Fx7T0yynfcBEICJkQAD3w5A%2BgAAAAESABAAMoTfp9jisUOTtNEUR7Ykyg%3D%3D&X-OWA-CANARY=5zylHvhjXkq2csNtV3S55rpuGkIsNNgIqL1JZjZdZ6xY94sewRASe8Wh32H3PoNOIOUmr3PRWeo.,,BeginRequest=2020-07-30T01:59:59.320Z;CorrelationID=<empty>;ProxyState-Run=None;FEAuth=BEVersion-1941997017;BeginGetResponse=2020-07-30T01:59:59.320Z;OnResponseReady=2020-07-30T01:59:59.340Z;EndGetResponse=2020-07-30T01:59:59.340Z;ProxyState-Complete=ProxyResponseData;EndRequest=2020-07-30T01:59:59.340Z;,', 'Owa', '%{DATE>>mdate}T%{TIME>>time}...,...,..,...,...,...,...,...,%{DATA>>domain},...,...,...,...,%{DATA>>uid},...,...,...,%{DATA>>ip},...,%{DATA>>log_status},...', 'ip:$getGeo>>country,city', '', '', 'owa访问日志', 'mdate\ntime\ndomain\nuser\nip\nlogstatus\ncountry\ncity', 1, '管理员', '2020-11-25 10:19:14', '2020-11-26 17:37:33');

-- ----------------------------
-- Table structure for rule_group
-- ----------------------------
DROP TABLE IF EXISTS `rule_group`;
CREATE TABLE `rule_group`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `object_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分析对象id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则组名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `file_regex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件正则',
  `file_encode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件编码',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rule_group
-- ----------------------------
INSERT INTO `rule_group` VALUES ('025c01c3-e992-4ac7-913d-85af0a9e80f1', '9e55951b-4ae2-48b9-9058-880e8b6793df', 'weichai_ru', '', 'weichai_ru', 'utf-8', '管理员', '2020-10-30 16:33:30', NULL, 'weichai_ru');
INSERT INTO `rule_group` VALUES ('474194b9-3daf-48fc-b454-e0b40894ad03', '30f04e04-fa9a-4a5d-9ce6-2f0a795908b3', 'eYou_analytics', 'eYou_analytics邮箱操作日志', '.*analytics.*', 'utf-8', '管理员', '2020-11-21 14:08:14', '2020-11-25 14:51:30', 'eYou_analytics');
INSERT INTO `rule_group` VALUES ('4de3d49c-16c4-404b-8558-ad91b82e7b20', '30f04e04-fa9a-4a5d-9ce6-2f0a795908b3', 'eYou_del_mail', 'eYou_del_mail邮件删除日志', '.*del_mail.*', 'utf-8', '管理员', '2020-11-21 14:04:05', '2020-11-25 14:53:04', 'eYou_del_mail');
INSERT INTO `rule_group` VALUES ('52b50052-6a70-4fd1-a98d-c090b3a7f59c', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'exchange_EWS', 'exchange_EWS访问日志', '.*', 'utf-8', '管理员', '2020-11-21 14:15:39', '2020-11-25 14:50:46', 'exchange_EWS');
INSERT INTO `rule_group` VALUES ('5ba02513-f2be-4b09-8684-9769baaa6224', '81fe2eeb-f111-4350-b9e2-918b6c780eb6', 'lin_ana_1126_rg', '', '.*', 'utf-8', '管理员', '2020-11-26 11:22:27', NULL, 'lin_ana_1126_rg');
INSERT INTO `rule_group` VALUES ('6409fdc2-db42-447e-8480-da1a65a4d183', '9e9b18d7-2cf8-464d-ae17-0a479b92d87d', 'test_coremail_wmsvr_2', '', '.*wmsvr.log.%{ALLDATA>>mdate}', 'utf-8', '管理员', '2020-11-26 16:06:27', '2020-11-26 16:13:49', 'test_coremail_wmsvr_2');
INSERT INTO `rule_group` VALUES ('817e4fcf-198c-40c2-8831-f30d1121bf5d', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'imap', '', '.*?imapsvr\\.log\\.%{DATE>>date}$', 'utf-8', '管理员', '2020-09-08 11:53:23', '2020-09-19 13:53:47', 'imap');
INSERT INTO `rule_group` VALUES ('9b345b92-a2a3-43bd-867b-57dd1cc1ee03', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'wms', 'core_mail_wms', '.*?wmsvr\\.log\\.%{DATE>>date}$', 'utf-8', '管理员', '2020-09-08 10:19:59', '2020-09-19 13:43:57', 'wms');
INSERT INTO `rule_group` VALUES ('9f340582-b57d-415d-91f3-4d33e26632fe', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'exchange_ECP', 'exchange_ECP访问日志', '.*', 'utf-8', '管理员', '2020-11-21 14:14:59', '2020-11-25 14:50:53', 'exchange_ECP');
INSERT INTO `rule_group` VALUES ('ab82c65e-a34c-4ca6-b23e-daaa1930d417', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'coremail_wmsvr', 'coremail_wmsvr邮服操作日志', '.* wmsvr.log.%{DATE>>mdate}', 'utf-8', '管理员', '2020-11-21 14:18:18', '2020-11-26 14:30:24', 'coremail_wmsvr');
INSERT INTO `rule_group` VALUES ('ad54a206-1b7c-43b6-9fb5-1e47b102b853', '9e9b18d7-2cf8-464d-ae17-0a479b92d87d', 'test_coremail_wmsvr', '', '.*wmsvr.log.%{ALLDATA>>mdate}', 'utf-8', '管理员', '2020-11-25 16:35:40', '2020-11-26 16:13:59', 'test_coremail_wmsvr');
INSERT INTO `rule_group` VALUES ('bb46f15a-9594-4d9c-b64f-e42c66d1991e', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'coremail_webadmin', 'coremail_webadminweb登录日志', '.*webadmin.*', 'utf-8', '管理员', '2020-11-21 14:17:03', '2020-11-25 14:50:37', 'coremail_webadmin');
INSERT INTO `rule_group` VALUES ('bc0820bc-4715-4293-bc20-eed07c52e163', 'ceea0874-2b89-467d-b6c7-418bc14eca81', 'pop3', '', '.*?pop3svr\\.log\\.%{DATE>>date}$', 'latin1', '管理员', '2020-09-08 11:51:14', '2020-09-19 13:43:57', 'pop3');
INSERT INTO `rule_group` VALUES ('bee22b3e-86e1-4baf-a27f-50ec413d0b3e', '30f04e04-fa9a-4a5d-9ce6-2f0a795908b3', 'eYou_ nginx_access', 'eYou_ nginx访问日志', '.* nginx_access.*', 'utf-8', '管理员', '2020-11-21 14:09:34', '2020-11-25 14:51:23', 'nginx_access');
INSERT INTO `rule_group` VALUES ('cfca3898-3b74-47a9-ab06-8d15c3520468', '4faca7ac-9976-4151-bf2c-8a80bf307a59', 'iis', 'iis访问日志', '.*', 'utf-8', '管理员', '2020-11-25 09:41:53', '2020-11-26 17:40:52', 'iis');
INSERT INTO `rule_group` VALUES ('d2747bbb-7b43-46b6-9c0a-435e2e0d95a6', '03a11322-d482-4f70-8253-0365d60c9ce5', 'lin_rg_1112_2', '', '.*', 'utf-8', '管理员', '2020-11-12 11:42:13', '2020-11-13 17:25:42', 'lin_rg_1112_2');
INSERT INTO `rule_group` VALUES ('d3110eeb-fe40-4288-aaee-fe5b25075f5c', '30f04e04-fa9a-4a5d-9ce6-2f0a795908b3', 'eYou_ auth', 'eYou_ auth邮箱登录日志', '.* auth.*', 'latin1', '管理员', '2020-11-21 14:06:52', '2020-11-25 14:52:51', 'eYou_ auth');
INSERT INTO `rule_group` VALUES ('d482c007-7005-41df-9654-cdd7d4bf0643', '30f04e04-fa9a-4a5d-9ce6-2f0a795908b3', 'eYou_deliver_mail', 'eYou_deliver_mail邮件投递日志', '.*deliver_mail.*', 'latin1', '管理员', '2020-11-21 14:02:42', '2020-11-25 14:53:12', 'eYou_deliver_mail');
INSERT INTO `rule_group` VALUES ('d9f25923-140f-404f-9aa9-b3b227a36c16', '03a11322-d482-4f70-8253-0365d60c9ce5', 'lin_rg_1124_4', '', '.*', 'utf-8', '管理员', '2020-11-12 10:38:04', '2020-11-24 16:53:54', 'lin_rg_1124_4');
INSERT INTO `rule_group` VALUES ('dfdcf2ff-6986-4043-b935-98b68721e276', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_rg_1119_imapsvr', '', '.*imapsvr.log.%{DATE>>mdate}', 'latin1', '管理员', '2020-11-19 11:38:44', '2020-12-10 21:05:42', 'weichai_rg_1119_imapsvr');
INSERT INTO `rule_group` VALUES ('e4b79b7c-09bd-4991-b7c4-0e4c8d3585f4', '27e1524f-f590-4187-add4-3a5f14a1723a', 'weichai_access_rg_1119', '邮服WEB日志', '.*log-%{DATE>>mdate}', 'utf-8', '管理员', '2020-11-19 14:23:13', '2020-11-21 14:09:49', 'weichai_access_rg_1119');
INSERT INTO `rule_group` VALUES ('ee3989c4-6a7a-4290-9926-f8395a6e8103', '9e9b18d7-2cf8-464d-ae17-0a479b92d87d', 'test_coremail_webadmin', '', '.*webadmin.log.%{ALLDATA>>mdate}', 'utf-8', '管理员', '2020-11-25 16:26:35', '2020-11-25 16:57:48', 'test_coremail_webadmin');
INSERT INTO `rule_group` VALUES ('eec24794-8276-4a05-9836-6e2562dce4fb', '7e041e1f-4886-4b49-9a64-58431788c11c', 'exchange_OWA', 'exchange_OWA访问日志', '.*', 'utf-8', '管理员', '2020-11-21 14:13:56', '2020-11-25 14:51:08', 'exchange_OWA');

-- ----------------------------
-- Table structure for unit
-- ----------------------------
DROP TABLE IF EXISTS `unit`;
CREATE TABLE `unit`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位中文名',
  `ename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位英文名',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit
-- ----------------------------
INSERT INTO `unit` VALUES ('2317bc45-ddcb-493f-b0a0-5a231df8c90e', 'test_unit', 'test_unit', '', '', '', '', '管理员', '2020-12-14 01:13:17', NULL);
INSERT INTO `unit` VALUES ('5e2828fe-ef72-4458-8525-849d89118e83', 'weichai_1211', 'weichai_1211', '', '', '', '', '管理员', '2020-12-10 20:15:28', NULL);

-- ----------------------------
-- Table structure for unit_analy_object
-- ----------------------------
DROP TABLE IF EXISTS `unit_analy_object`;
CREATE TABLE `unit_analy_object`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `unit_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位id',
  `object_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分析对象id',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of unit_analy_object
-- ----------------------------
INSERT INTO `unit_analy_object` VALUES ('0bdf679c-a4ee-4718-af75-2594b1c800b7', '5e2828fe-ef72-4458-8525-849d89118e83', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', '2020-12-10 21:11:40', '2020-12-10 21:11:40');
INSERT INTO `unit_analy_object` VALUES ('39641158-3fda-4138-b060-0ef3509e80ea', '2317bc45-ddcb-493f-b0a0-5a231df8c90e', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', '2020-12-14 01:18:53', '2020-12-14 01:18:53');

-- ----------------------------
-- Table structure for used_rule
-- ----------------------------
DROP TABLE IF EXISTS `used_rule`;
CREATE TABLE `used_rule`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `use_rule_group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规则名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `log_data` varchar(1500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志数据',
  `log_feature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志特征',
  `extract_rule` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提取规则',
  `switch_rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '转换规则',
  `supplement_rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '补充规则',
  `replace_rule` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '替换规则',
  `is_enabled` tinyint(4) NULL DEFAULT NULL,
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of used_rule
-- ----------------------------
INSERT INTO `used_rule` VALUES ('0627d472-2a85-4886-9802-dca20edede72', '724ed90e-efce-4fc1-b00c-0ae816020b9e', '121ca738-577f-41e8-85be-41009b2af580', '94d60ba3-76ab-4499-b1d4-787a746dde9a', 'rule1', '', NULL, '', '%{DATA>>mdate}-%{TIME>>time}\\sU:%{DATA>>user}\\s...C:%{DATA>>req_ip}\\s.*', 'mdate:$parseDate(\'%Y%m%d\')>>mdate;req_ip:$getGeo>>country,city;user:$addStr(\'@jiuzhou.com.cn\')>>user', '{ \"opt\": \"login\", \"type\": \"HTTP\",\"result\":\"success\" }', '', 1, NULL, '2020-10-20 17:53:04', '2020-10-20 17:53:04');
INSERT INTO `used_rule` VALUES ('12bbf3c1-7335-43c8-b2e7-7fb41d97be81', 'd6c8e17d-e233-428d-a199-fd0da749149b', 'dfdcf2ff-6986-4043-b935-98b68721e276', '78f2b143-5c9d-4ab0-8548-0993b8857631', 'weichai_rg_1119_imapsvr_1', '', 'T:2558260992(00:00:07)[root-I011mXkAAIdfwV6dtj0A:Info] User djyzhixab@weichai.com from [112.224.69.113] login fail', 'login', '.*\\(%{TIME>>time}...User\\s%{DATA>>username}\\sfrom\\s\\[%{IPV4>>ip}...login\\s%{ALLDATA>>result}', 'ip:$getGeo>>country,city', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', '', 1, NULL, '2020-12-10 21:45:16', '2020-12-10 21:45:16');
INSERT INTO `used_rule` VALUES ('2e3092f7-04c2-4cc3-9573-ba07448de0e2', '1bb29854-dc70-4b38-97a0-1017674d167e', 'dfdcf2ff-6986-4043-b935-98b68721e276', '78f2b143-5c9d-4ab0-8548-0993b8857631', 'weichai_rg_1119_imapsvr_1', '', 'T:2558260992(00:00:07)[root-I011mXkAAIdfwV6dtj0A:Info] User djyzhixab@weichai.com from [112.224.69.113] login fail', 'login', '.*\\(%{TIME>>time}...User\\s%{DATA>>username}\\sfrom\\s\\[%{IPV4>>ip}...login\\s%{ALLDATA>>result}', 'ip:$getGeo>>country,city', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', '', 1, NULL, '2020-12-14 01:18:53', '2020-12-14 01:18:53');
INSERT INTO `used_rule` VALUES ('3d288019-3a77-4cf7-812e-4d2592982135', 'e680a0c5-85fe-4729-a89b-377c28965fbd', 'ca712a03-ec1b-4bc8-b97d-adb6a7307aff', '28b5edd0-5961-44af-abe5-3d8377435af6', 'haihangrule1', '', NULL, '', '%{DATE>>date},%{TIME>>time},%{DATA>>user},%{IPV4>>ip},...,%{ALLDATA>>status}', 'ip:$getGeo>>country,city', '{}', 'status==200>>status=success;status==206>>status=success', 1, NULL, '2020-11-03 14:15:36', '2020-11-03 14:15:36');
INSERT INTO `used_rule` VALUES ('56fc11be-1e7c-497d-be7b-ea31158bacc9', 'ee31daef-8103-4c0d-b320-d99d3debeee4', '025c01c3-e992-4ac7-913d-85af0a9e80f1', '9511cfec-38d1-4732-b8c2-a92854d04ce2', 'weichai_rule', '', NULL, '', '%{IPV4>>req_ip}\\s.%{DATA>>mdate}:%{DATA>>time}\\s[\\S]{1,6}\\s%{ALLDATA>>username}', 'req_ip:$getGeo>>country,city;mdate:$parseDateweichai(\'%Y%m%d\')>>mdate', '{\"opt\":\"login\",\"result\":\"success\",\"type\":\"http\"}', '', 1, NULL, '2020-11-03 14:15:36', '2020-11-03 14:15:36');
INSERT INTO `used_rule` VALUES ('7fff1930-8218-40bd-958c-e2c9a61e3001', '3f3f5c18-dd5a-42dc-9e37-7dc79cd3d8d4', 'dfdcf2ff-6986-4043-b935-98b68721e276', '78f2b143-5c9d-4ab0-8548-0993b8857631', 'weichai_rg_1119_imapsvr_1', '', 'T:2558260992(00:00:07)[root-I011mXkAAIdfwV6dtj0A:Info] User djyzhixab@weichai.com from [112.224.69.113] login fail', 'login', '.*\\(%{TIME>>time}...User\\s%{DATA>>username}\\sfrom\\s\\[%{IPV4>>ip}...login\\s%{ALLDATA>>result}', 'ip:$getGeo>>country,city', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', '', 1, NULL, '2020-12-14 01:15:02', '2020-12-14 01:15:02');
INSERT INTO `used_rule` VALUES ('9aeab662-9051-40f9-baba-ef7bb8def34b', '7b927e0e-66aa-46c0-bfcd-88dda27d3bb1', 'dfdcf2ff-6986-4043-b935-98b68721e276', '78f2b143-5c9d-4ab0-8548-0993b8857631', 'weichai_rg_1119_imapsvr_1', '', 'T:2558260992(00:00:07)[root-I011mXkAAIdfwV6dtj0A:Info] User djyzhixab@weichai.com from [112.224.69.113] login fail', 'login', '.*\\(%{TIME>>time}...User\\s%{DATA>>username}\\sfrom\\s\\[%{IPV4>>ip}...login\\s%{ALLDATA>>result}', 'ip:$getGeo>>country,city', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', '', 1, NULL, '2020-12-10 21:43:24', '2020-12-10 21:43:24');
INSERT INTO `used_rule` VALUES ('eaea40d5-357d-4a3d-9c6e-9d8c15e7cf22', '55d944e8-5133-4ba4-93e8-b24a5c02abfd', '41d09398-3f2c-4d5e-a9e4-fce87ea5eae9', 'd1690e8b-a085-4685-98c0-734303b13720', 'eap_rule1', '', NULL, '', '%{DATA>>mdate}-%{TIME>>time}\\\\sU:%{DATA>>user}\\\\s...C:%{DATA>>req_ip}\\\\s', 'mdate:$parseDate(\'%Y%m%d\')>>mdate;req_ip:$getGeo>>country,city;user:$addStr(\'@jiuzhou.com.cn\')>>user', '{ \"opt\": \"login\", \"type\": \"HTTP\",\"result\":\"success\" }', '', 1, NULL, '2020-10-20 17:34:45', '2020-10-20 17:34:45');
INSERT INTO `used_rule` VALUES ('fa822b82-3f37-48fc-85c6-7fa612b482a1', '2706007e-2f16-40d4-9992-143dd5c16231', 'dfdcf2ff-6986-4043-b935-98b68721e276', '78f2b143-5c9d-4ab0-8548-0993b8857631', 'weichai_rg_1119_imapsvr_1', '', 'T:2558260992(00:00:07)[root-I011mXkAAIdfwV6dtj0A:Info] User djyzhixab@weichai.com from [112.224.69.113] login fail', 'login', '.*\\(%{TIME>>time}...User\\s%{DATA>>username}\\sfrom\\s\\[%{IPV4>>ip}...login\\s%{ALLDATA>>result}', 'ip:$getGeo>>country,city', '{ \"opt\": \"login\", \"type\": \"HTTP\" }', '', 1, NULL, '2020-12-10 21:25:38', '2020-12-10 21:25:38');

-- ----------------------------
-- Table structure for used_rule_group
-- ----------------------------
DROP TABLE IF EXISTS `used_rule_group`;
CREATE TABLE `used_rule_group`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `project_analysis_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `object_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `input_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_regex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_encode` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `output_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(4) NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of used_rule_group
-- ----------------------------
INSERT INTO `used_rule_group` VALUES ('1bb29854-dc70-4b38-97a0-1017674d167e', '74825323-e794-4ac8-a343-5cebfdb5eb35', 'dfdcf2ff-6986-4043-b935-98b68721e276', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_rg_1119_imapsvr', 'weichai_rg_1119_imapsvr', '/data/data0/weichai_data/coremail/home/coremail/logs/', '', '.*imapsvr.log.%{DATE>>mdate}', 'latin1', NULL, 0, '2020-12-14 01:18:53', '2020-12-14 01:18:53');
INSERT INTO `used_rule_group` VALUES ('2706007e-2f16-40d4-9992-143dd5c16231', '19588981-59bb-490b-a646-82c1b3735db5', 'dfdcf2ff-6986-4043-b935-98b68721e276', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_rg_1119_imapsvr', 'weichai_rg_1119_imapsvr', '/data/data0/weichai_data/coremail/home/coremail/logs/', '', '.*imapsvr.log.%{DATE>>mdate}', 'latin1', NULL, 0, '2020-12-10 21:25:38', '2020-12-10 21:25:38');
INSERT INTO `used_rule_group` VALUES ('3f3f5c18-dd5a-42dc-9e37-7dc79cd3d8d4', 'c6731fcf-f7f2-4a95-b8e8-616e156febf2', 'dfdcf2ff-6986-4043-b935-98b68721e276', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_rg_1119_imapsvr', 'weichai_rg_1119_imapsvr', '/data/data0/weichai_data/coremail/home/coremail/logs/', '', '.*imapsvr.log.%{DATE>>mdate}', 'latin1', NULL, 0, '2020-12-14 01:15:02', '2020-12-14 01:15:02');
INSERT INTO `used_rule_group` VALUES ('55d944e8-5133-4ba4-93e8-b24a5c02abfd', '3a57cad3-fa4c-4fec-9737-5333af2dfedf', '41d09398-3f2c-4d5e-a9e4-fce87ea5eae9', '147a0310-c7eb-4644-9552-31b216101044', 'eap_rg1', 'eap_rg1', '/opt/hxht/logimporter/testData/weblogin/', '', '.*', 'utf-8', NULL, 0, '2020-10-20 17:34:45', '2020-10-20 17:34:45');
INSERT INTO `used_rule_group` VALUES ('724ed90e-efce-4fc1-b00c-0ae816020b9e', 'cb7aca84-c1d4-4cc4-a794-b4aa92ed0014', '121ca738-577f-41e8-85be-41009b2af580', 'd65b55a9-fbe2-40f1-ba04-fb12871a6c4d', 'rulegroup', 'rulegroup', '/opt/hxht/logimporter/testData/weblogin/', '', '.*', 'utf-8', NULL, 0, '2020-10-20 17:53:04', '2020-10-20 17:53:04');
INSERT INTO `used_rule_group` VALUES ('7b927e0e-66aa-46c0-bfcd-88dda27d3bb1', 'aee6ca07-35f8-4158-8960-fab0d4d46a4f', 'dfdcf2ff-6986-4043-b935-98b68721e276', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_rg_1119_imapsvr', 'weichai_rg_1119_imapsvr', '/data/data0/weichai_data/coremail_36_11/home/coremail/logs/', '', '.*imapsvr.log.%{DATE>>mdate}', 'latin1', NULL, 0, '2020-12-10 21:43:24', '2020-12-10 21:43:24');
INSERT INTO `used_rule_group` VALUES ('d6c8e17d-e233-428d-a199-fd0da749149b', 'd9d8caed-067c-4529-a5ac-42fa9408d9ca', 'dfdcf2ff-6986-4043-b935-98b68721e276', '378fb5d7-196c-4cdf-acc0-2a0efb5fe451', 'weichai_rg_1119_imapsvr', 'weichai_rg_1119_imapsvr', '/data/data1/weichai_data/coremail_6_25/home/coremail/logs/', '', '.*imapsvr.log.%{DATE>>mdate}', 'latin1', NULL, 0, '2020-12-10 21:45:16', '2020-12-10 21:45:16');
INSERT INTO `used_rule_group` VALUES ('e680a0c5-85fe-4729-a89b-377c28965fbd', 'edb4c2e8-230a-4a0b-8e23-8478ab5b38b9', 'ca712a03-ec1b-4bc8-b97d-adb6a7307aff', '1634fb58-0a76-4dbf-ae7d-413438588afb', 'haihang_group', 'haihang_group', '/log/weichai/', '', 'haihang_group', 'utf-8', NULL, 0, '2020-11-03 14:15:36', '2020-11-03 14:15:36');
INSERT INTO `used_rule_group` VALUES ('ee31daef-8103-4c0d-b320-d99d3debeee4', 'c97638ab-c16b-49e9-a86a-24a6f2addaab', '025c01c3-e992-4ac7-913d-85af0a9e80f1', '9e55951b-4ae2-48b9-9058-880e8b6793df', 'weichai_ru', 'weichai_ru', NULL, '', 'weichai_ru', 'utf-8', NULL, 0, '2020-11-03 14:15:36', '2020-11-03 14:15:36');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `true_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pass_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_login_time` datetime(0) NULL DEFAULT NULL,
  `last_token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key_value` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `active` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否生效',
  `safe_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '安全级别',
  `salt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐值',
  PRIMARY KEY (`_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('05cc5729-bf45-11e9-ad7b-0242ac110003', 'user', '业务员', '5C6334DC87CAEEB01407418DA1725248', NULL, NULL, 0, '2019-08-15 18:11:49', '超管', '2020-06-10 16:36:13', NULL, NULL, '1', '公开', '05cc5729-bf45-11e9-ad7b-0242ac110003');
INSERT INTO `user` VALUES ('2c3274d1-bf27-11e9-ad7b-0242ac110003', 'superadmin', '管理员', '273045DD8E165A3F7AFA018D31BEC706', NULL, NULL, 0, '2019-08-15 14:38:08', '王法强', '2020-06-12 13:54:08', '管理员', NULL, '1', '保密', '2c3274d1-bf27-11e9-ad7b-0242ac110003');

SET FOREIGN_KEY_CHECKS = 1;
