package com.cdkj.loan.domain;

import java.util.Date;

import com.cdkj.loan.dao.base.ABaseDO;

/**
* 城市列表
* @author: CYunlai 
* @since: 2018-11-15 18:48:34
* @history:
*/
public class CityList extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // id
    private int id;

    // 城市ID
    private String cityId;

    // 城市名称
    private String cityName;

    // 所属省份ID
    private String provId;

    // 所属省份名称
    private String provName;

    // 更新时间
    private Date createDatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getProvName() {
        return provName;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
