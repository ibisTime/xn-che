package com.cdkj.loan.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630450Req {

    private String cdkjToken;

    @NotBlank
    private String modelId;// 车型标识

    @NotBlank
    private String regDate;// 待估车辆的上牌时间

    @NotBlank
    private String mile;// 待估车辆的公里数(单位万公里)

    @NotBlank
    private String zone;// 城市标识

    public String getCdkjToken() {
        return cdkjToken;
    }

    public void setCdkjToken(String cdkjToken) {
        this.cdkjToken = cdkjToken;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getMile() {
        return mile;
    }

    public void setMile(String mile) {
        this.mile = mile;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

}
