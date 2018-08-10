package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630414Req {

    @NotBlank(message = "编号不能为空")
    private String code; // 编号（必填）

    @NotBlank(message = "最新修改人不能为空")
    private String updater; // 最新修改人（必填）

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
