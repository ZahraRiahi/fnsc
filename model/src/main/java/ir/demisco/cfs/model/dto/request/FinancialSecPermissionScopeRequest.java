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

    @NotNull(message = "لطفا وضعیت فلگ دسترسی به همه انواع سند را مشخص کنید.")
    public Boolean getAllLedgerTypesFlag() {
        return allLedgerTypesFlag;
    }

    public void setAllLedgerTypesFlag(Boolean allLedgerTypesFlag) {
        this.allLedgerTypesFlag = allLedgerTypesFlag;
    }

    @NotNull(message = "لطفا وضعیت فلگ دسترسی به همه دوره های مالی  را مشخص کنید.")
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

    @NotNull(message = "لطفا شناسه سازمان را وارد کنید.")
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


        public Builder withFinancialUserId(List<Long> financialUserId) {
            financialSecPermissionScopeRequest.setFinancialUserId(financialUserId);
            return this;
        }

        public Builder withFinancialLedgerTypeId(Long financialLedgerTypeId) {
            financialSecPermissionScopeRequest.setFinancialLedgerTypeId(financialLedgerTypeId);
            return this;
        }

        public Builder withFinancialDepartmentId(Long financialDepartmentId) {
            financialSecPermissionScopeRequest.setFinancialDepartmentId(financialDepartmentId);
            return this;
        }

        public Builder withDepartmentId(Long departmentId) {
            financialSecPermissionScopeRequest.setDepartmentId(departmentId);
            return this;
        }

        public Builder withEffectiveDate(LocalDateTime effectiveDate) {
            financialSecPermissionScopeRequest.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder withDisableDate(LocalDateTime disableDate) {
            financialSecPermissionScopeRequest.setDisableDate(disableDate);
            return this;
        }

        public Builder withAllLedgerTypesFlag(Boolean allLedgerTypesFlag) {
            financialSecPermissionScopeRequest.setAllLedgerTypesFlag(allLedgerTypesFlag);
            return this;
        }

        public Builder withAllFncDepartmentFlag(Boolean allFncDepartmentFlag) {
            financialSecPermissionScopeRequest.setAllFncDepartmentFlag(allFncDepartmentFlag);
            return this;
        }

        public Builder withFinancialGroupId(List<Long> financialGroupId) {
            financialSecPermissionScopeRequest.setFinancialGroupId(financialGroupId);
            return this;
        }

        public Builder withOrganizationId(Long organizationId) {
            financialSecPermissionScopeRequest.setOrganizationId(organizationId);
            return this;
        }

        public FinancialSecPermissionScopeRequest build() {
            return financialSecPermissionScopeRequest;
        }
    }
}
