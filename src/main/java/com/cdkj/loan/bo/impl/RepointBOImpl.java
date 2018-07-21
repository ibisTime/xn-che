package com.cdkj.loan.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.loan.bo.IRepointBO;
import com.cdkj.loan.bo.base.PaginableBOImpl;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.dao.IRepointDAO;
import com.cdkj.loan.domain.Repoint;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.exception.BizException;

/**
 * 
 * @author: jiafr 
 * @since: 2018年6月18日 下午2:53:01 
 * @history:
 */
@Component
public class RepointBOImpl extends PaginableBOImpl<Repoint> implements
        IRepointBO {

    @Autowired
    private IRepointDAO repointDAO;

    @Override
    public boolean isRepointExist(String code) {
        Repoint condition = new Repoint();
        condition.setCode(code);
        if (repointDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveRepoint(Repoint data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generate(EGeneratePrefix.REPOINT.getCode());
            data.setCode(code);
            repointDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeRepoint(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Repoint data = new Repoint();
            data.setCode(code);
            count = repointDAO.delete(data);
        }
        return count;
    }

    @Override
    public void refreshRepoint(Repoint data) {
        if (StringUtils.isNotBlank(data.getCode())) {
            repointDAO.update(data);
        }
    }

    @Override
    public List<Repoint> queryRepointList(Repoint condition) {
        return repointDAO.selectList(condition);
    }

    @Override
    public Repoint getRepoint(String code) {
        Repoint data = null;
        if (StringUtils.isNotBlank(code)) {
            Repoint condition = new Repoint();
            condition.setCode(code);
            data = repointDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "返点数据不存在");
            }
        }
        return data;
    }

    @Override
    public void confirmLoan(Repoint data) {

        if (null != data) {
            repointDAO.confirmLoan(data);
        }

    }
}
