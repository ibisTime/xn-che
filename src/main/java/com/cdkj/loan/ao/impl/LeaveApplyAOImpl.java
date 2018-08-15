package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.ILeaveApplyAO;
import com.cdkj.loan.bo.IArchiveBO;
import com.cdkj.loan.bo.ILeaveApplyBO;
import com.cdkj.loan.bo.ISYSConfigBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.common.SysConstants;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Archive;
import com.cdkj.loan.domain.LeaveApply;
import com.cdkj.loan.domain.SYSConfig;
import com.cdkj.loan.dto.req.XN632890Req;
import com.cdkj.loan.dto.req.XN632891Req;
import com.cdkj.loan.dto.res.XN632892Res;
import com.cdkj.loan.enums.ELeaveApplyStatus;
import com.cdkj.loan.exception.BizException;

/**
 * 请假管理
 * @author: silver 
 * @since: 2018年6月5日 下午8:27:05 
 * @history:
 */
@Service
public class LeaveApplyAOImpl implements ILeaveApplyAO {
    @Autowired
    private ILeaveApplyBO leaveApplyBO;

    @Autowired
    private IArchiveBO archiveBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public String addLeaveApply(XN632890Req req) {
        // 判断人事档案中是否存在
        archiveBO.doCheck(req.getApplyUser());

        LeaveApply data = new LeaveApply();
        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());
        data.setType(req.getType());
        data.setReason(req.getReason());
        data.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));
        data.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.DATA_TIME_PATTERN_2));

        data.setTotalHour(StringValidater.toInteger(req.getTotalHour()));
        data.setPdf(req.getPdf());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setStatus(ELeaveApplyStatus.TO_APPROVE.getCode());

        return leaveApplyBO.saveLeaveApply(data);
    }

    @Override
    public void approveLeaveApply(XN632891Req req) {
        if (!leaveApplyBO.isLeaveApplyExist(req.getCode())) {
            throw new BizException("xn0000", "请假申请不存在");
        }
        LeaveApply leaveApply = leaveApplyBO.getLeaveApply(req.getCode());
        if (!ELeaveApplyStatus.TO_APPROVE.getCode().equals(
            leaveApply.getStatus())) {
            throw new BizException("xn0000", "申请单不在待审核状态!");
        }

        LeaveApply data = new LeaveApply();
        if (req.getResult().equals(ELeaveApplyStatus.APPROVE_PASS.getCode())) {
            data.setStatus(ELeaveApplyStatus.APPROVE_PASS.getCode());
        } else if (req.getResult().equals(
            ELeaveApplyStatus.APPROVE_FAIL.getCode())) {
            data.setStatus(ELeaveApplyStatus.APPROVE_FAIL.getCode());
        }
        data.setCode(req.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());

        leaveApplyBO.approveLeaveApply(data);
    }

    @Override
    public Paginable<LeaveApply> queryLeaveApplyPage(int start, int limit,
            LeaveApply condition) {
        Paginable<LeaveApply> page = leaveApplyBO.getPaginable(start, limit,
            condition);
        List<LeaveApply> leaveApplyList = page.getList();
        for (LeaveApply leaveApply : leaveApplyList) {
            Archive archive = new Archive();
            archive.setUserId(leaveApply.getApplyUser());
            leaveApply.setApplyUserArchive(archiveBO.queryArchiveList(archive));
        }
        return page;
    }

    @Override
    public List<LeaveApply> queryLeaveApplyList(LeaveApply condition) {
        return leaveApplyBO.queryLeaveApplyList(condition);
    }

    @Override
    public LeaveApply getLeaveApply(String code) {
        Archive archive = new Archive();
        LeaveApply leaveApply = leaveApplyBO.getLeaveApply(code);
        archive.setUserId(leaveApply.getApplyUser());
        leaveApply.setApplyUserArchive(archiveBO.queryArchiveList(archive));
        return leaveApply;
    }

    @Override
    public XN632892Res getLeavedHour(String applyUser) {
        SYSConfig sysConfig = sysConfigBO
            .getSYSConfig(SysConstants.LEAVE_YEAR_HOUR);
        Long leavedHore = leaveApplyBO.getTotalHour(applyUser);
        Long totalHour = StringValidater.toLong(sysConfig.getCvalue());
        Long remainHour = totalHour - leavedHore;

        XN632892Res res = new XN632892Res();
        res.setLeavedHour(leavedHore);
        res.setTotalHour(totalHour);
        res.setRemainHour(remainHour);
        return res;
    }
}
