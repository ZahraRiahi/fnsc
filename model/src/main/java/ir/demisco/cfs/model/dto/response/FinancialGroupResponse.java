package ir.demisco.cfs.model.dto.response;

public class FinancialGroupResponse {
    private Long organizationId;
    private Long financialGroupId;
    private String financialGroupCode;
    private String financialGroupDescription;
    private String organizationName;

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

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialGroupResponse financialGroupResponse;

        private Builder() {
            financialGroupResponse = new FinancialGroupResponse();
        }


        public Builder organizationId(Long organizationId) {
            financialGroupResponse.setOrganizationId(organizationId);
            return this;
        }

        public Builder financialGroupId(Long financialGroupId) {
            financialGroupResponse.setFinancialGroupId(financialGroupId);
            return this;
        }

        public Builder financialGroupCode(String financialGroupCode) {
            financialGroupResponse.setFinancialGroupCode(financialGroupCode);
            return this;
        }

        public Builder financialGroupDescription(String financialGroupDescription) {
            financialGroupResponse.setFinancialGroupDescription(financialGroupDescription);
            return this;
        }

        public Builder organizationName(String organizationName) {
            financialGroupResponse.setOrganizationName(organizationName);
            return this;
        }

        public FinancialGroupResponse build() {
            return financialGroupResponse;
        }
    }
}
