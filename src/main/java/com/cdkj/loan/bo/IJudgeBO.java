package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.Judge;
import com.cdkj.loan.dto.req.XN630560Req;
import com.cdkj.loan.dto.req.XN630562Req;
import com.cdkj.loan.dto.req.XN630565Req;
import com.cdkj.loan.dto.req.XN630566Req;
import com.cdkj.loan.dto.req.XN630567Req;
import com.cdkj.loan.dto.req.XN630568Req;
import com.cdkj.loan.enums.EBoolean;

public interface IJudgeBO extends IPaginableBO<Judge> {

    public String saveJudge(XN630560Req req);

    // 执行结果录入
    public void refreshJudgeResultInput(XN630562Req req);

    public List<Judge> queryJudgeList(Judge condition);

    public Judge getJudge(String code);

    public Judge queryJudgeByRepayBizCode(String repayBizCode, EBoolean status);

    public Judge getJudgeByRepaybizCode(String code);

    // 出纳打款
    public void cashier(XN630565Req req);

    // 受理
    public void acceptance(XN630566Req req);

    // 开庭
    public void toHoldCourt(XN630567Req req);

    // 判决
    public void sentence(XN630568Req req);

}
