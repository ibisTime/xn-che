package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.Car;

public interface ICarBO extends IPaginableBO<Car> {

    public String saveCar(Car data);

    public Car getCar(String code);

    public int editCar(Car data);

    public List<Car> queryCar(Car condition);

    public void removeCar(Car car);

    public Car getCarByModelId(String modelId);
}
