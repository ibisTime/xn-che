package com.cdkj.loan.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.loan.ao.IReplaceRepayPlanAO;
import com.cdkj.loan.api.AProcessor;
import com.cdkj.loan.common.JsonUtil;
import com.cdkj.loan.core.ObjValidater;
import com.cdkj.loan.domain.ReplaceRepayPlan;
import com.cdkj.loan.dto.req.XN632338Req;
import com.cdkj.loan.exception.BizException;
import com.cdkj.loan.exception.ParaException;
import com.cdkj.loan.spring.SpringContextHolder;

/**
 * 代偿审核列表查
 * @author: silver 
 * @since: 2018年6月16日 上午10:22:24 
 * @history:
 */
public class XN632338 extends AProcessor {
    private IReplaceRepayPlanAO replaceRepayPlanAO = SpringContextHolder
        .getBean(IReplaceRepayPlanAO.class);

    private XN632338Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ReplaceRepayPlan condition = new ReplaceRepayPlan();
        condition.setBizCode(req.getBizCode());
        condition.setType(req.getType());
        condition.setCurNodeCode(req.getCurNodeCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IReplaceRepayPlanAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return replaceRepayPlanAO.queryReplaceRepayPlanList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN632338Req.class);
        ObjValidater.validateReq(req);
    }
}
