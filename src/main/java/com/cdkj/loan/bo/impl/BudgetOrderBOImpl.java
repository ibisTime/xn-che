package com.cdkj.loan.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IBudgetOrderBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.IBudgetOrderDAO;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.exception.BizException;

@Component
public class BudgetOrderBOImpl extends PaginableBOImpl<BudgetOrder>
        implements IBudgetOrderBO {

    @Autowired
    private IBudgetOrderDAO budgetOrderDAO;

    public String saveBudgetOrder(BudgetOrder data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater
                .generate(EGeneratePrefix.BUDGETORDER.getCode());
            data.setCode(code);
            budgetOrderDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeBudgetOrder(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            BudgetOrder data = new BudgetOrder();
            data.setCode(code);
            count = budgetOrderDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshBudgetOrder(BudgetOrder data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = budgetOrderDAO.update(data);
        }
        return count;
    }

    @Override
    public List<BudgetOrder> queryBudgetOrderList(BudgetOrder condition) {
        return budgetOrderDAO.selectList(condition);
    }

    @Override
    public BudgetOrder getBudgetOrder(String code) {
        BudgetOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            BudgetOrder condition = new BudgetOrder();
            condition.setCode(code);
            data = budgetOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }
}
