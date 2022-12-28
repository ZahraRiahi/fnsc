package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialSecPermissionScopeOutputResponse {
    private Long userPermissionScopeId;
    private Long financialUserId;
    private Long financialGroupId;
    private Long financialDepartmentId;
    private Long departmentId;
    private Long financialLedgerTypeId;
    private Long applicationUserId;
    private Boolean allLedgerTypesFlag;
    private Boolean allFncDepartmentFlag;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private String financialUserName;
    private String financialGroupCode;
    private String financialGroupDescription;
    private String departmentCode;
    private String departmentName;
    private String financialDepartmentCode;
    private String financialDepartmentName;
    private String ledgerTypeDescription;

    public Long getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(Long userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
    }

    public Long getFinancialUserId() {
        return financialUserId;
    }

    public void setFinancialUserId(Long financialUserId) {
        this.financialUserId = financialUserId;
    }

    public Long getFinancialGroupId() {
        return financialGroupId;
    }

    public void setFinancialGroupId(Long financialGroupId) {
        this.financialGroupId = financialGroupId;
    }

    public Long getFinancialDepartmentId() {
        return financialDepartmentId;
    }

    public void setFinancialDepartmentId(Long financialDepartmentId) {
        this.financialDepartmentId = financialDepartmentId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getFinancialLedgerTypeId() {
        return financialLedgerTypeId;
    }

    public void setFinancialLedgerTypeId(Long financialLedgerTypeId) {
        this.financialLedgerTypeId = financialLedgerTypeId;
    }

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public Boolean getAllLedgerTypesFlag() {
        return allLedgerTypesFlag;
    }

    public void setAllLedgerTypesFlag(Boolean allLedgerTypesFlag) {
        this.allLedgerTypesFlag = allLedgerTypesFlag;
    }

    public Boolean getAllFncDepartmentFlag() {
        return allFncDepartmentFlag;
    }

    public void setAllFncDepartmentFlag(Boolean allFncDepartmentFlag) {
        this.allFncDepartmentFlag = allFncDepartmentFlag;
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

    public String getFinancialUserName() {
        return financialUserName;
    }

    public void setFinancialUserName(String financialUserName) {
        this.financialUserName = financialUserName;
    }

    public String getFinancialGroupCode() {
        return financialGroupCode;
    }

    public void setFinancialGroupCode(String financialGroupCode) {
        this.financialGroupCode = financialGroupCode;
    }

    public String getFinancialGroupDescription() {
        return financialGroupDescription;
    }

    public void setFinancialGroupDescription(String financialGroupDescription) {
        this.financialGroupDescription = financialGroupDescription;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFinancialDepartmentCode() {
        return financialDepartmentCode;
    }

    public void setFinancialDepartmentCode(String financialDepartmentCode) {
        this.financialDepartmentCode = financialDepartmentCode;
    }

    public String getFinancialDepartmentName() {
        return financialDepartmentName;
    }

    public void setFinancialDepartmentName(String financialDepartmentName) {
        this.financialDepartmentName = financialDepartmentName;
    }

    public String getLedgerTypeDescription() {
        return ledgerTypeDescription;
    }

    public void setLedgerTypeDescription(String ledgerTypeDescription) {
        this.ledgerTypeDescription = ledgerTypeDescription;
    }
    public static FinancialSecPermissionScopeOutputResponse.Builder builder() {
        return new FinancialSecPermissionScopeOutputResponse.Builder();
    }

    public static final class Builder {
        private FinancialSecPermissionScopeOutputResponse financialSecPermissionScopeOutputResponse;

        private Builder() {
            financialSecPermissionScopeOutputResponse = new FinancialSecPermissionScopeOutputResponse();
        }

        public static Builder aFinancialSecPermissionScopeOutputResponse() {
            return new Builder();
        }

        public Builder userPermissionScopeId(Long userPermissionScopeId) {
            financialSecPermissionScopeOutputResponse.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public Builder financialUserId(Long financialUserId) {
            financialSecPermissionScopeOutputResponse.setFinancialUserId(financialUserId);
            return this;
        }

        public Builder financialGroupId(Long financialGroupId) {
            financialSecPermissionScopeOutputResponse.setFinancialGroupId(financialGroupId);
            return this;
        }

        public Builder financialDepartmentId(Long financialDepartmentId) {
            financialSecPermissionScopeOutputResponse.setFinancialDepartmentId(financialDepartmentId);
            return this;
        }

        public Builder departmentId(Long departmentId) {
            financialSecPermissionScopeOutputResponse.setDepartmentId(departmentId);
            return this;
        }

        public Builder financialLedgerTypeId(Long financialLedgerTypeId) {
            financialSecPermissionScopeOutputResponse.setFinancialLedgerTypeId(financialLedgerTypeId);
            return this;
        }

        public Builder applicationUserId(Long applicationUserId) {
            financialSecPermissionScopeOutputResponse.setApplicationUserId(applicationUserId);
            return this;
        }

        public Builder allLedgerTypesFlag(Boolean allLedgerTypesFlag) {
            financialSecPermissionScopeOutputResponse.setAllLedgerTypesFlag(allLedgerTypesFlag);
            return this;
        }

        public Builder allFncDepartmentFlag(Boolean allFncDepartmentFlag) {
            financialSecPermissionScopeOutputResponse.setAllFncDepartmentFlag(allFncDepartmentFlag);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialSecPermissionScopeOutputResponse.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialSecPermissionScopeOutputResponse.setDisableDate(disableDate);
            return this;
        }

        public Builder financialUserName(String financialUserName) {
            financialSecPermissionScopeOutputResponse.setFinancialUserName(financialUserName);
            return this;
        }

        public Builder financialGroupCode(String financialGroupCode) {
            financialSecPermissionScopeOutputResponse.setFinancialGroupCode(financialGroupCode);
            return this;
        }

        public Builder financialGroupDescription(String financialGroupDescription) {
            financialSecPermissionScopeOutputResponse.setFinancialGroupDescription(financialGroupDescription);
            return this;
        }

        public Builder departmentCode(String departmentCode) {
            financialSecPermissionScopeOutputResponse.setDepartmentCode(departmentCode);
            return this;
        }

        public Builder departmentName(String departmentName) {
            financialSecPermissionScopeOutputResponse.setDepartmentName(departmentName);
            return this;
        }

        public Builder financialDepartmentCode(String financialDepartmentCode) {
            financialSecPermissionScopeOutputResponse.setFinancialDepartmentCode(financialDepartmentCode);
            return this;
        }

        public Builder financialDepartmentName(String financialDepartmentName) {
            financialSecPermissionScopeOutputResponse.setFinancialDepartmentName(financialDepartmentName);
            return this;
        }

        public Builder ledgerTypeDescription(String ledgerTypeDescription) {
            financialSecPermissionScopeOutputResponse.setLedgerTypeDescription(ledgerTypeDescription);
            return this;
        }

        public FinancialSecPermissionScopeOutputResponse build() {
            return financialSecPermissionScopeOutputResponse;
        }
    }
}
