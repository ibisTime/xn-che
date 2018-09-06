package com.cdkj.loan.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.domain.ReqBudget;
import com.cdkj.loan.dto.req.XN632100Req;
import com.cdkj.loan.dto.req.XN632102Req;
import com.cdkj.loan.dto.req.XN632103Req;
import com.cdkj.loan.dto.req.XN632104Req;

@Component
public interface IReqBudgetAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addReqBudget(XN632100Req req);

    public void collectionReqBudget(XN632103Req req);

    public void loan(XN632102Req req);

    public Paginable<ReqBudget> queryReqBudgetPage(int start, int limit,
            ReqBudget condition);

    public Paginable<ReqBudget> queryReqBudgetPageByRoleCode(int start,
            int limit, ReqBudget condition);

    public List<ReqBudget> queryReqBudgetList(ReqBudget condition);

    public ReqBudget getReqBudget(String code);

    // 打款回录
    public void backRecord(XN632104Req req);
}
