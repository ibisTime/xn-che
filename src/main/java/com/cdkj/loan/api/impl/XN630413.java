package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ISeriesAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN630413Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 车系上架
 * @author: CYL 
 * @since: 2018年4月24日 下午5:36:39 
 * @history:
 */

public class XN630413 extends AProcessor {

    private ISeriesAO seriesAO = SpringContextHolder.getBean(ISeriesAO.class);

    private XN630413Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        seriesAO.upSeries(req.getCode(), req.getLocation(), req.getOrderNo(),
            req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630413Req.class);
        ObjValidater.validateReq(req);

    }

}
