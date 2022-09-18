package ir.demisco.cfs.model.dto.response;

import java.util.Date;

public class FinancialAlternativeUsersOutputResponse {
    private Long financialAlternativeId;
    private Long mainUserId;
    private String mainUserName;
    private String mainNickName;
    private Long financialUserIdAlternative;
    private String alternativeUserName;
    private String alternativeNickName;
    private Long organizationId;
    private String organizationName;
    private Date effectiveDate;
    private Date disableDate;

    public Long getFinancialAlternativeId() {
        return financialAlternativeId;
    }

    public void setFinancialAlternativeId(Long financialAlternativeId) {
        this.financialAlternativeId = financialAlternativeId;
    }

    public Long getMainUserId() {
        return mainUserId;
    }

    public void setMainUserId(Long mainUserId) {
        this.mainUserId = mainUserId;
    }

    public String getMainUserName() {
        return mainUserName;
    }

    public void setMainUserName(String mainUserName) {
        this.mainUserName = mainUserName;
    }

    public String getMainNickName() {
        return mainNickName;
    }

    public void setMainNickName(String mainNickName) {
        this.mainNickName = mainNickName;
    }

    public Long getFinancialUserIdAlternative() {
        return financialUserIdAlternative;
    }

    public void setFinancialUserIdAlternative(Long financialUserIdAlternative) {
        this.financialUserIdAlternative = financialUserIdAlternative;
    }

    public String getAlternativeUserName() {
        return alternativeUserName;
    }

    public void setAlternativeUserName(String alternativeUserName) {
        this.alternativeUserName = alternativeUserName;
    }

    public String getAlternativeNickName() {
        return alternativeNickName;
    }

    public void setAlternativeNickName(String alternativeNickName) {
        this.alternativeNickName = alternativeNickName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }
    public static FinancialAlternativeUsersOutputResponse.Builder builder() {
        return new FinancialAlternativeUsersOutputResponse.Builder();
    }
    public static final class Builder {
        private FinancialAlternativeUsersOutputResponse financialAlternativeUsersOutputResponse;

        private Builder() {
            financialAlternativeUsersOutputResponse = new FinancialAlternativeUsersOutputResponse();
        }

        public static Builder financialAlternativeUsersOutputResponse() {
            return new Builder();
        }

        public Builder financialAlternativeId(Long financialAlternativeId) {
            financialAlternativeUsersOutputResponse.setFinancialAlternativeId(financialAlternativeId);
            return this;
        }

        public Builder mainUserId(Long mainUserId) {
            financialAlternativeUsersOutputResponse.setMainUserId(mainUserId);
            return this;
        }

        public Builder mainUserName(String mainUserName) {
            financialAlternativeUsersOutputResponse.setMainUserName(mainUserName);
            return this;
        }

        public Builder mainNickName(String mainNickName) {
            financialAlternativeUsersOutputResponse.setMainNickName(mainNickName);
            return this;
        }

        public Builder financialUserIdAlternative(Long financialUserIdAlternative) {
            financialAlternativeUsersOutputResponse.setFinancialUserIdAlternative(financialUserIdAlternative);
            return this;
        }

        public Builder alternativeUserName(String alternativeUserName) {
            financialAlternativeUsersOutputResponse.setAlternativeUserName(alternativeUserName);
            return this;
        }

        public Builder alternativeNickName(String alternativeNickName) {
            financialAlternativeUsersOutputResponse.setAlternativeNickName(alternativeNickName);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            financialAlternativeUsersOutputResponse.setOrganizationId(organizationId);
            return this;
        }

        public Builder organizationName(String organizationName) {
            financialAlternativeUsersOutputResponse.setOrganizationName(organizationName);
            return this;
        }

        public Builder effectiveDate(Date effectiveDate) {
            financialAlternativeUsersOutputResponse.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(Date disableDate) {
            financialAlternativeUsersOutputResponse.setDisableDate(disableDate);
            return this;
        }

        public FinancialAlternativeUsersOutputResponse build() {
            return financialAlternativeUsersOutputResponse;
        }
    }
}
