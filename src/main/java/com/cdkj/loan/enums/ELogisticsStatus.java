package com.cdkj.loan.enums;

/**
 * 资料传递状态
 * @author: silver 
 * @since: 2018年5月29日 下午10:18:47 
 * @history:
 */
public enum ELogisticsStatus {
    TO_SEND("0", "待发件"), TO_RECEIVE("1", "已发件待收件"), RECEIVED("2", "已收件待审核"), AUDITE_PASS(
            "3", "审核通过"), TO_SEND_AGAIN("4", "审核不通过待补件"), BACK_PIECE("5", "退件");

    ELogisticsStatus(String code, String value) {
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
