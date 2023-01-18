package ir.demisco.cfs.model.dto.request;

import java.time.LocalDateTime;

public class FinancialUserPermissionInputModelRequest {
    private Long userPermissionScopeId;
    private Long financialUserIdCreator;
    private Long financialTypeActivityId;
    private Long financialDocumentTypeId;
    private Long financialPeriodId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Long allDocumentTypeFlag;
    private Long allFinancialPeriodFlag;
    private Long id;

    public Long getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(Long userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
    }

    public Long getFinancialUserIdCreator() {
        return financialUserIdCreator;
    }

    public void setFinancialUserIdCreator(Long financialUserIdCreator) {
        this.financialUserIdCreator = financialUserIdCreator;
    }

    public Long getFinancialTypeActivityId() {
        return financialTypeActivityId;
    }

    public void setFinancialTypeActivityId(Long financialTypeActivityId) {
        this.financialTypeActivityId = financialTypeActivityId;
    }

    public Long getFinancialDocumentTypeId() {
        return financialDocumentTypeId;
    }

    public void setFinancialDocumentTypeId(Long financialDocumentTypeId) {
        this.financialDocumentTypeId = financialDocumentTypeId;
    }

    public Long getFinancialPeriodId() {
        return financialPeriodId;
    }

    public void setFinancialPeriodId(Long financialPeriodId) {
        this.financialPeriodId = financialPeriodId;
    }

    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDateTime getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(LocalDateTime disableDate) {
        this.disableDate = disableDate;
    }

    public Long getAllDocumentTypeFlag() {
        return allDocumentTypeFlag;
    }

    public void setAllDocumentTypeFlag(Long allDocumentTypeFlag) {
        this.allDocumentTypeFlag = allDocumentTypeFlag;
    }

    public Long getAllFinancialPeriodFlag() {
        return allFinancialPeriodFlag;
    }

    public void setAllFinancialPeriodFlag(Long allFinancialPeriodFlag) {
        this.allFinancialPeriodFlag = allFinancialPeriodFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
