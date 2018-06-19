package com.cdkj.loan.bo;

import java.util.List;

import com.cdkj.loan.bo.base.IPaginableBO;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.BudgetOrderFee;

/**
 * 手续费
 * @author: jiafr 
 * @since: 2018年5月30日 下午8:28:04 
 * @history:
 */
public interface IBudgetOrderFeeBO extends IPaginableBO<BudgetOrderFee> {

    // 手续费新增
    public String saveBudgetOrderFee(BudgetOrder budgetOrder, String operator);

    // 设置手续费作废
    public void refreshBudgetOrderNoEffect(String budgetOrder);

    public List<BudgetOrderFee> queryBudgetOrderFeeList(BudgetOrderFee condition);

    public BudgetOrderFee getBudgetOrderFee(String code);

    public void refreshBudgetOrderFee(BudgetOrderFee budgetOrderFee);

}
