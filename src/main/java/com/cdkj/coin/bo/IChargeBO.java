package com.cdkj.coin.bo;

import java.util.List;

import com.cdkj.coin.bo.base.IPaginableBO;
import com.cdkj.coin.domain.Account;
import com.cdkj.coin.domain.Charge;
import com.cdkj.coin.enums.EBizType;
import com.cdkj.coin.enums.EChannelType;

public interface IChargeBO extends IPaginableBO<Charge> {
    String applyOrderOnline(Account account, String payGroup, String refNo,
            EBizType bizType, String bizNote, Long transAmount,
            EChannelType channelType, String applyUser);

    void callBackChange(Charge dbCharge, boolean booleanFlag);

    String applyOrderOffline(Account account, EBizType bizType, Long amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    void payOrder(Charge data, boolean booleanFlag, String payUser,
            String payNote);

    List<Charge> queryChargeList(Charge condition);

    Charge getCharge(String code, String systemCode);

}