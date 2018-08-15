package com.cdkj.loan.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.loan.ao.IConvertApplyAO;
import com.cdkj.loan.bo.IArchiveBO;
import com.cdkj.loan.bo.IConvertApplyBO;
import com.cdkj.loan.bo.IEntryApplyBO;
import com.cdkj.loan.bo.IProbationAssessBO;
import com.cdkj.loan.bo.ISYSUserBO;
import com.cdkj.loan.bo.base.Paginable;
import com.cdkj.loan.common.DateUtil;
import com.cdkj.loan.core.OrderNoGenerater;
import com.cdkj.loan.core.StringValidater;
import com.cdkj.loan.domain.Archive;
import com.cdkj.loan.domain.ConvertApply;
import com.cdkj.loan.domain.EntryApply;
import com.cdkj.loan.domain.ProbationAssess;
import com.cdkj.loan.domain.SYSUser;
import com.cdkj.loan.dto.req.XN632870Req;
import com.cdkj.loan.dto.req.XN632870ReqProbationAssess;
import com.cdkj.loan.enums.EApproveResult;
import com.cdkj.loan.enums.EConvertApplyStatus;
import com.cdkj.loan.enums.EEntryApplyStatus;
import com.cdkj.loan.enums.EGeneratePrefix;

@Service
public class ConvertApplyAOImpl implements IConvertApplyAO {

    @Autowired
    private IConvertApplyBO convertApplyBO;

    @Autowired
    private IProbationAssessBO probationAssessBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IArchiveBO archiveBO;

    @Autowired
    private IEntryApplyBO entryApplyBO;

    @Override
    public String addConvertApply(XN632870Req req) {
        ConvertApply data = new ConvertApply();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.CONVERTAPPLY.getCode());
        data.setCode(code);
        data.setEntryCode(req.getEntryCode());
        data.setApplyUser(req.getApplyUser());
        data.setApplyDatetime(new Date());

        EntryApply entryApply = entryApplyBO.getEntryApply(req.getEntryCode());
        data.setEntryDatetime(entryApply.getEntryDatetime());
        data.setProbationStartDatetime(entryApply.getProbationStartDatetime());
        data.setProbationEndDatetime(entryApply.getProbationEndDatetime());

        data.setWorkSummary(req.getWorkSummary());
        data.setAllEvaluation(req.getAllEvaluation());
        data.setIsFullWorker(req.getIsFullWorker());
        data.setEffectDatetime(DateUtil.strToDate(req.getEffectDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        data.setStatus(EConvertApplyStatus.STAY_AUDIT.getCode());
        data.setRemark(req.getRemark());

        List<XN632870ReqProbationAssess> list = req.getProbationAssessList();
        for (XN632870ReqProbationAssess reqpa : list) {
            ProbationAssess probationAssess = new ProbationAssess();
            probationAssess.setConvertCode(code);
            probationAssess.setEvalItem(reqpa.getEvalItem());
            Integer grade = StringValidater.toInteger(reqpa.getGrade());
            probationAssess.setGrade(grade);
            probationAssessBO.saveProbationAssess(probationAssess);
        }
        convertApplyBO.saveConvertApply(data);
        return code;
    }

    @Override
    public void approveConvertApply(String code, String approveResult,
            String updater, String remark) {
        ConvertApply convertApply = convertApplyBO.getConvertApply(code);
        if (EApproveResult.PASS.getCode().equals(approveResult)) {
            convertApply.setStatus(EEntryApplyStatus.AUDIT_PASS.getCode());
        } else {
            convertApply.setStatus(EEntryApplyStatus.AUDIT_NOT_PASS.getCode());
        }
        convertApply.setUpdater(updater);
        convertApply.setUpdateDatetime(new Date());
        convertApply.setRemark(remark);
        convertApplyBO.updateConvertApplyStatus(convertApply);
    }

    @Override
    public int editConvertApply(ConvertApply data) {
        return convertApplyBO.refreshConvertApply(data);
    }

    @Override
    public Paginable<ConvertApply> queryConvertApplyPage(int start, int limit,
            ConvertApply condition) {
        Paginable<ConvertApply> paginable = convertApplyBO.getPaginable(start,
            limit, condition);
        if (paginable != null) {
            for (ConvertApply convertApply : paginable.getList()) {
                initConvertApply(convertApply);
            }
        }
        return paginable;
    }

    @Override
    public List<ConvertApply> queryConvertApplyList(ConvertApply condition) {
        List<ConvertApply> queryConvertApplyList = convertApplyBO
            .queryConvertApplyList(condition);
        for (ConvertApply convertApply : queryConvertApplyList) {
            initConvertApply(convertApply);
        }
        return queryConvertApplyList;
    }

    @Override
    public ConvertApply getConvertApply(String code) {
        ConvertApply convertApply = convertApplyBO.getConvertApply(code);
        initConvertApply(convertApply);
        return convertApply;
    }

    private void initConvertApply(ConvertApply convertApply) {
        SYSUser user = sysUserBO.getUser(convertApply.getApplyUser());
        convertApply.setUser(user);
        Archive archive = archiveBO
            .getArchiveByUserid(convertApply.getApplyUser());
        convertApply.setArchice(archive);
        EntryApply entryApply = entryApplyBO
            .getEntryApply(convertApply.getEntryCode());
        convertApply.setEntryApply(entryApply);
        List<ProbationAssess> list = probationAssessBO
            .queryProbationAssessListByConvertApplyCode(convertApply.getCode());
        convertApply.setProbationAssessesList(list);
        int totalGrade = 0;
        for (ProbationAssess probationAssess : list) {
            totalGrade += probationAssess.getGrade();
        }
        convertApply.setTotalGrade(totalGrade);
    }
}
