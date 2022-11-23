package ir.demisco.cfs.model.dto.request;

public class FinancialAlternativeUsersInputRequest {
    private Long flgAllOrganizations;
    private Long mainFinancialUserId;
    private Long organizationId;

    public Long getFlgAllOrganizations() {
        return flgAllOrganizations;
    }

    public void setFlgAllOrganizations(Long flgAllOrganizations) {
        this.flgAllOrganizations = flgAllOrganizations;
    }

    public Long getMainFinancialUserId() {
        return mainFinancialUserId;
    }

    public void setMainFinancialUserId(Long mainFinancialUserId) {
        this.mainFinancialUserId = mainFinancialUserId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
