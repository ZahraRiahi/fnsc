package ir.demisco.cfs.model.dto.request;

public class FinancialUserGroupInputModelRequest {
    private Long organizationId;
    private Long financialGroupId;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getFinancialGroupId() {
        return financialGroupId;
    }

    public void setFinancialGroupId(Long financialGroupId) {
        this.financialGroupId = financialGroupId;
    }
}
