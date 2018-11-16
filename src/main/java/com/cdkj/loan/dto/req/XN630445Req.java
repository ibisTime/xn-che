package com.cdkj.loan.dto.req;

public class XN630445Req extends APageReq {

    private static final long serialVersionUID = -8374696319412469839L;

    private String cdkjToken;

    // 所属省份ID
    private String provId;

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getCdkjToken() {
        return cdkjToken;
    }

    public void setCdkjToken(String cdkjToken) {
        this.cdkjToken = cdkjToken;
    }

}
