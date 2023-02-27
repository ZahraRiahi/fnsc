package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;

public class FinancialGroupRequest {
    private Long financialGroupId;
    private Long organizationId;
    private String financialGroupCode;
    private String financialGroupDescription;

    public Long getFinancialGroupId() {
        return financialGroupId;
    }

    public void setFinancialGroupId(Long financialGroupId) {
        this.financialGroupId = financialGroupId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @NotNull(message = "لطفا کد گروه را وارد کنید.")
    public String getFinancialGroupCode() {
        return financialGroupCode;
    }

    public void setFinancialGroupCode(String financialGroupCode) {
        this.financialGroupCode = financialGroupCode;
    }

    @NotNull(message = "لطفا شرح گروه را وارد کنید.")
    public String getFinancialGroupDescription() {
        return financialGroupDescription;
    }

    public void setFinancialGroupDescription(String financialGroupDescription) {
        this.financialGroupDescription = financialGroupDescription;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialGroupRequest financialGroupRequest;

        private Builder() {
            financialGroupRequest = new FinancialGroupRequest();
        }


        public Builder financialGroupId(Long financialGroupId) {
            financialGroupRequest.setFinancialGroupId(financialGroupId);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            financialGroupRequest.setOrganizationId(organizationId);
            return this;
        }

        public Builder financialGroupCode(String financialGroupCode) {
            financialGroupRequest.setFinancialGroupCode(financialGroupCode);
            return this;
        }

        public Builder financialGroupDescription(String financialGroupDescription) {
            financialGroupRequest.setFinancialGroupDescription(financialGroupDescription);
            return this;
        }

        public FinancialGroupRequest build() {
            return financialGroupRequest;
        }
    }

}
