package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.loan.ao.ICityListAO;
import com.cdkj.loan.bo.ICityListBO;
import com.cdkj.loan.bo.ISYSConfigBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.core.OkHttpUtils;
import com.cdkj.loan.domain.CityList;
import com.cdkj.loan.domain.SYSConfig;
import com.cdkj.loan.dto.req.XN630440Req;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.exception.BizException;

@Service
public class CityListAOImpl implements ICityListAO {

    @Autowired
    private ICityListBO cityListBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public void addCityList(CityList data) {
        cityListBO.saveCityList(data);
    }

    @Override
    public Paginable<CityList> queryCityListPage(int start, int limit,
            CityList condition) {
        return cityListBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CityList> queryCityListList(CityList condition) {
        return cityListBO.queryCityListList(condition);
    }

    @Override
    public CityList getCityList(int id) {
        return cityListBO.getCityList(id);
    }

    @Override
    @Transactional
    public void refreshCityList(XN630440Req req) {
        SYSConfig url = sysConfigBO.getSYSConfig("car_refresh", "url");
        CityList condition = new CityList();
        List<CityList> cityListList = queryCityListList(condition);
        for (CityList cityList : cityListList) {
            cityListBO.removeCityList(cityList);
        }
        String json = OkHttpUtils
            .doAccessHTTPGetJson(url.getCvalue() + "/getAllCity" + "?token="
                    + "ed34a9f390e806112420863423cd8dbc");
        JSONObject jsono = JSONObject.parseObject(json);
        String status = jsono.get("status").toString();
        if (status.equals("0")) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                jsono.get("error_msg").toString());
        }

        String list = jsono.get("city_list").toString();
        JSONArray parseArray = JSONArray.parseArray(list);
        for (Object object : parseArray) {
            JSONObject jsonObject = (JSONObject) object;
            String cityId = jsonObject.getString("city_id");
            String cityName = jsonObject.getString("city_name");
            String provId = jsonObject.getString("prov_id");
            String provName = jsonObject.getString("prov_name");

            CityList cityList = new CityList();
            cityList.setCityId(cityId);
            cityList.setCityName(cityName);
            cityList.setProvId(provId);
            cityList.setProvName(provName);
            cityList.setCreateDatetime(new Date());
            cityListBO.saveCityList(cityList);
        }
    }
}
