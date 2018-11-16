--
-- Table structure for table `tdh_basic_valuation`
--

DROP TABLE IF EXISTS `tdh_basic_valuation`;
CREATE TABLE `tdh_basic_valuation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `model_id` varchar(32) DEFAULT NULL COMMENT '车型标识',
  `reg_date` varchar(32) DEFAULT NULL COMMENT '待估车辆的上牌时间',
  `mile` double DEFAULT NULL COMMENT '待估车辆的公里数(单位万公里)',
  `zone` varchar(32) DEFAULT NULL COMMENT '城市标识',
  `eval_price` varchar(32) DEFAULT NULL COMMENT '评估价格',
  `low_price` varchar(32) DEFAULT NULL COMMENT '最低价',
  `good_price` varchar(32) DEFAULT NULL COMMENT '最优价',
  `high_price` varchar(32) DEFAULT NULL COMMENT '最高价',
  `dealer_buy_price` varchar(32) DEFAULT NULL COMMENT '车商收购价',
  `individual_price` varchar(32) DEFAULT NULL COMMENT '个人交易价',
  `dealer_price` varchar(32) DEFAULT NULL COMMENT '车商零售价',
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `price` varchar(32) DEFAULT NULL COMMENT '新车售价',
  `discharge_standard` varchar(32) DEFAULT NULL COMMENT '排放标准',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `car_logo_url` varchar(255) DEFAULT NULL COMMENT '汽车标志网址',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础估值';

--
-- Table structure for table `tdh_city_list`
--

DROP TABLE IF EXISTS `tdh_city_list`;
CREATE TABLE `tdh_city_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `city_id` int(11) DEFAULT NULL COMMENT '城市ID',
  `city_name` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `prov_id` int(11) DEFAULT NULL COMMENT '所属省份ID',
  `prov_name` varchar(32) DEFAULT NULL COMMENT '所属省份名称',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市列表';

--
-- Table structure for table `tht_brand`
--

DROP TABLE IF EXISTS `tht_brand`;
CREATE TABLE `tht_brand` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌标识',
  `type` varchar(4) DEFAULT NULL COMMENT '品牌类型（1接口导入,2用户新增）',
  `letter` varchar(32) DEFAULT NULL COMMENT '字母序号',
  `logo` varchar(255) DEFAULT NULL COMMENT 'logo',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `description` text COMMENT '品牌介绍',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `update_datetime` datetime DEFAULT NULL COMMENT '最新修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`) COMMENT '品牌'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tht_car`
--

DROP TABLE IF EXISTS `tht_car`;
CREATE TABLE `tht_car` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `series_id` int(11) DEFAULT NULL COMMENT '车系标识',
  `model_id` int(11) DEFAULT NULL COMMENT '车型标识',
  `type` varchar(4) DEFAULT NULL COMMENT '车型类型（1接口导入,2用户新增）',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `series_code` varchar(32) DEFAULT NULL COMMENT '车系编号',
  `series_name` varchar(255) DEFAULT NULL COMMENT '车系名称',
  `brand_code` varchar(32) DEFAULT NULL COMMENT '品牌编号',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '品牌名称',
  `original_price` bigint(32) DEFAULT NULL COMMENT '原价',
  `sale_price` varchar(255) DEFAULT NULL COMMENT '参考价',
  `model_year` varchar(32) DEFAULT NULL COMMENT '年款',
  `min_reg_year` varchar(32) DEFAULT NULL COMMENT ' 最小上牌年份',
  `max_reg_year` varchar(32) DEFAULT NULL COMMENT '最大上牌年份',
  `liter` varchar(255) DEFAULT NULL COMMENT '排量',
  `gear_type` varchar(255) DEFAULT NULL COMMENT '变速箱',
  `discharge_standard` varchar(255) DEFAULT NULL COMMENT '排放标准',
  `seat_number` varchar(32) DEFAULT NULL COMMENT '座位数',
  `sf_amount` bigint(20) DEFAULT NULL COMMENT '首付金额',
  `location` int(11) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI次序',
  `slogan` text COMMENT '广告语',
  `adv_pic` varchar(255) DEFAULT NULL COMMENT '广告图',
  `pic` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `description` text COMMENT '图文描述',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `update_datetime` datetime DEFAULT NULL COMMENT '最新修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`) COMMENT '车型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tht_series`
--
DROP TABLE IF EXISTS `tht_series`;
CREATE TABLE `tht_series` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌标识',
  `series_id` int(11) DEFAULT NULL COMMENT '车系标识',
  `type` varchar(4) DEFAULT NULL COMMENT '车系类型（1接口导入,2用户新增）',
  `maker_type` varchar(255) DEFAULT NULL COMMENT '制造商类型',
  `brand_code` varchar(32) DEFAULT NULL COMMENT '品牌编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `series_group_name` varchar(255) DEFAULT NULL COMMENT '系列组名',
  `slogan` text COMMENT '广告语',
  `adv_pic` varchar(255) DEFAULT NULL COMMENT '广告图',
  `price` bigint(20) DEFAULT NULL COMMENT '价格区间',
  `location` int(11) DEFAULT NULL COMMENT 'UI位置',
  `order_no` int(11) DEFAULT NULL COMMENT 'UI次序',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `update_datetime` datetime DEFAULT NULL COMMENT '最新修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`) COMMENT '车系'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tsys_config`
--
DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(96) DEFAULT NULL,
  `ckey` varchar(765) DEFAULT NULL,
  `cvalue` text,
  `update_datetime` datetime DEFAULT NULL,
  `remark` varchar(765) DEFAULT NULL,
  `company_code` varchar(96) DEFAULT NULL,
  `system_code` varchar(96) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

