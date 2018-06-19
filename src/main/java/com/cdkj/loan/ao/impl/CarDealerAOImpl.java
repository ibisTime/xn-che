package com.cdkj.loan.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.loan.ao.ICarDealerAO;
import com.cdkj.loan.bo.ICarDealerBO;
import com.cdkj.loan.bo.ICarDealerProtocolBO;
import com.cdkj.loan.bo.ICollectBankcardBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.CarDealer;
import com.cdkj.loan.domain.CarDealerProtocol;
import com.cdkj.loan.domain.CollectBankcard;
import com.cdkj.loan.dto.req.XN632060Req;
import com.cdkj.loan.dto.req.XN632062Req;
import com.cdkj.loan.enums.EApproveResult;
import com.cdkj.loan.enums.ECarDealerNode;
import com.cdkj.loan.enums.ECollectBankcardType;
import com.cdkj.loan.enums.EGeneratePrefix;
import com.cdkj.loan.enums.EbelongBank;

@Service
@Transactional
public class CarDealerAOImpl implements ICarDealerAO {

    @Autowired
    private ICarDealerBO carDealerBO;

    @Autowired
    ICollectBankcardBO collectBankcardBO;

    @Autowired
    ICarDealerProtocolBO carDealerProtocolBO;

    @Override
    public String addCarDealer(XN632060Req req) {
        CarDealer data = new CarDealer();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CARDEALER.getCode());
        data.setCode(code);
        data.setFullName(req.getFullName());
        data.setAbbrName(req.getAbbrName());
        data.setIsSelfDevelop(req.getIsSelfDevelop());
        data.setAddress(req.getAddress());
        data.setCarDealerType(req.getCarDealerType());
        data.setMainContact(req.getMainContact());
        data.setContactPhone(StringValidater.toLong(req.getContactPhone()));
        data.setMainBrand(req.getMainBrand());
        data.setParentGroup(req.getParentGroup());
        data.setAgreementValidDateStart(
            DateUtil.strToDate(req.getAgreementValidDateStart(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setAgreementValidDateEnd(DateUtil.strToDate(
            req.getAgreementValidDateEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setAgreementStatus(req.getAgreementStatus());
        data.setAgreementPic(req.getAgreementPic());
        data.setSettleWay(req.getSettleWay());
        data.setBusinessArea(req.getBusinessArea());
        data.setBelongBranchCompany(req.getBelongBranchCompany());
        data.setCurNodeCode(ECarDealerNode.TO_AUDIT.getCode());
        data.setApproveNote(req.getApproveNote());
        data.setPolicyNote(req.getPolicyNote());
        data.setRemark(req.getRemark());
        carDealerBO.saveCarDealer(data);
        // 经销商收款账号
        collectBankcardBO.saveCollectBankcardList(
            req.getJxsCollectBankcardList(),
            ECollectBankcardType.DEALER_COLLECT.getCode(), code);
        // 协议
        carDealerProtocolBO
            .saveCarDealerProtocolList(req.getCarDealerProtocolList(), code);
        // 工行返点账号
        collectBankcardBO.saveCollectBankcardList(
            req.getGsCollectBankcardList(),
            ECollectBankcardType.DEALER_REBATE.getCode(), EbelongBank.GH.getCode(),
            code);
        // 中行返点账号
        collectBankcardBO.saveCollectBankcardList(
            req.getZhCollectBankcardList(),
            ECollectBankcardType.DEALER_REBATE.getCode(), EbelongBank.ZH.getCode(),
            code);
        // 建行返点账号
        collectBankcardBO.saveCollectBankcardList(
            req.getJhCollectBankcardList(),
            ECollectBankcardType.DEALER_REBATE.getCode(), EbelongBank.JH.getCode(),
            code);

        return code;
    }

    @Override
    @Transactional
    public void editCarDealer(XN632062Req req) {
        CarDealer data = carDealerBO.getCarDealer(req.getCode());
        // 删除经销商下的收款账号
        collectBankcardBO.removeCollectBankcardByCompanyCode(data.getCode());
        // 删除协议
        carDealerProtocolBO
            .removeCarDealerProtocolByCarDealerCode(data.getCode());

        data.setFullName(req.getFullName());
        data.setAbbrName(req.getAbbrName());
        data.setIsSelfDevelop(req.getIsSelfDevelop());
        data.setAddress(req.getAddress());
        data.setCarDealerType(req.getCarDealerType());
        data.setMainContact(req.getMainContact());
        data.setContactPhone(StringValidater.toLong(req.getContactPhone()));
        data.setMainBrand(req.getMainBrand());
        data.setParentGroup(req.getParentGroup());
        data.setAgreementValidDateStart(
            DateUtil.strToDate(req.getAgreementValidDateStart(),
                DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setAgreementValidDateEnd(DateUtil.strToDate(
            req.getAgreementValidDateEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setAgreementStatus(req.getAgreementStatus());
        data.setAgreementPic(req.getAgreementPic());
        data.setSettleWay(req.getSettleWay());
        data.setBusinessArea(req.getBusinessArea());
        data.setBelongBranchCompany(req.getBelongBranchCompany());

        data.setApproveNote(req.getApproveNote());
        data.setPolicyNote(req.getPolicyNote());
        data.setRemark(req.getRemark());
        carDealerBO.refreshCarDealer(data);

        // 保存
        collectBankcardBO.saveCollectBankcardList(
            req.getJxsCollectBankcardList(),
            ECollectBankcardType.DEALER_COLLECT.getCode(), req.getCode());

        carDealerProtocolBO.saveCarDealerProtocolList(
            req.getCarDealerProtocolList(), req.getCode());

        collectBankcardBO.saveCollectBankcardList(
            req.getGsCollectBankcardList(),
            ECollectBankcardType.DEALER_REBATE.getCode(), EbelongBank.GH.getCode(),
            req.getCode());

        collectBankcardBO.saveCollectBankcardList(
            req.getZhCollectBankcardList(),
            ECollectBankcardType.DEALER_REBATE.getCode(), EbelongBank.ZH.getCode(),
            req.getCode());

        collectBankcardBO.saveCollectBankcardList(
            req.getJhCollectBankcardList(),
            ECollectBankcardType.DEALER_REBATE.getCode(), EbelongBank.JH.getCode(),
            req.getCode());

    }

    // 审核
    @Override
    public void audit(String code, String auditResult, String auditor,
            String approveNote) {
        CarDealer carDealer = carDealerBO.getCarDealer(code);
        if (EApproveResult.PASS.getCode().equals(auditResult)) {
            carDealer.setCurNodeCode(ECarDealerNode.AUDIT_PASS.getCode());
        } else {
            carDealer.setCurNodeCode(ECarDealerNode.AUDIT_NOT_PASS.getCode());
        }
        carDealer.setApproveNote(approveNote);
        carDealerBO.refreshCarDealerNode(carDealer);
    }

    @Override
    public int dropCarDealer(String code) {
        return carDealerBO.removeCarDealer(code);
    }

    @Override
    public Paginable<CarDealer> queryCarDealerPage(int start, int limit,
            CarDealer condition) {
        Paginable<CarDealer> results = carDealerBO.getPaginable(start, limit,
            condition);
        if (CollectionUtils.isNotEmpty(results.getList())) {
            for (CarDealer carDealer : results.getList()) {
                CollectBankcard collectBankcard = new CollectBankcard();
                collectBankcard.setCompanyCode(carDealer.getCode());
                collectBankcard
                    .setType(ECollectBankcardType.DEALER_COLLECT.getCode());
                List<CollectBankcard> jxsCollectBankcardList = collectBankcardBO
                    .queryCollectBankcardList(collectBankcard);
                carDealer.setJxsCollectBankcardList(jxsCollectBankcardList);
                collectBankcard
                    .setType(ECollectBankcardType.DEALER_REBATE.getCode());
                List<CollectBankcard> queryCollectBankcardList = collectBankcardBO
                    .queryCollectBankcardList(collectBankcard);

                // 协议
                CarDealerProtocol carDealerProtocol = new CarDealerProtocol();
                carDealerProtocol.setCarDealerCode(carDealer.getCode());
                List<CarDealerProtocol> queryCarDealerProtocolList = carDealerProtocolBO
                    .queryCarDealerProtocolList(carDealerProtocol);
                carDealer.setCarDealerProtocolList(queryCarDealerProtocolList);

                List<CollectBankcard> ghList = new ArrayList<CollectBankcard>();
                List<CollectBankcard> zhList = new ArrayList<CollectBankcard>();
                List<CollectBankcard> jhList = new ArrayList<CollectBankcard>();
                for (CollectBankcard collectBankcard2 : queryCollectBankcardList) {
                    // 工行
                    if (collectBankcard2.getBelongBank()
                        .equals(EbelongBank.GH.getCode())) {
                        ghList.add(collectBankcard2);
                    }
                    // 中行
                    if (collectBankcard2.getBelongBank()
                        .equals(EbelongBank.ZH.getCode())) {
                        zhList.add(collectBankcard2);
                    }
                    // 建行
                    if (collectBankcard2.getBelongBank()
                        .equals(EbelongBank.JH.getCode())) {
                        jhList.add(collectBankcard2);
                    }
                }
                carDealer.setGsCollectBankcardList(ghList);
                carDealer.setZhCollectBankcardList(zhList);
                carDealer.setJhCollectBankcardList(jhList);

            }
        }

        return results;
    }

    @Override
    public List<CarDealer> queryCarDealerList(CarDealer condition) {
        List<CarDealer> queryCarDealerList = carDealerBO
            .queryCarDealerList(condition);
        for (CarDealer carDealer : queryCarDealerList) {
            // 收款账号
            CollectBankcard collectBankcard = new CollectBankcard();
            collectBankcard.setCompanyCode(carDealer.getCode());
            collectBankcard.setType(ECollectBankcardType.DEALER_COLLECT.getCode());
            List<CollectBankcard> jxsCollectBankcardList = collectBankcardBO
                .queryCollectBankcardList(collectBankcard);
            carDealer.setJxsCollectBankcardList(jxsCollectBankcardList);// 经销商收款账号

            // 协议
            CarDealerProtocol carDealerProtocol = new CarDealerProtocol();
            carDealerProtocol.setCarDealerCode(carDealer.getCode());
            List<CarDealerProtocol> queryCarDealerProtocolList = carDealerProtocolBO
                .queryCarDealerProtocolList(carDealerProtocol);
            carDealer.setCarDealerProtocolList(queryCarDealerProtocolList);

            // 返点账号
            collectBankcard.setType(ECollectBankcardType.DEALER_REBATE.getCode());
            List<CollectBankcard> queryCollectBankcardList = collectBankcardBO
                .queryCollectBankcardList(collectBankcard);

            List<CollectBankcard> ghList = new ArrayList<CollectBankcard>();
            List<CollectBankcard> zhList = new ArrayList<CollectBankcard>();
            List<CollectBankcard> jhList = new ArrayList<CollectBankcard>();
            for (CollectBankcard domain : queryCollectBankcardList) {
                // 工行
                if (domain.getBelongBank().equals(EbelongBank.GH.getCode())) {
                    ghList.add(domain);
                }
                // 中行
                if (domain.getBelongBank().equals(EbelongBank.ZH.getCode())) {
                    zhList.add(domain);
                }
                // 建行
                if (domain.getBelongBank().equals(EbelongBank.JH.getCode())) {
                    jhList.add(domain);
                }
            }
            carDealer.setGsCollectBankcardList(ghList);
            carDealer.setZhCollectBankcardList(zhList);
            carDealer.setJhCollectBankcardList(jhList);
        }
        return queryCarDealerList;
    }

    @Override
    public CarDealer getCarDealer(String code) {
        CarDealer carDealer = carDealerBO.getCarDealer(code);

        CollectBankcard collectBankcard = new CollectBankcard();
        collectBankcard.setCompanyCode(carDealer.getCode());
        collectBankcard.setType(ECollectBankcardType.DEALER_COLLECT.getCode());
        List<CollectBankcard> jxsCollectBankcardList = collectBankcardBO
            .queryCollectBankcardList(collectBankcard);
        carDealer.setJxsCollectBankcardList(jxsCollectBankcardList);
        collectBankcard.setType(ECollectBankcardType.DEALER_REBATE.getCode());
        List<CollectBankcard> queryCollectBankcardList = collectBankcardBO
            .queryCollectBankcardList(collectBankcard);

        // 协议
        CarDealerProtocol carDealerProtocol = new CarDealerProtocol();
        carDealerProtocol.setCarDealerCode(carDealer.getCode());
        List<CarDealerProtocol> queryCarDealerProtocolList = carDealerProtocolBO
            .queryCarDealerProtocolList(carDealerProtocol);
        carDealer.setCarDealerProtocolList(queryCarDealerProtocolList);

        List<CollectBankcard> ghList = new ArrayList<CollectBankcard>();
        List<CollectBankcard> zhList = new ArrayList<CollectBankcard>();
        List<CollectBankcard> jhList = new ArrayList<CollectBankcard>();
        for (CollectBankcard domain : queryCollectBankcardList) {
            // 工行
            if (domain.getBelongBank().equals(EbelongBank.GH.getCode())) {
                ghList.add(domain);
            }
            // 中行
            if (domain.getBelongBank().equals(EbelongBank.ZH.getCode())) {
                zhList.add(domain);
            }
            // 建行
            if (domain.getBelongBank().equals(EbelongBank.JH.getCode())) {
                jhList.add(domain);
            }
        }
        carDealer.setGsCollectBankcardList(ghList);
        carDealer.setZhCollectBankcardList(zhList);
        carDealer.setJhCollectBankcardList(jhList);

        return carDealer;
    }

}
