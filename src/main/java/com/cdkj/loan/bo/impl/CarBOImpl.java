package com.cdkj.loan.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.ICarBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.ICarDAO;
import com.cdkj.loan.domain.Car;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.exception.BizException;

@Component
public class CarBOImpl extends PaginableBOImpl<Car> implements ICarBO {

    @Autowired
    private ICarDAO carDAO;

    @Override
    public long getTotalCount(Car condition) {
        return carDAO.selectTotalCount(condition);
    }

    @Override
    public String saveCar(Car data) {
        String code = null;
        if (data != null) {
            if (data.getCode() == null) {
                code = OrderNoGenerater.generate(EGeneratePrefix.Car.getCode());
                data.setCode(code);
            }
            carDAO.insert(data);
        }
        return code;
    }

    @Override
    public int editCar(Car data) {

        return carDAO.update(data);
    }

    @Override
    public Car getCar(String code) {
        Car data = null;
        if (StringUtils.isNotBlank(code)) {
            Car condition = new Car();
            condition.setCode(code);
            data = carDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "车型不存在");
            }
        }
        return data;
    }

    @Override
    public List<Car> queryCar(Car condition) {

        return carDAO.selectList(condition);
    }

    @Override
    public void removeCar(Car car) {
        carDAO.delete(car);
    }

    @Override
    public Car getCarByModelId(String modelId) {
        Car car = new Car();
        car.setModelId(modelId);
        return carDAO.select(car);
    }

}
