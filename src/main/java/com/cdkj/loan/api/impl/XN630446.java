package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ICityListAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.dto.req.XN630446Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 城市列表详情查
 * @author: CYL 
 * @since: 2018年11月15日 下午6:56:58 
 * @history:
 */
public class XN630446 extends AProcessor {

    private ICityListAO cityListAO = SpringContextHolder
        .getBean(ICityListAO.class);

    private XN630446Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return cityListAO.getCityList(StringValidater.toInteger(req.getId()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630446Req.class);
        ObjValidater.validateReq(req);
    }

}
