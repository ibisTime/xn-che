package com.cdkj.loan.domain;

import java.util.Date;

import com.cdkj.loan.dao.base.ABaseDO;

/**
* 基础估值
* @author: CYunlai 
* @since: 2018-11-15 20:01:35
* @history:
*/
public class BasicValuation extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // id
    private int id;

    // 车型标识
    private String modelId;

    // 待估车辆的上牌时间
    private String regDate;

    // 待估车辆的公里数(单位万公里)
    private String mile;

    // 城市标识
    private String zone;

    // 评估价格
    private String evalPrice;

    // 最低价
    private String lowPrice;

    // 最优价
    private String goodPrice;

    // 最高价
    private String highPrice;

    // 车商收购价
    private String dealerBuyPrice;

    // 个人交易价
    private String individualPrice;

    // 车商零售价
    private String dealerPrice;

    // 地址
    private String url;

    // 新车售价
    private String price;

    // 排放标准
    private String dischargeStandard;

    // 标题
    private String title;

    // 汽车标志网址
    private String carLogoUrl;

    // 更新人
    private String updater;

    // 更新时间
    private Date createDatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEvalPrice(String evalPrice) {
        this.evalPrice = evalPrice;
    }

    public String getEvalPrice() {
        return evalPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setDealerBuyPrice(String dealerBuyPrice) {
        this.dealerBuyPrice = dealerBuyPrice;
    }

    public String getDealerBuyPrice() {
        return dealerBuyPrice;
    }

    public void setIndividualPrice(String individualPrice) {
        this.individualPrice = individualPrice;
    }

    public String getIndividualPrice() {
        return individualPrice;
    }

    public void setDealerPrice(String dealerPrice) {
        this.dealerPrice = dealerPrice;
    }

    public String getDealerPrice() {
        return dealerPrice;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setDischargeStandard(String dischargeStandard) {
        this.dischargeStandard = dischargeStandard;
    }

    public String getDischargeStandard() {
        return dischargeStandard;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setCarLogoUrl(String carLogoUrl) {
        this.carLogoUrl = carLogoUrl;
    }

    public String getCarLogoUrl() {
        return carLogoUrl;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
