package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.ICarAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Car;
import com.cdkj.loan.dto.req.XN630425Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 分页查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:39:28 
 * @history:
 */

public class XN630425 extends AProcessor {

    private ICarAO carAO = SpringContextHolder.getBean(ICarAO.class);

    private XN630425Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Car condition = new Car();
        condition.setName(req.getName());
        condition.setBrandCode(req.getBrandCode());
        condition.setBrandName(req.getBrandName());
        condition.setSeriesCode(req.getSeriesCode());
        condition.setSeriesName(req.getSeriesName());
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICarAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return carAO.queryCarPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630425Req.class);
        ObjValidater.validateReq(req);
    }

}
