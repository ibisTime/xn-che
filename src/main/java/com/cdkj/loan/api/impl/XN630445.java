package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.ICityListAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.CityList;
import com.cdkj.loan.dto.req.XN630445Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 城市列表分页查
 * @author: CYL 
 * @since: 2018年11月15日 下午6:56:58 
 * @history:
 */
public class XN630445 extends AProcessor {

    private ICityListAO cityListAO = SpringContextHolder
        .getBean(ICityListAO.class);

    private XN630445Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CityList condition = new CityList();
        condition.setProvId(req.getProvId());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICityListAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cityListAO.queryCityListPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630445Req.class);
        ObjValidater.validateReq(req);
    }

}
