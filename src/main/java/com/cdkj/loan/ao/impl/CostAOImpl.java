package com.cdkj.loan.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.ICostAO;
import com.cdkj.loan.bo.ICostBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Cost;
import com.cdkj.loan.dto.req.XN630535Req;
import com.cdkj.loan.enums.EBoolean;

@Service
public class CostAOImpl implements ICostAO {

    @Autowired
    private ICostBO costBO;

    @Override
    public void addCost(String repayPlanCode, String overdueTreatmentCode,
            List<XN630535Req> list) {
        for (XN630535Req Req : list) {
            Cost cost = new Cost();
            cost.setRepayPlanCode(repayPlanCode);
            cost.setOverdueTreatmentCode(overdueTreatmentCode);
            cost.setItem(Req.getItem());
            cost.setAmount(StringValidater.toLong(Req.getAmount()));
            cost.setPayDatetime(DateUtil.strToDate(Req.getPayDatetime(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
            cost.setPayWay(Req.getPayWay());
            cost.setStatus(EBoolean.NO.getCode());
            costBO.saveCost(cost);
        }
    }

    @Override
    public int dropCost(String code) {
        return costBO.removeCost(code);
    }

    @Override
    public Paginable<Cost> queryCostPage(int start, int limit, Cost condition) {
        return costBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Cost> queryCostList(Cost condition) {
        return costBO.queryCostList(condition);
    }

    @Override
    public Cost getCost(String code) {
        return costBO.getCost(code);
    }

}
