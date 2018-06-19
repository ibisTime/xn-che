package com.cdkj.loan.enums;

/**
 * 处理结果
 * @author: xieyj 
 * @since: 2018年6月17日 下午5:52:42 
 * @history:
 */
public enum EDealResult {
    REDEEM("1", "已赎回"), SELLED("2", "出售"), GREEN("3", "偿还逾期金额并提供保证金"), JUDGE(
            "4", "司法诉讼");

    EDealResult(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}