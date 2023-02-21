package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialSecUserPermissionScopeResponse {
    private Long userPermissionScopeId;
    private Long financialUserCreatorId;
    private Long financialActivityTypeId;
    private Long financialDocumentTypeId;
    private Long financialPeriodId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Long allDocumentTypeFlag;
    private Long allFinancialPeriodFlag;
    private Long selfCreatorFlag;
    private Long allCreatorFlag;
    private String activityTypeCode;
    private String activityTypeDescription;
    private String subSystemCode;
    private String subSystemDescription;

    public Long getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(Long userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
    }

    public Long getFinancialUserCreatorId() {
        return financialUserCreatorId;
    }

    public void setFinancialUserCreatorId(Long financialUserCreatorId) {
        this.financialUserCreatorId = financialUserCreatorId;
    }

    public Long getFinancialActivityTypeId() {
        return financialActivityTypeId;
    }

    public void setFinancialActivityTypeId(Long financialActivityTypeId) {
        this.financialActivityTypeId = financialActivityTypeId;
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

    public Long getSelfCreatorFlag() {
        return selfCreatorFlag;
    }

    public void setSelfCreatorFlag(Long selfCreatorFlag) {
        this.selfCreatorFlag = selfCreatorFlag;
    }

    public Long getAllCreatorFlag() {
        return allCreatorFlag;
    }

    public void setAllCreatorFlag(Long allCreatorFlag) {
        this.allCreatorFlag = allCreatorFlag;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public void setActivityTypeCode(String activityTypeCode) {
        this.activityTypeCode = activityTypeCode;
    }

    public String getActivityTypeDescription() {
        return activityTypeDescription;
    }

    public void setActivityTypeDescription(String activityTypeDescription) {
        this.activityTypeDescription = activityTypeDescription;
    }

    public String getSubSystemCode() {
        return subSystemCode;
    }

    public void setSubSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
    }

    public String getSubSystemDescription() {
        return subSystemDescription;
    }

    public void setSubSystemDescription(String subSystemDescription) {
        this.subSystemDescription = subSystemDescription;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialSecUserPermissionScopeResponse financialSecUserPermissionScopeResponse;

        private Builder() {
            financialSecUserPermissionScopeResponse = new FinancialSecUserPermissionScopeResponse();
        }


        public Builder userPermissionScopeId(Long userPermissionScopeId) {
            financialSecUserPermissionScopeResponse.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public Builder financialUserCreatorId(Long financialUserCreatorId) {
            financialSecUserPermissionScopeResponse.setFinancialUserCreatorId(financialUserCreatorId);
            return this;
        }

        public Builder financialActivityTypeId(Long financialActivityTypeId) {
            financialSecUserPermissionScopeResponse.setFinancialActivityTypeId(financialActivityTypeId);
            return this;
        }

        public Builder financialDocumentTypeId(Long financialDocumentTypeId) {
            financialSecUserPermissionScopeResponse.setFinancialDocumentTypeId(financialDocumentTypeId);
            return this;
        }

        public Builder financialPeriodId(Long financialPeriodId) {
            financialSecUserPermissionScopeResponse.setFinancialPeriodId(financialPeriodId);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialSecUserPermissionScopeResponse.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialSecUserPermissionScopeResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder allDocumentTypeFlag(Long allDocumentTypeFlag) {
            financialSecUserPermissionScopeResponse.setAllDocumentTypeFlag(allDocumentTypeFlag);
            return this;
        }

        public Builder allFinancialPeriodFlag(Long allFinancialPeriodFlag) {
            financialSecUserPermissionScopeResponse.setAllFinancialPeriodFlag(allFinancialPeriodFlag);
            return this;
        }

        public Builder selfCreatorFlag(Long selfCreatorFlag) {
            financialSecUserPermissionScopeResponse.setSelfCreatorFlag(selfCreatorFlag);
            return this;
        }

        public Builder allCreatorFlag(Long allCreatorFlag) {
            financialSecUserPermissionScopeResponse.setAllCreatorFlag(allCreatorFlag);
            return this;
        }

        public Builder activityTypeCode(String activityTypeCode) {
            financialSecUserPermissionScopeResponse.setActivityTypeCode(activityTypeCode);
            return this;
        }

        public Builder activityTypeDescription(String activityTypeDescription) {
            financialSecUserPermissionScopeResponse.setActivityTypeDescription(activityTypeDescription);
            return this;
        }

        public Builder subSystemCode(String subSystemCode) {
            financialSecUserPermissionScopeResponse.setSubSystemCode(subSystemCode);
            return this;
        }

        public Builder subSystemDescription(String subSystemDescription) {
            financialSecUserPermissionScopeResponse.setSubSystemDescription(subSystemDescription);
            return this;
        }

        public FinancialSecUserPermissionScopeResponse build() {
            return financialSecUserPermissionScopeResponse;
        }
    }
}
