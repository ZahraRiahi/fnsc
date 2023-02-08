package ir.demisco.cfs.model.dto.request;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
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
}
