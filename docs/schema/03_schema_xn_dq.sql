
/*请款预算单*/
DROP TABLE IF EXISTS `tdq_req_budget`;
CREATE TABLE `tdq_req_budget` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '业务公司编号',
  `receipt_bank` varchar(255) DEFAULT NULL COMMENT '收款银行',
  `receipt_account` varchar(255) DEFAULT NULL COMMENT '收款账号',
  `budget_amount` bigint(20) DEFAULT NULL COMMENT '预算金额',
  
  `use_datetime` datetime DEFAULT NULL COMMENT '用款日期',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `finance_check_note` text COMMENT '财务审核说明',
  `pay_amount` bigint(255) DEFAULT NULL COMMENT '打款金额',
  
  `pay_bank` varchar(255) DEFAULT NULL COMMENT '打款银行',
  `pay_account` varchar(255) DEFAULT NULL COMMENT '打款账号',
  `water_bill` varchar(255) DEFAULT NULL COMMENT '水单',
  `pay_datetime` datetime DEFAULT NULL COMMENT '打款时间',
  `pay_remark` text COMMENT '打款备注',
  
  `dz_amount` varchar(255) DEFAULT NULL COMMENT '垫资总额',
  `dz_datetime` datetime DEFAULT NULL COMMENT '垫资日期',
  `collection_bank` varchar(255) DEFAULT NULL COMMENT '收回款银行',
  `collection_amount` bigint(20) DEFAULT NULL COMMENT '收回款金额',
  `collection_account` varchar(255) DEFAULT NULL COMMENT '收回款账号',
  
  `collection_datetime` datetime DEFAULT NULL COMMENT '收回款日期',
  `collection_remark` text COMMENT '收回款备注',
  `cur_node_code` varchar(32) DEFAULT NULL COMMENT '节点编号',
  PRIMARY KEY (`code`) COMMENT '请款预算单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdq_budget_order`;
CREATE TABLE `tdq_budget_order` (
  `code` varchar(32) NOT NULL COMMENT '预算单编号',
  `customer_type` varchar(4) DEFAULT NULL COMMENT '客户类型',
  `customer_name` varchar(32) DEFAULT NULL COMMENT '客户姓名',
  `car_dealer_code` varchar(32) DEFAULT NULL COMMENT '汽车经销商编号',
  `loan_bank_code` varchar(32) DEFAULT NULL COMMENT '贷款银行编号',
  `loan_bank_subbranch` tinytext DEFAULT NULL COMMENT '贷款银行经办支行',
  `original_price` bigint(20) DEFAULT NULL COMMENT '厂商指导价',
  `car_model` varchar(32) DEFAULT NULL COMMENT '车辆型号',
  `loan_periods` int(11) DEFAULT NULL COMMENT '贷款周期',
  `invoice_price` bigint(20) DEFAULT NULL COMMENT '发票价格',
  `shop_way` varchar(32) DEFAULT NULL COMMENT '购车途径',
  `rate_type` varchar(4) DEFAULT NULL COMMENT '利率类型',
  `loan_amount` bigint(20) DEFAULT NULL COMMENT '贷款金额',
  `is_survey` varchar(4) DEFAULT NULL COMMENT '是否需要贷前调查',
  `bank_rate` decimal(18,8) DEFAULT NULL COMMENT '银行利率',
  `company_loan_cs` decimal(18,8) DEFAULT NULL COMMENT '我司贷款成数',
  `is_advance_fund` varchar(4) DEFAULT NULL COMMENT '是否垫资',
  `global_rate` decimal(18,8) DEFAULT NULL COMMENT '综合利率',
  `fee` bigint(20) DEFAULT NULL COMMENT '手续费',
  `car_dealer_subsidy` bigint(20) DEFAULT NULL COMMENT '汽车经销商厂家贴息',
  `bank_loan_cs` decimal(18,8) DEFAULT NULL COMMENT '银行贷款成数',
  `company_code` tinytext DEFAULT NULL COMMENT '公司编号',
  `sale_user_id` varchar(32) DEFAULT NULL COMMENT '业务员编号',
  `apply_user_company` tinytext DEFAULT NULL COMMENT '申请人就职单位',
  `apply_user_duty` tinytext DEFAULT NULL COMMENT '申请人职位',
  `apply_user_ghr_relation` tinytext DEFAULT NULL COMMENT '申请人与共还人关系',
  `marry_state` varchar(32) DEFAULT NULL COMMENT '婚姻状况',
  `other_income_note` tinytext DEFAULT NULL COMMENT '其他收入说明',
  `is_house_property` varchar(4) DEFAULT NULL COMMENT '房产证情况',
  `house_property` tinytext DEFAULT NULL COMMENT '房产证',
  `is_house_contract` varchar(4) DEFAULT NULL COMMENT '有无购房合同',
  `house_contract` tinytext DEFAULT NULL COMMENT '购房合同',
  `house_invoice` tinytext DEFAULT NULL COMMENT '购房发票',
  `is_license` varchar(4) DEFAULT NULL COMMENT '营业执照情况',
  `license` tinytext DEFAULT NULL COMMENT '营业执照',
  `is_site_prove` varchar(4) DEFAULT NULL COMMENT '提供场地证明',
  `site_prove` tinytext DEFAULT NULL COMMENT '场地证明',
  `site_area` tinytext DEFAULT NULL COMMENT '经营场地面积',
  `car_type` varchar(4) DEFAULT NULL COMMENT '现有车辆类型',
  `is_drice_license` varchar(4) DEFAULT NULL COMMENT '有无驾照',
  `drice_license` tinytext DEFAULT NULL COMMENT '驾照',
  `other_property_note` tinytext DEFAULT NULL COMMENT '其他资产说明',
  `apply_birth_address` tinytext DEFAULT NULL COMMENT '申请人户籍地',
  `apply_now_address` tinytext DEFAULT NULL COMMENT '现住地址',
  `house_type` varchar(4) DEFAULT NULL COMMENT '现住房屋类型',
  `gh_birth_address` tinytext DEFAULT NULL COMMENT '共还人户籍地',
  `guarantor1_birth_address` tinytext DEFAULT NULL COMMENT '担保1户籍地',
  `guarantor2_birth_address` tinytext DEFAULT NULL COMMENT '担保2户籍地',
  `other_note` tinytext DEFAULT NULL COMMENT '其他情况说明',
  `oil_subsidy` bigint(20) DEFAULT NULL COMMENT '油补',
  `oil_subsidy_kil` decimal(18,8) DEFAULT NULL COMMENT '油补公里数',
  `is_plat_insure` char(1) DEFAULT NULL COMMENT '是否我司续保',
  `gps_fee` bigint(20) DEFAULT NULL COMMENT 'GPS收费',
  `gps_deduct` bigint(20) DEFAULT NULL COMMENT 'GPS提成',
  `gps_fee_way` varchar(4) DEFAULT NULL COMMENT 'GPS收费方式',
  `ly_amount` bigint(20) DEFAULT NULL COMMENT '履约保证金',
  `fx_amount` bigint(20) DEFAULT NULL COMMENT '担保风险金',
  `other_fee` bigint(20) DEFAULT NULL COMMENT '杂费',
  `fee_way` varchar(4) DEFAULT NULL COMMENT '手续费收取方式',
  `marry_divorce` tinytext DEFAULT NULL COMMENT '结婚证离婚证',
  `apply_user_hkb` tinytext DEFAULT NULL COMMENT '户口本(主贷本人页)',
  `bank_bill_pdf` tinytext DEFAULT NULL COMMENT '银行流水',
  `single_prove_pdf` tinytext DEFAULT NULL COMMENT '单身证明',
  `income_prove_pdf` tinytext DEFAULT NULL COMMENT '收入证明',
  `live_prove_pdf` tinytext DEFAULT NULL COMMENT '居住证明',
  `build_prove_pdf` tinytext DEFAULT NULL COMMENT '自建房证明',
  `hkb_first_page` tinytext DEFAULT NULL COMMENT '户口本（首页）',
  `hkb_main_page` tinytext DEFAULT NULL COMMENT '户口本（户主页）',
  `gh_hkb` tinytext DEFAULT NULL COMMENT '共还人户口本',
  `guarantor1_id_no` tinytext DEFAULT NULL COMMENT '担保人1身份证',
  `guarantor1_hkb` tinytext DEFAULT NULL COMMENT '担保人1户口本',
  `guarantor2_id_no` tinytext DEFAULT NULL COMMENT '担保人2身份证',
  `guarantor2_hkb` tinytext DEFAULT NULL COMMENT '担保人2户口本',
  `house_pic` tinytext DEFAULT NULL COMMENT '小区外观',
  `house_unit_pic` tinytext DEFAULT NULL COMMENT '单元楼照片',
  `house_door_pic` tinytext DEFAULT NULL COMMENT '门牌照片',
  `house_room_pic` tinytext DEFAULT NULL COMMENT '客厅照片',
  `house_customer_pic` tinytext DEFAULT NULL COMMENT '主贷与住宅合影',
  `house_sale_customer_pic` tinytext DEFAULT NULL COMMENT '签约员与客户在房子合影照片',
  `company_name_pic` tinytext DEFAULT NULL COMMENT '企业名称照片',
  `company_place_pic` tinytext DEFAULT NULL COMMENT '办公场地照片',
  `company_workshop_pic` tinytext DEFAULT NULL COMMENT '生产车间照片',
  `company_sale_customer_pic` tinytext DEFAULT NULL COMMENT '签约员与客户在公司合影照片',
  `second_hgz` tinytext DEFAULT NULL COMMENT '二手车合格证',
  `second_odometer` tinytext DEFAULT NULL COMMENT '里程表',
  `second_car_front_pic` tinytext DEFAULT NULL COMMENT '车前正面照',
  `second_console_pic` tinytext DEFAULT NULL COMMENT '中控台',
  `second_300_pdf` tinytext DEFAULT NULL COMMENT '车300评估页',
  `second_qxb_pic` tinytext DEFAULT NULL COMMENT '汽修宝截图',
  `second_car_in_pic` tinytext DEFAULT NULL COMMENT '车内饰',
  `second_number` tinytext DEFAULT NULL COMMENT '铭牌',
  `other_file_pdf` tinytext DEFAULT NULL COMMENT '其他材料附件',
  `other_apply_note` tinytext DEFAULT NULL COMMENT '申请说明事项',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `cancel_reason` tinytext DEFAULT NULL COMMENT '撤销理由',
  `bank_commit_datetime` datetime DEFAULT NULL COMMENT '银行提交时间',
  `bank_commit_note` tinytext DEFAULT NULL COMMENT '银行提交说明',
  `bank_fk_amount` bigint(20) DEFAULT NULL COMMENT '银行放款金额',
  `bank_fk_datetime` datetime DEFAULT NULL COMMENT '银行放款时间',
  `bank_receipt_code` bigint(20) DEFAULT NULL COMMENT '收款银行',
  `bank_receipt_number` varchar(32) DEFAULT NULL COMMENT '收款银行账号',
  `bank_receipt_pdf` tinytext DEFAULT NULL COMMENT '收款凭证',
  `bank_receipt_note` text COMMENT '收款说明',
  `pledge_commit_datetime` datetime DEFAULT NULL COMMENT '抵押提交时间',
  `pledge_commit_note` tinytext DEFAULT NULL COMMENT '抵押提交说明',
  `pledge_contract_code` varchar(32) DEFAULT NULL COMMENT '抵押合同编号',
  `insurance_company_code` tinytext DEFAULT NULL COMMENT '保险公司编号',
  `car_color` tinytext DEFAULT NULL COMMENT '车辆颜色',
  `car_brand` tinytext DEFAULT NULL COMMENT '车辆品牌',
  `frame_no` varchar(32) DEFAULT NULL COMMENT '车架号码',
  `engine_no` varchar(32) DEFAULT NULL COMMENT '发动机号码',
  `commerce_insurance` bigint(20) DEFAULT NULL COMMENT '商业险合计',
  `insurance_effect_datetime` datetime DEFAULT NULL COMMENT '保险生效日期',
  `insurance_bank` tinytext DEFAULT NULL COMMENT '保险经办银行',
  `guarantee_contract_code` varchar(32) DEFAULT NULL COMMENT '担保合同编号',
  `reg_certificate_code` varchar(32) DEFAULT NULL COMMENT '登记证书号',
  `other_contact` tinytext DEFAULT NULL COMMENT '其他联系人',
  `contact_mobile` varchar(16) DEFAULT NULL COMMENT '联系人手机',
  `guarantor_name` varchar(32) DEFAULT NULL COMMENT '担保人姓名',
  `guarantor_mobile` varchar(16) DEFAULT NULL COMMENT '担保人手机',
  `bank_card_number` varchar(32) DEFAULT NULL COMMENT '银行卡号',
  `bill_datetime` datetime DEFAULT NULL COMMENT '对账单日',
  `month_amount` bigint(20) DEFAULT NULL COMMENT '月还款额',
  `id_no_pic` varchar(32) DEFAULT NULL COMMENT '身份证照片',
  `file_list` tinytext DEFAULT NULL COMMENT '已入档清单',
  `is_complete` varchar(4) DEFAULT NULL COMMENT '资料是否完善',
  `store_place` tinytext DEFAULT NULL COMMENT '存放位置',
  `file_remark` tinytext DEFAULT NULL COMMENT '入档备注',
  `operator` tinytext DEFAULT NULL COMMENT '经办人',
  `operate_datetime` datetime DEFAULT NULL COMMENT '经办日期',
  `operate_department` tinytext DEFAULT NULL COMMENT '经办部门',
  `makecard_remark` tinytext DEFAULT NULL COMMENT '制卡备注',
  `delivery_datetime` datetime DEFAULT NULL COMMENT '提车日期',
  `is_right_invoice` varchar(4) DEFAULT NULL COMMENT '发票是否正确',
  `current_invoice_price` bigint(20) DEFAULT NULL COMMENT '现发票价',
  `invoice` tinytext DEFAULT NULL COMMENT '发票',
  `certification` tinytext DEFAULT NULL COMMENT '合格证',
  `force_insurance` bigint(20) DEFAULT NULL COMMENT '交强险',
  `business_insurance` bigint(20) DEFAULT NULL COMMENT '商业险',
  `motor_reg_certification` tinytext DEFAULT NULL COMMENT '机动车登记证书',
  `pd_pdf` tinytext DEFAULT NULL COMMENT '批单',
  `fbh_remark` tinytext DEFAULT NULL COMMENT '发保合备注',
  `fbh_warn_day` int(11) DEFAULT NULL COMMENT '发保合预警天数',
  `status` tinytext DEFAULT NULL COMMENT '状态',
  `should_back_amount` bigint(20) DEFAULT NULL COMMENT '应退按揭款',
  `pay_datetime` datetime DEFAULT NULL COMMENT '付款时间',
  `pay_bank` tinytext DEFAULT NULL COMMENT '付款银行',
  `pay_account` tinytext DEFAULT NULL COMMENT '付款账号',
  `pay_pdf` tinytext DEFAULT NULL COMMENT '付款凭证',
  `zf_apply_datetime` datetime DEFAULT NULL COMMENT '申请日期',
  `zf_reason` tinytext DEFAULT NULL COMMENT '作废原因',
  `zf_sk_amount` bigint(20) DEFAULT NULL COMMENT '收款金额',
  `zf_sk_bank` tinytext DEFAULT NULL COMMENT '收款银行',
  `zf_sk_bankcard_number` tinytext DEFAULT NULL COMMENT '收款账号',
  `zf_sk_receipt_datetime` datetime DEFAULT NULL COMMENT '收款时间',
  `zf_finance_remark` tinytext DEFAULT NULL COMMENT '财务备注',
  
  `make_card_status` varchar(4) DEFAULT NULL COMMENT '制卡状态',
  `make_card_operator` tinytext DEFAULT NULL COMMENT '制卡更新人',
  `make_card_remark` tinytext DEFAULT NULL COMMENT '制卡备注',
  `frozen_status` varchar(4) DEFAULT NULL COMMENT '冻结状态(0冻结 1正常)',
  `cancel_node_code` varchar(32) DEFAULT NULL COMMENT '客户申请作废时的节点编号',
  
  `is_submit_cancel` varchar(32) DEFAULT NULL COMMENT '是否提交作废申请',
  `back_advance_status` varchar(4) DEFAULT NULL COMMENT '退客户垫资款状态(0无需退款1银行已放款待财务退款2财务已退垫资款)',
  `back_advance_amount` bigint(20) DEFAULT NULL COMMENT '退款金额',
  `back_advance_account` varchar(32) DEFAULT NULL COMMENT '收款账号',
  `back_advance_open_bank` tinytext DEFAULT NULL COMMENT '开户行',
  
  `back_advance_subbranch` tinytext DEFAULT NULL COMMENT '开户支行',
  `back_advance_invoice` tinytext DEFAULT NULL COMMENT '凭证',
  `cur_node_code` varchar(32) DEFAULT NULL COMMENT '节点编号',
  `repay_biz_code` varchar(32) DEFAULT NULL COMMENT '业务编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tdq_credit`;
CREATE TABLE `tdq_credit` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `budget_code` varchar(32) DEFAULT NULL COMMENT '预算单编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '业务公司编号',
  `sale_user_id` varchar(32) DEFAULT NULL COMMENT '业务员编号',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `loan_bank_code` varchar(32) DEFAULT NULL COMMENT '贷款银行编号',
  `shop_way` varchar(32) DEFAULT NULL COMMENT '购车途径',
  `loan_amount` bigint(20) DEFAULT NULL COMMENT '贷款金额',
  `xsz_front` varchar(255) DEFAULT NULL COMMENT '行驶证正面',
  `xsz_reverse` varchar(255) DEFAULT NULL COMMENT '行驶证反面',
  `cur_node_code` varchar(32) DEFAULT NULL COMMENT '节点编号',
  `accessory` varchar(255) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='征信表';

DROP TABLE IF EXISTS `tdq_credit_user`;
CREATE TABLE `tdq_credit_user` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `credit_code` varchar(32) DEFAULT NULL COMMENT '征信单编号',
  `loan_role` varchar(255) DEFAULT NULL COMMENT '贷款角色',
  `user_name` varchar(255) DEFAULT NULL COMMENT '贷款人姓名',
  `relation` varchar(255) DEFAULT NULL COMMENT '与借款人关系',
  
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `id_no` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `dkdy_count` int(11) DEFAULT NULL COMMENT '贷款抵押笔数',
  `dkdy_amount` bigint(20) DEFAULT NULL COMMENT '贷款抵押贷款余额',
  `dkdy_2year_over_times` int(11) DEFAULT NULL COMMENT '贷款抵押近两年逾期次数',
  
  `dkdy_max_over_amount` bigint(20) DEFAULT NULL COMMENT '贷款抵押最高逾期金额',
  `dkdy_current_over_amount` bigint(20) DEFAULT NULL COMMENT '贷款抵押当前逾期金额',
  `dkdy_6month_avg_amount` bigint(20) DEFAULT NULL COMMENT '贷款抵押近6个月平均月还',
  `hkxy_unsettle_count` int(11) DEFAULT NULL COMMENT '贷款信用未结清贷款笔数',
  `hkxy_unsettle_amount` bigint(20) DEFAULT NULL COMMENT '贷款信用未结清贷款余额',
  
  `hkxy_2year_over_times` int(11) DEFAULT NULL COMMENT '贷款信用近两年逾期次数',
  `hkxy_month_max_over_amount` bigint(20) DEFAULT NULL COMMENT '贷款信用单月最高逾期金额',
  `hkxy_current_over_amount` bigint(20) DEFAULT NULL COMMENT '贷款信用当前逾期金额',
  `hkxy_6month_avg_amount` bigint(20) DEFAULT NULL COMMENT '贷款信用近6个月平均月还',
  `xyk_count` int(11) DEFAULT NULL COMMENT '信用卡张数',
  
  `xyk_credit_amount` bigint(20) DEFAULT NULL COMMENT '信用卡授信总额',
  `xyk_6month_use_amount` bigint(20) DEFAULT NULL COMMENT '信用卡近6个月使用额',
  `xyk_2year_over_times` int(11) DEFAULT NULL COMMENT '信用卡近两年逾期次数',
  `xyk_month_max_over_amount` bigint(20) DEFAULT NULL COMMENT '信用卡单月最高逾期金额',
  `xyk_current_over_amount` bigint(20) DEFAULT NULL COMMENT '信用卡当前逾期金额',
  
  `out_guarantees_count` int(11) DEFAULT NULL COMMENT '对外担保笔数',
  `out_guarantees_amount` bigint(20) DEFAULT NULL COMMENT '对外担保余额',
  `out_guarantees_remark` varchar(255) DEFAULT NULL COMMENT '对外担保备注',
  `auth_pdf` varchar(255) DEFAULT NULL COMMENT '征信查询授权书',
  `interview_pic` varchar(255) DEFAULT NULL COMMENT '面签照片',
  
  `month_income` bigint(20) DEFAULT NULL COMMENT '月收入',
  `settle_interest` decimal(18,8) DEFAULT NULL COMMENT '结息',
  `balance` bigint(20) DEFAULT NULL COMMENT '余额',
  `jour_show_income` char(1) DEFAULT NULL COMMENT '流水是否体现月收入',
  `is_print` char(1) DEFAULT NULL COMMENT '是否打件',
  
  `id_no_front` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `id_no_reverse` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='征信人员表';


/*请款预算单*/
DROP TABLE IF EXISTS `tdq_req_budget`;
CREATE TABLE `tdq_req_budget` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '业务公司编号',
  `receipt_bank` varchar(255) DEFAULT NULL COMMENT '收款银行',
  `receipt_account` varchar(255) DEFAULT NULL COMMENT '收款账号',
  `budget_amount` bigint(20) DEFAULT NULL COMMENT '预算金额',
  
  `use_datetime` datetime DEFAULT NULL COMMENT '用款日期',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `finance_check_note` text COMMENT '财务审核说明',
  `pay_amount` bigint(255) DEFAULT NULL COMMENT '打款金额',
  
  `pay_bank` varchar(255) DEFAULT NULL COMMENT '打款银行',
  `pay_account` varchar(255) DEFAULT NULL COMMENT '打款账号',
  `water_bill` varchar(255) DEFAULT NULL COMMENT '水单',
  `pay_datetime` datetime DEFAULT NULL COMMENT '打款时间',
  `pay_remark` text COMMENT '打款备注',
  
  `dz_amount` varchar(255) DEFAULT NULL COMMENT '垫资总额',
  `dz_datetime` datetime DEFAULT NULL COMMENT '垫资日期',
  `collection_bank` varchar(255) DEFAULT NULL COMMENT '收回款银行',
  `collection_amount` bigint(20) DEFAULT NULL COMMENT '收回款金额',
  `collection_account` varchar(255) DEFAULT NULL COMMENT '收回款账号',
  
  `collection_datetime` datetime DEFAULT NULL COMMENT '收回款日期',
  `collection_remark` text COMMENT '收回款备注',
  `cur_node_code` varchar(32) DEFAULT NULL COMMENT '节点编号',
  PRIMARY KEY (`code`) COMMENT '请款预算单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*垫资表*/
DROP TABLE IF EXISTS `tdq_advance_fund`;
CREATE TABLE `tdq_advance_fund` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `budget_code` varchar(32) NOT NULL COMMENT '预算单编号',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户姓名',
  `company_code` varchar(32) DEFAULT NULL COMMENT '业务公司编号',
  `car_dealer_code` varchar(32) DEFAULT NULL COMMENT '汽车经销商编号',
  `use_amount` bigint(20) DEFAULT NULL COMMENT '用款金额(应退按揭款)',
  `loan_bank_code` varchar(32) DEFAULT NULL COMMENT '贷款银行编号',
  `is_advance_fund` varchar(32) DEFAULT NULL COMMENT '是否垫资',
  `collect_bankcard_code` varchar(32) DEFAULT NULL COMMENT '收款账号编号',
  `cur_node_code` varchar(32) DEFAULT NULL COMMENT '节点编号',
  `make_bill_note` varchar(255) DEFAULT NULL COMMENT '制单意见说明',
  `advance_fund_amount` bigint(20) DEFAULT NULL COMMENT '垫资金额',
  `advance_fund_datetime` datetime DEFAULT NULL COMMENT '垫资日期',
  `pay_bankcard_code` varchar(32) DEFAULT NULL COMMENT '付款银行编号',
  `bill_pdf` varchar(255) DEFAULT NULL COMMENT '付款凭证',
  `note` varchar(255) DEFAULT NULL COMMENT '意见说明',
  `cancel_reason` varchar(255) DEFAULT NULL COMMENT '撤销理由',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`code`) COMMENT '垫资表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*垫资汇总表*/
DROP TABLE IF EXISTS `tdq_total_advance_fund`;
CREATE TABLE `tdq_total_advance_fund` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) NOT NULL COMMENT '类型',
  `company_code` varchar(32) DEFAULT NULL COMMENT '业务公司编号',
  `total_advance_fund` bigint(20) DEFAULT NULL COMMENT '垫资总金额',
  `pay_amount` bigint(20) DEFAULT NULL COMMENT '付款金额',
  `make_bill_note` VARCHAR(255) NULL COMMENT '制单意见说明' 
  `pay_datetime` datetime DEFAULT NULL COMMENT '付款时间',
  `pay_bankcard_code` varchar(32) DEFAULT NULL COMMENT '付款账号编号',
  `bill_pdf` varchar(255) DEFAULT NULL COMMENT '付款凭证（水单）',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '付款意见说明',
  `collection_amount` bigint(20) DEFAULT NULL COMMENT '收款金额',
  `collection_datetime` datetime DEFAULT NULL COMMENT '收款时间',
  `collection_bankcard_code` varchar(32) DEFAULT NULL COMMENT '收款银行（收款账号编号）',
  `collection_bill_pdf` varchar(255) DEFAULT NULL COMMENT '收款凭证（水单）',
  `collection_note` varchar(255) DEFAULT NULL COMMENT '收款意见说明',
  `updater` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  
  PRIMARY KEY (`code`) COMMENT '垫资汇总表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*返点表*/
DROP TABLE IF EXISTS `tdqt_repoint`;
CREATE TABLE `tdqt_repoint` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `car_dealer_code` varchar(4) NOT NULL COMMENT '汽车经销商编号',
  `account_name` varchar(32) DEFAULT NULL COMMENT '户名',
  `total_amount` bigint(20) DEFAULT NULL COMMENT '总金额',
  `reason` bigint(20) DEFAULT NULL COMMENT '缘由',
  `company_code` datetime DEFAULT NULL COMMENT '申请公司编号',
  `apply_user_id` varchar(32) DEFAULT NULL COMMENT '申请人编号',
  `apply_datetime` varchar(255) DEFAULT NULL COMMENT '申请时间',
  `pay_datetime` varchar(255) DEFAULT NULL COMMENT '付款时间',
  `pay_account_code` bigint(20) DEFAULT NULL COMMENT '付款账号编号',
  `bill_pdf` datetime DEFAULT NULL COMMENT '水单',
  `pay_remark` varchar(32) DEFAULT NULL COMMENT '付款备注',
  `settle_type` varchar(255) DEFAULT NULL COMMENT '结算方式',
  `cur_node_code` varchar(255) DEFAULT NULL COMMENT '节点',
  PRIMARY KEY (`code`) COMMENT '返点表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*返点明细表*/
DROP TABLE IF EXISTS `tdqt_repoint_detail`;
CREATE TABLE `tdqt_repoint_detail` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `biz_code` varchar(32) NOT NULL COMMENT '业务编号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '客户姓名',
  `id_no` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `car_dealer_code` varchar(255) DEFAULT NULL COMMENT '汽车经销商',
  `car_type` varchar(32) DEFAULT NULL COMMENT '车辆型号',
  `loan_amount` bigint(20) DEFAULT NULL COMMENT '贷款金额',
  `bank_rate` decimal(18,8) DEFAULT NULL COMMENT '银行实际利率',
  `benchmark_rate` decimal(18,8) DEFAULT NULL COMMENT '基准利率',
  `fee` bigint(20) DEFAULT NULL COMMENT '服务费',
  `use_money_purpose` varchar(255) DEFAULT NULL COMMENT '用款用途',
  `repoint_amount` bigint(20) DEFAULT NULL COMMENT '返点金额',
  `account_code` varchar(32) DEFAULT NULL COMMENT '账号编号',
  PRIMARY KEY (`code`) COMMENT '返点明细表'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


