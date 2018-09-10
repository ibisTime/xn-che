package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ICarAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.dto.req.XN630420Req;
import com.cdkj.loan.dto.res.BooleanRes;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 新增车型
 * @author: CYL 
 * @since: 2018年4月23日 下午4:26:43 
 * @history:
 */

public class XN630420 extends AProcessor {

    private ICarAO carAO = SpringContextHolder.getBean(ICarAO.class);

    private XN630420Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        carAO.addCar(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630420Req.class);
        ObjValidater.validateReq(req);
    }

}
