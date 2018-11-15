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
import com.cdkj.loan.ao.ISeriesAO;
import com.cdkj.loan.bo.IBrandBO;
import com.cdkj.loan.bo.ISYSConfigBO;
import com.cdkj.loan.bo.ISeriesBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.core.OkHttpUtils;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Brand;
import com.cdkj.loan.domain.SYSConfig;
import com.cdkj.loan.domain.Series;
import com.cdkj.loan.dto.req.XN630410Req;
import com.cdkj.loan.dto.req.XN630412Req;
import com.cdkj.loan.dto.req.XN630418Req;
import com.cdkj.loan.enums.EBizErrorCode;
import com.cdkj.loan.enums.EBrandStatus;
import com.cdkj.loan.enums.ECarProduceType;
import com.cdkj.loan.exception.BizException;

@Service
public class SeriesAOImpl implements ISeriesAO {

    @Autowired
    private ISeriesBO seriesBO;

    @Autowired
    private IBrandBO brandBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public void addSeries(XN630410Req req) {

        Series series = new Series();
        series.setBrandCode(req.getBrandCode());
        series.setName(req.getName());
        series.setSlogan(req.getSlogan());
        series.setAdvPic(req.getAdvPic());
        series.setPrice(StringValidater.toLong(req.getPrice()));
        series.setStatus(EBrandStatus.TO_UP.getCode());
        series.setUpdater(req.getUpdater());
        series.setUpdateDatetime(new Date());
        series.setRemark(req.getRemark());
        seriesBO.saveSeries(series);

    }

    @Override
    public void editSeries(XN630412Req req) {
        Series series = seriesBO.getSeries(req.getCode());
        if (EBrandStatus.UP.getCode().equals(series.getStatus())) {
            throw new BizException("xn0000", "品牌已上架，请在下架后修改");
        }
        series.setBrandCode(req.getBrandCode());
        series.setName(req.getName());
        series.setSlogan(req.getSlogan());
        series.setAdvPic(req.getAdvPic());
        series.setPrice(StringValidater.toLong(req.getPrice()));
        series.setUpdater(req.getUpdater());
        series.setUpdateDatetime(new Date());
        series.setRemark(req.getRemark());
        seriesBO.editSeries(series);
    }

    @Override
    public void upSeries(String code, String location, String orderNo,
            String updater) {
        Series series = seriesBO.getSeries(code);
        series.setLocation(StringValidater.toInteger(location));
        series.setOrderNo(StringValidater.toInteger(orderNo));
        series.setStatus(EBrandStatus.UP.getCode());
        series.setUpdater(updater);
        series.setUpdateDatetime(new Date());
        seriesBO.upSeries(series);
    }

    @Override
    public void downSeries(String code, String updater) {
        Series series = seriesBO.getSeries(code);
        series.setStatus(EBrandStatus.DOWN.getCode());
        series.setUpdater(updater);
        series.setUpdateDatetime(new Date());
        seriesBO.downSeries(series);
    }

    @Override
    public Paginable<Series> querySeriesPage(int start, int limit,
            Series condition) {
        return seriesBO.getPaginable(start, limit, condition);
    }

    @Override
    public Series getSeries(String code) {
        return seriesBO.getSeries(code);
    }

    @Override
    public List<Series> querySeriesList(Series condition) {
        return seriesBO.querySeries(condition);
    }

    @Override
    @Transactional
    public void refreshSeries(XN630418Req req) {
        SYSConfig url = sysConfigBO.getSYSConfig("car_refresh", "url");
        if (StringUtils.isBlank(req.getBrandId())) {
            Brand brand = new Brand();
            brand.setType(ECarProduceType.IMPORT.getCode());
            List<Brand> queryBrand = brandBO.queryBrand(brand);
            for (Brand domain : queryBrand) {
                refresh(url, domain.getBrandId());
            }
        } else {
            Brand brand = brandBO.getBrandByBrandId(req.getBrandId());
            if (brand == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "品牌标识不存在！");
            }
            refresh(url, req.getBrandId());
        }
    }

    private void refresh(SYSConfig url, String brandId) {
        String json = OkHttpUtils.doAccessHTTPGetJson(url.getCvalue()
                + "/getCarSeriesList" + "?token="
                + "ed34a9f390e806112420863423cd8dbc" + "&brandId=" + brandId);
        JSONObject jsono = JSONObject.parseObject(json);
        String status = jsono.get("status").toString();
        if (status.equals("0")) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                jsono.get("error_msg").toString());
        }
        Series condition = new Series();
        condition.setBrandId(brandId);
        condition.setType(ECarProduceType.IMPORT.getCode());
        List<Series> querySeries = seriesBO.querySeries(condition);
        if (CollectionUtils.isNotEmpty(querySeries)) {
            for (Series series : querySeries) {
                seriesBO.removeSeries(series);
            }
        }

        String list = jsono.get("series_list").toString();
        JSONArray parseArray = JSONArray.parseArray(list);
        for (Object object : parseArray) {
            JSONObject jsonObject = (JSONObject) object;
            String seriesId = jsonObject.getString("series_id");
            String seriesName = jsonObject.getString("series_name");
            String makerType = jsonObject.getString("maker_type");
            String seriesGroupName = jsonObject.getString("series_group_name");
            Date updateTime = jsonObject.getDate("update_time");

            Series series = new Series();
            series.setBrandId(brandId);
            series.setSeriesId(seriesId);
            series.setType(ECarProduceType.IMPORT.getCode());
            series.setMakerType(makerType);
            series.setName(seriesName);
            series.setSeriesGroupName(seriesGroupName);
            series.setStatus(EBrandStatus.UP.getCode());
            series.setUpdateDatetime(updateTime);
            seriesBO.saveSeries(series);
        }
    }

}
