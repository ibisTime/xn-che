package com.cdkj.loan.api.impl;

import com.cdkj.loan.ao.ICityListAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.CityList;
import com.cdkj.loan.dto.req.XN630447Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 城市列表列表查
 * @author: CYL 
 * @since: 2018年11月15日 下午6:56:58 
 * @history:
 */
public class XN630447 extends AProcessor {

    private ICityListAO cityListAO = SpringContextHolder
        .getBean(ICityListAO.class);

    private XN630447Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CityList condition = new CityList();
        condition.setProvId(req.getProvId());
        return cityListAO.queryCityListList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630447Req.class);
        ObjValidater.validateReq(req);
    }

}
