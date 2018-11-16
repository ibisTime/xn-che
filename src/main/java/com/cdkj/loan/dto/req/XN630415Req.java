package com.cdkj.loan.dto.req;

public class XN630415Req extends APageReq {

    private String name; // 名称（选填）

    private String brandCode; // 品牌编号（选填）

    private String status; // 状态（选填）
    // 类型（1接口导入2用户新增）

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
