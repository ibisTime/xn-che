package com.cdkj.loan.domain;

import java.util.Date;

import com.cdkj.loan.dao.base.ABaseDO;

public class ReqBudget extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -1440806324346784462L;

    private String code;// 编号

    private String companyCode;// 业务公司编号

    private String receiptAccount;// 收款账号编号

    private Long budgetAmount;// 预算金额

    private Date useDatetime;// 用款日期

    private String financeCheckNote;// 财务审核说明

    private Long payAmount;// 打款金额

    private String payAccount;// 打款账号编号

    private String waterBill;// 水单

    private Date payDatetime;// 打款时间(当天打款当天用 没用完收回来 用款日期当天没打款该请款单作废)

    private String applyUser;// 申请人

    private Date applyDatetime;// 申请时间

    private String payRemark;// 打款备注

    private Long dzAmount;// 垫资总额

    private Date dzDatetime;// 垫资日期

    private Long collectionAmount;// 收回款金额

    private String collectionAccount;// 收回款账号编号

    private Date collectionDatetime;// 收回款日期

    private String collectionRemark;// 收回款备注

    private String curNodeCode;// 节点编号

    /*-------辅助字段----------*/

    private Date applyDatetimeStart;// 申请时间起

    private Date applyDatetimeEnd;// 申请时间止

    private String roleCode; // 角色编号

    private String companyName; // 公司名称

    private String applyUserName; // 申请人姓名

    private String bankName; // 银行名称

    private String realName; // 户名

    private Date payDatetimeStart;// 打款时间起

    private Date payDatetimeEnd;// 打款时间止

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getReceiptAccount() {
        return receiptAccount;
    }

    public void setReceiptAccount(String receiptAccount) {
        this.receiptAccount = receiptAccount;
    }

    public Long getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Long budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public Date getUseDatetime() {
        return useDatetime;
    }

    public void setUseDatetime(Date useDatetime) {
        this.useDatetime = useDatetime;
    }

    public String getFinanceCheckNote() {
        return financeCheckNote;
    }

    public void setFinanceCheckNote(String financeCheckNote) {
        this.financeCheckNote = financeCheckNote;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(String waterBill) {
        this.waterBill = waterBill;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Long getDzAmount() {
        return dzAmount;
    }

    public void setDzAmount(Long dzAmount) {
        this.dzAmount = dzAmount;
    }

    public Date getDzDatetime() {
        return dzDatetime;
    }

    public void setDzDatetime(Date dzDatetime) {
        this.dzDatetime = dzDatetime;
    }

    public Long getCollectionAmount() {
        return collectionAmount;
    }

    public void setCollectionAmount(Long collectionAmount) {
        this.collectionAmount = collectionAmount;
    }

    public String getCollectionAccount() {
        return collectionAccount;
    }

    public void setCollectionAccount(String collectionAccount) {
        this.collectionAccount = collectionAccount;
    }

    public Date getCollectionDatetime() {
        return collectionDatetime;
    }

    public void setCollectionDatetime(Date collectionDatetime) {
        this.collectionDatetime = collectionDatetime;
    }

    public String getCollectionRemark() {
        return collectionRemark;
    }

    public void setCollectionRemark(String collectionRemark) {
        this.collectionRemark = collectionRemark;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public Date getPayDatetimeStart() {
        return payDatetimeStart;
    }

    public void setPayDatetimeStart(Date payDatetimeStart) {
        this.payDatetimeStart = payDatetimeStart;
    }

    public Date getPayDatetimeEnd() {
        return payDatetimeEnd;
    }

    public void setPayDatetimeEnd(Date payDatetimeEnd) {
        this.payDatetimeEnd = payDatetimeEnd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

}
