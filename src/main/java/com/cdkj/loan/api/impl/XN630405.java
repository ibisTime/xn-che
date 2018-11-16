package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.IBrandAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Brand;
import com.cdkj.loan.dto.req.XN630405Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 分页查询
 * @author: CYL 
 * @since: 2018年4月24日 下午5:35:06 
 * @history:
 */

public class XN630405 extends AProcessor {

    private IBrandAO brandAO = SpringContextHolder.getBean(IBrandAO.class);

    private XN630405Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Brand condition = new Brand();
        condition.setName(req.getName());
        condition.setLetter(req.getLetter());
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IBrandAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return brandAO.queryBrandPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630405Req.class);
        ObjValidater.validateReq(req);
    }

}
