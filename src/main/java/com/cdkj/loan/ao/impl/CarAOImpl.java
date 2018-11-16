package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.loan.ao.ICarAO;
import com.cdkj.loan.bo.ICarBO;
import com.cdkj.loan.bo.ISYSConfigBO;
import com.cdkj.loan.bo.ISeriesBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.core.OkHttpUtils;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Car;
import com.cdkj.loan.domain.SYSConfig;
import com.cdkj.loan.domain.Series;
import com.cdkj.loan.dto.req.XN630420Req;
import com.cdkj.loan.dto.req.XN630422Req;
import com.cdkj.loan.dto.req.XN630428Req;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.enums.EBrandStatus;
import com.cdkj.loan.enums.ECarProduceType;
import com.cdkj.loan.exception.BizException;

@Service
public class CarAOImpl implements ICarAO {

    @Autowired
    private ICarBO carBO;

    @Autowired
    private ISeriesBO seriesBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public void addCar(XN630420Req req) {
        Car car = new Car();

        car.setName(req.getName());
        car.setSeriesCode(req.getSeriesCode());
        car.setSeriesName(req.getSeriesName());
        car.setBrandCode(req.getBrandCode());
        car.setBrandName(req.getBrandName());

        car.setOriginalPrice(StringValidater.toLong(req.getOriginalPrice()));
        car.setSalePrice(req.getSalePrice());
        car.setSfAmount(StringValidater.toLong(req.getSfAmount()));
        car.setSlogan(req.getSlogan());
        car.setAdvPic(req.getAdvPic());

        car.setPic(req.getPic());
        car.setDescription(req.getDescription());
        car.setStatus(EBrandStatus.TO_UP.getCode());
        car.setUpdater(req.getUpdater());
        car.setUpdateDatetime(new Date());

        car.setRemark(req.getRemark());
        carBO.saveCar(car);

    }

    @Override
    public void editCar(XN630422Req req) {
        Car car = carBO.getCar(req.getCode());
        if (EBrandStatus.UP.getCode().equals(car.getStatus())) {
            throw new BizException("xn0000", "品牌已上架，请在下架后修改");
        }
        car.setName(req.getName());
        car.setSeriesCode(req.getSeriesCode());
        car.setSeriesName(req.getSeriesName());
        car.setBrandCode(req.getBrandCode());
        car.setBrandName(req.getBrandName());
        car.setOriginalPrice(StringValidater.toLong(req.getOriginalPrice()));
        car.setSalePrice(req.getSalePrice());
        car.setSfAmount(StringValidater.toLong(req.getSfAmount()));
        car.setSlogan(req.getSlogan());
        car.setAdvPic(req.getAdvPic());
        car.setPic(req.getPic());
        car.setDescription(req.getDescription());
        car.setUpdater(req.getUpdater());
        car.setUpdateDatetime(new Date());
        car.setRemark(req.getRemark());
        carBO.editCar(car);
    }

    @Override
    public void upCar(String code, String location, String orderNo,
            String updater, String remark) {
        Car car = carBO.getCar(code);
        car.setStatus(EBrandStatus.UP.getCode());
        car.setLocation(StringValidater.toInteger(location));
        car.setOrderNo(StringValidater.toInteger(orderNo));
        car.setUpdater(updater);
        car.setUpdateDatetime(new Date());
        car.setRemark(remark);
        carBO.editCar(car);
    }

    @Override
    public void downCar(String code, String updater, String remark) {
        Car car = carBO.getCar(code);
        car.setStatus(EBrandStatus.DOWN.getCode());
        car.setUpdater(updater);
        car.setUpdateDatetime(new Date());
        car.setRemark(remark);
        carBO.editCar(car);
    }

    @Override
    public Paginable<Car> queryCarPage(int start, int limit, Car condition) {
        Paginable<Car> paginable = carBO.getPaginable(start, limit, condition);
        return paginable;
    }

    @Override
    public Car getCar(String code) {
        Car car = carBO.getCar(code);
        return car;
    }

    @Override
    public List<Car> queryCarList(Car condition) {
        return carBO.queryCar(condition);
    }

    @Override
    @Transactional
    public void refreshCar(XN630428Req req) {
        SYSConfig url = sysConfigBO.getSYSConfig("car_refresh", "url");
        if (StringUtils.isBlank(req.getSeriesId())) {
            Series series = new Series();
            series.setType(ECarProduceType.IMPORT.getCode());
            List<Series> querySeries = seriesBO.querySeries(series);
            for (Series domain : querySeries) {
                refresh(url, domain.getSeriesId(), domain.getCode(),
                    domain.getName(), domain.getBrandCode(),
                    domain.getBrandName());
            }
        } else {
            Series series = seriesBO.getSeriesBySeriesId(req.getSeriesId());
            if (series == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "车系标识不存在！");
            }
            refresh(url, req.getSeriesId(), series.getCode(), series.getName(),
                series.getBrandCode(), series.getBrandName());
        }
    }

    private void refresh(SYSConfig url, String seriesId, String seriesCode,
            String seriesName, String brandCode, String brandName) {
        String json = OkHttpUtils.doAccessHTTPGetJson(url.getCvalue()
                + "/getCarModelList" + "?token="
                + "ed34a9f390e806112420863423cd8dbc" + "&seriesId=" + seriesId);
        JSONObject jsono = JSONObject.parseObject(json);
        String status = jsono.get("status").toString();
        if (status.equals("0")) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                jsono.get("error_msg").toString());
        }

        Car condition = new Car();
        condition.setSeriesId(seriesId);
        condition.setType(ECarProduceType.IMPORT.getCode());
        List<Car> queryCar = carBO.queryCar(condition);
        if (CollectionUtils.isNotEmpty(queryCar)) {
            for (Car car : queryCar) {
                carBO.removeCar(car);
            }
        }
        String list = jsono.get("model_list").toString();
        JSONArray parseArray = JSONArray.parseArray(list);
        for (Object object : parseArray) {
            JSONObject jsonObject = (JSONObject) object;
            String modelId = jsonObject.getString("model_id");
            String modelName = jsonObject.getString("model_name");
            String modelPrice = jsonObject.getString("model_price");
            String modelYear = jsonObject.getString("model_year");
            String minRegYear = jsonObject.getString("min_reg_year");
            String maxRegYear = jsonObject.getString("max_reg_year");
            String liter = jsonObject.getString("liter");
            String gearType = jsonObject.getString("gear_type");
            String dischargeStandard = jsonObject
                .getString("discharge_standard");
            String seatNumber = jsonObject.getString("seat_number");
            Date updateTime = jsonObject.getDate("update_time");

            Car car = new Car();
            car.setSeriesId(seriesId);
            car.setModelId(modelId);
            car.setType(ECarProduceType.IMPORT.getCode());
            car.setName(modelName);
            car.setSeriesCode(seriesCode);
            car.setSeriesName(seriesName);
            car.setBrandCode(brandCode);
            car.setBrandName(brandName);
            car.setSalePrice(modelPrice + "万");
            car.setModelYear(modelYear);
            car.setMinRegYear(minRegYear);
            car.setMaxRegYear(maxRegYear);
            car.setLiter(liter);
            car.setGearType(gearType);
            car.setDischargeStandard(dischargeStandard);
            car.setSeatNumber(seatNumber);
            car.setStatus(EBrandStatus.UP.getCode());
            car.setUpdateDatetime(updateTime);
            carBO.saveCar(car);
        }
    }

}
