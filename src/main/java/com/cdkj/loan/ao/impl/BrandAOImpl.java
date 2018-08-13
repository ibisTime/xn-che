package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.cdkj.loan.aliyun.util.HttpUtils;
import com.cdkj.loan.ao.IBrandAO;
import com.cdkj.loan.bo.IBrandBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.Brand;
import com.cdkj.loan.dto.req.XN630400Req;
import com.cdkj.loan.dto.req.XN630402Req;
import com.cdkj.loan.enums.EBrandStatus;
import com.cdkj.loan.exception.BizException;

@Service
public class BrandAOImpl implements IBrandAO {

    @Autowired
    private IBrandBO brandBO;

    @Override
    public void addBrand(XN630400Req req) {
        Brand brand = new Brand();
        brand.setLetter(req.getLetter());
        brand.setLogo(req.getLogo());
        brand.setName(req.getName());
        brand.setDescription(req.getDescription());

        brand.setStatus(EBrandStatus.TO_UP.getCode());
        brand.setUpdater(req.getUpdater());
        brand.setUpdateDatetime(new Date());
        brand.setRemark(req.getRemark());
        brandBO.saveBrand(brand);
        // 导入品牌基础数据
        // JSONArray json = generateBrand();
        // for (Object obj : json) {
        // JSONObject jo = (JSONObject) obj;
        // String id = jo.getString("id");
        // String name = jo.getString("name");
        // String initial = jo.getString("initial");
        // String parentid = jo.getString("parentid");
        // String logo = jo.getString("logo");
        // String depth = jo.getString("depth");
        //
        // Brand brand = new Brand();
        // brand.setLetter(initial);
        // brand.setLogo(logo);
        // brand.setName(name);
        // brand.setDescription(id);
        //
        // brand.setStatus(EBrandStatus.TO_UP.getCode());
        // brand.setUpdater("USYS201800000000001");
        // brand.setUpdateDatetime(new Date());
        // brandBO.saveBrand(brand);
        // }
    }

    private JSONArray generateBrand() {
        JSONArray json = null;
        String host = "https://jisucxdq.market.alicloudapi.com";
        String path = "/car/brand";
        String method = "GET";
        String appcode = "1bd9832a74284772a7549ff0cc51043e";
        Map<String, String> headers = new HashMap<String, String>();
        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
        // 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        try {
            /**
            * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
            */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers,
                querys);
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity);// 获取response的body
            json = (JSONArray) JSONArray
                .parse(string.substring(34, string.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void editBrand(XN630402Req req) {
        Brand brand = brandBO.getBrand(req.getCode());
        if (EBrandStatus.UP.getCode().equals(brand.getStatus())) {
            throw new BizException("xn0000", "品牌已上架，请在下架后修改");
        }
        brand.setLogo(req.getLogo());
        brand.setName(req.getName());
        brand.setDescription(req.getDescription());
        brand.setUpdater(req.getUpdater());
        brand.setUpdateDatetime(new Date());
        brand.setRemark(req.getRemark());
        brandBO.editBrand(brand);
    }

    @Override
    public void upBrand(String code, String updater, String remark) {
        Brand brand = brandBO.getBrand(code);
        if (EBrandStatus.UP.getCode().equals(brand.getStatus())) {
            throw new BizException("xn0000", "品牌已上架,请勿重复上架");
        }
        brand.setStatus(EBrandStatus.UP.getCode());
        brand.setUpdater(updater);
        brand.setUpdateDatetime(new Date());
        brand.setRemark(remark);
        brandBO.editBrand(brand);
    }

    @Override
    public void downBrand(String code, String updater, String remark) {
        Brand brand = brandBO.getBrand(code);
        if (!EBrandStatus.UP.getCode().equals(brand.getStatus())) {
            throw new BizException("xn0000", "品牌未上架");
        }
        brand.setStatus(EBrandStatus.DOWN.getCode());
        brand.setUpdater(updater);
        brand.setUpdateDatetime(new Date());
        brand.setRemark(remark);
        brandBO.editBrand(brand);
    }

    @Override
    public Paginable<Brand> queryBrandPage(int start, int limit,
            Brand condition) {
        return brandBO.getPaginable(start, limit, condition);
    }

    @Override
    public Brand getBrand(String code) {
        return brandBO.getBrand(code);
    }

    @Override
    public List<Brand> queryBrandList(Brand condition) {
        return brandBO.queryBrand(condition);
    }

}
