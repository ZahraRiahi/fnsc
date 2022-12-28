package ir.demisco.cfs.model.dto.request;

public class FinancialSecPermissionScopeInputRequest {
    private Long organizationId;
    private Long financialUserId;
    private Long financialGroupId;
    private Long filterMode;
    private Boolean allFinancialLedgersFlg;
    private Boolean allFinancialDepartmentFlg;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public Long getFilterMode() {
        return filterMode;
    }

    public void setFilterMode(Long filterMode) {
        this.filterMode = filterMode;
    }

    public Boolean getAllFinancialLedgersFlg() {
        return allFinancialLedgersFlg;
    }

    public void setAllFinancialLedgersFlg(Boolean allFinancialLedgersFlg) {
        this.allFinancialLedgersFlg = allFinancialLedgersFlg;
    }

    public Boolean getAllFinancialDepartmentFlg() {
        return allFinancialDepartmentFlg;
    }

    public void setAllFinancialDepartmentFlg(Boolean allFinancialDepartmentFlg) {
        this.allFinancialDepartmentFlg = allFinancialDepartmentFlg;
    }
}
