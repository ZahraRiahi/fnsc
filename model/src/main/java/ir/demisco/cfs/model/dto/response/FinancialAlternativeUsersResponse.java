package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialAlternativeUsersResponse {
    private Long financialAlternativeId;
    private Long mainUserId;
    private String mainUserName;
    private String mainNickName;
    private Long financialUserIdAlternative;
    private String alternativeUserName;
    private String alternativeNickName;
    private Long organizationId;
    private String organizationName;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;

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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialAlternativeUsersResponse financialAlternativeUsersResponse;

        private Builder() {
            financialAlternativeUsersResponse = new FinancialAlternativeUsersResponse();
        }


        public Builder financialAlternativeId(Long financialAlternativeId) {
            financialAlternativeUsersResponse.setFinancialAlternativeId(financialAlternativeId);
            return this;
        }

        public Builder mainUserId(Long mainUserId) {
            financialAlternativeUsersResponse.setMainUserId(mainUserId);
            return this;
        }

        public Builder mainUserName(String mainUserName) {
            financialAlternativeUsersResponse.setMainUserName(mainUserName);
            return this;
        }

        public Builder mainNickName(String mainNickName) {
            financialAlternativeUsersResponse.setMainNickName(mainNickName);
            return this;
        }

        public Builder financialUserIdAlternative(Long financialUserIdAlternative) {
            financialAlternativeUsersResponse.setFinancialUserIdAlternative(financialUserIdAlternative);
            return this;
        }

        public Builder alternativeUserName(String alternativeUserName) {
            financialAlternativeUsersResponse.setAlternativeUserName(alternativeUserName);
            return this;
        }

        public Builder alternativeNickName(String alternativeNickName) {
            financialAlternativeUsersResponse.setAlternativeNickName(alternativeNickName);
            return this;
        }

        public Builder organizationId(Long organizationId) {
            financialAlternativeUsersResponse.setOrganizationId(organizationId);
            return this;
        }

        public Builder organizationName(String organizationName) {
            financialAlternativeUsersResponse.setOrganizationName(organizationName);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialAlternativeUsersResponse.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialAlternativeUsersResponse.setDisableDate(disableDate);
            return this;
        }

        public FinancialAlternativeUsersResponse build() {
            return financialAlternativeUsersResponse;
        }
    }
}
