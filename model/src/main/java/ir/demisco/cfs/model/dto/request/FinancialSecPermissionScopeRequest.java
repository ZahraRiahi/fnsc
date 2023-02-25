package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class FinancialSecPermissionScopeRequest {
    private List<Long> financialUserId;
    private Long financialLedgerTypeId;
    private Long financialDepartmentId;
    private Long departmentId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Boolean allLedgerTypesFlag;
    private Boolean allFncDepartmentFlag;
    private List<Long> financialGroupId;
    private Long organizationId;

    public List<Long> getFinancialUserId() {
        return financialUserId;
    }

    public void setFinancialUserId(List<Long> financialUserId) {
        this.financialUserId = financialUserId;
    }

    public Long getFinancialLedgerTypeId() {
        return financialLedgerTypeId;
    }

    public void setFinancialLedgerTypeId(Long financialLedgerTypeId) {
        this.financialLedgerTypeId = financialLedgerTypeId;
    }

    public Long getFinancialDepartmentId() {
        return financialDepartmentId;
    }

    public void setFinancialDepartmentId(Long financialDepartmentId) {
        this.financialDepartmentId = financialDepartmentId;
    }

    @NotNull(message = "لطفا شناسه شعبه  را وارد کنید.")
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @NotNull(message = "لطفا تاریخ شروع را وارد کنید.")
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

    public List<Long> getFinancialGroupId() {
        return financialGroupId;
    }

    public void setFinancialGroupId(List<Long> financialGroupId) {
        this.financialGroupId = financialGroupId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialSecPermissionScopeRequest financialSecPermissionScopeRequest;

        private Builder() {
            financialSecPermissionScopeRequest = new FinancialSecPermissionScopeRequest();
        }


        public Builder financialUserId(List<Long> financialUserId) {
            financialSecPermissionScopeRequest.setFinancialUserId(financialUserId);
            return this;
        }

        public Builder financialLedgerTypeId(Long financialLedgerTypeId) {
            financialSecPermissionScopeRequest.setFinancialLedgerTypeId(financialLedgerTypeId);
            return this;
        }

        public Builder financialDepartmentId(Long financialDepartmentId) {
            financialSecPermissionScopeRequest.setFinancialDepartmentId(financialDepartmentId);
            return this;
        }

        public Builder departmentId(Long departmentId) {
            financialSecPermissionScopeRequest.setDepartmentId(departmentId);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialSecPermissionScopeRequest.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialSecPermissionScopeRequest.setDisableDate(disableDate);
            return this;
        }

        public Builder allLedgerTypesFlag(Boolean allLedgerTypesFlag) {
            financialSecPermissionScopeRequest.setAllLedgerTypesFlag(allLedgerTypesFlag);
            return this;
        }

        public Builder allFncDepartmentFlag(Boolean allFncDepartmentFlag) {
            financialSecPermissionScopeRequest.setAllFncDepartmentFlag(allFncDepartmentFlag);
            return this;
        }

        public Builder financialGroupId(List<Long> financialGroupId) {
            financialSecPermissionScopeRequest.setFinancialGroupId(financialGroupId);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            financialSecPermissionScopeRequest.setOrganizationId(organizationId);
            return this;
        }

        public FinancialSecPermissionScopeRequest build() {
            return financialSecPermissionScopeRequest;
        }
    }

}
