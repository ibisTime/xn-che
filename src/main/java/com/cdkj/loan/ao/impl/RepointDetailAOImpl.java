package com.cdkj.loan.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.IRepointDetailAO;
import com.cdkj.loan.bo.IBudgetOrderBO;
import com.cdkj.loan.bo.ICarDealerBO;
import com.cdkj.loan.bo.ICarDealerProtocolBO;
import com.cdkj.loan.bo.ICollectBankcardBO;
import com.cdkj.loan.bo.IDepartmentBO;
import com.cdkj.loan.bo.IRepointDetailBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.AmountUtil;
import com.cdkj.loan.domain.BudgetOrder;
import com.cdkj.loan.domain.CarDealer;
import com.cdkj.loan.domain.CarDealerProtocol;
import com.cdkj.loan.domain.CollectBankcard;
import com.cdkj.loan.domain.Department;
import com.cdkj.loan.domain.RepointDetail;
import com.cdkj.loan.dto.req.XN632290Req;
import com.cdkj.loan.dto.res.XN632290Res;
import com.cdkj.loan.enums.ECollectBankcard;
import com.cdkj.loan.enums.EUseMoneyPurpose;
import com.cdkj.loan.exception.BizException;

/**
 * 
 * @author: jiafr 
 * @since: 2018年6月16日 下午2:43:55 
 * @history:
 */
@Service
public class RepointDetailAOImpl implements IRepointDetailAO {

    @Autowired
    private IRepointDetailBO repointDetailBO;

    @Autowired
    private ICarDealerProtocolBO carDealerProtocolBO;

    @Autowired
    private ICollectBankcardBO collectBankcardBO;

    @Autowired
    private ICarDealerBO carDealerBO;

    @Autowired
    private IBudgetOrderBO budgetOrderBO;

    @Autowired
    private IDepartmentBO departmentBO;

    @Override
    public String addRepointDetail(RepointDetail data) {
        return repointDetailBO.saveRepointDetail(data);
    }

    @Override
    public int editRepointDetail(RepointDetail data) {
        if (!repointDetailBO.isRepointDetailExist(data.getCode())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return repointDetailBO.refreshRepointDetail(data);
    }

    @Override
    public int dropRepointDetail(String code) {
        if (!repointDetailBO.isRepointDetailExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return repointDetailBO.removeRepointDetail(code);
    }

    @Override
    public Paginable<RepointDetail> queryRepointDetailPage(int start,
            int limit, RepointDetail condition) {
        return repointDetailBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<RepointDetail> queryRepointDetailList(RepointDetail condition) {
        return repointDetailBO.queryRepointDetailList(condition);
    }

    @Override
    public RepointDetail getRepointDetail(String code) {
        return repointDetailBO.getRepointDetail(code);
    }

    @Override
    public List<XN632290Res> showRepointDetail(XN632290Req req) {

        BudgetOrder budgetOrder = budgetOrderBO.getBudgetOrder(req
            .getBudgetOrderCode());
        List<XN632290Res> resList = new ArrayList<XN632290Res>();
        // 协议内返点
        CarDealerProtocol condition = new CarDealerProtocol();
        condition.setCarDealerCode(req.getCarDealerCode());
        List<CarDealerProtocol> list = carDealerProtocolBO
            .queryCarDealerProtocolList(condition);
        for (CarDealerProtocol carDealerProtocol : list) {
            XN632290Res res = new XN632290Res();
            res.setUseMoneyPurpose(EUseMoneyPurpose.PROTOCOL_INNER.getCode());
            // 1单笔 2贷款额百分比
            if ("1".equals(carDealerProtocol.getReturnPointType())) {
                res.setRepointAmount(carDealerProtocol.getReturnPointFee());
            } else if ("2".equals(carDealerProtocol.getReturnPointType())) {
                Long loanAmount = budgetOrder.getLoanAmount();
                Double returnPointRate = carDealerProtocol.getReturnPointRate();
                Long RepointAmount = AmountUtil
                    .mul(loanAmount, returnPointRate);
                res.setRepointAmount(RepointAmount);
            }
            res.setId(String.valueOf(carDealerProtocol.getId()));
            CollectBankcard condition1 = new CollectBankcard();
            condition1.setCompanyCode(req.getCarDealerCode());
            condition1.setType(ECollectBankcard.DEALER_REBATE.getCode());
            List<CollectBankcard> list1 = collectBankcardBO
                .queryCollectBankcardList(condition1);
            CollectBankcard bankcard1 = list1.get(0);
            res.setAccountCode(bankcard1.getBankcardNumber());
            CarDealer carDealer = carDealerBO.getCarDealer(req
                .getCarDealerCode());
            res.setCompanyName(carDealer.getFullName());
            res.setBankcardNumber(bankcard1.getBankcardNumber());
            res.setSubbranch(bankcard1.getSubbranch());
            resList.add(res);
        }

        // 应退按揭款
        XN632290Res res = new XN632290Res();
        res.setUseMoneyPurpose(EUseMoneyPurpose.MORTGAGE.getCode());
        res.setRepointAmount(budgetOrder.getLoanAmount());
        Department department = departmentBO.getDepartment(budgetOrder
            .getCompanyCode());
        res.setCompanyName(department.getName());

        CollectBankcard condition2 = new CollectBankcard();
        condition2.setCompanyCode(department.getCode());
        List<CollectBankcard> list3 = collectBankcardBO
            .queryCollectBankcardList(condition2);
        CollectBankcard bankcard2 = list3.get(0);
        res.setBankcardNumber(bankcard2.getBankcardNumber());
        res.setSubbranch(bankcard2.getSubbranch());
        resList.add(res);
        return resList;
    }
}