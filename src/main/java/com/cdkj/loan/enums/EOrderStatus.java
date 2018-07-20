package com.cdkj.loan.enums;

/**
 * @author: xieyj 
 * @since: 2016年5月25日 上午9:28:36 
 * @history:
 */
public enum EOrderStatus {
    TO_PAY("1", "待支付"), PAY_YES("2", "已支付"), SEND("4", "已发货"), RECEIVE("5",
            "已收货"), COMMENT("6", "已评论"), YHYC("91", "用户取消订单"), SHYC("92",
                    "商户取消订单"), KDYC("93", "快递异常"), RECYCLE("94", "用户删除");

    EOrderStatus(String code, String value) {
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
