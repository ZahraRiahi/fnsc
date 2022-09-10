package ir.demisco.cfs.model.dto.response;

public class FinancialSecurityUserResponse {
    private Long applicationUserId;
    private Long financialUserId;
    private String userName;
    private String nickName;

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public Long getFinancialUserId() {
        return financialUserId;
    }

    public void setFinancialUserId(Long financialUserId) {
        this.financialUserId = financialUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static FinancialSecurityUserResponse.Builder builder() {
        return new FinancialSecurityUserResponse.Builder();
    }

    public static final class Builder {
        private FinancialSecurityUserResponse financialSecurityUserResponse;

        private Builder() {
            financialSecurityUserResponse = new FinancialSecurityUserResponse();
        }

        public static Builder financialSecurityUserResponse() {
            return new Builder();
        }

        public Builder applicationUserId(Long applicationUserId) {
            financialSecurityUserResponse.setApplicationUserId(applicationUserId);
            return this;
        }

        public Builder financialUserId(Long financialUserId) {
            financialSecurityUserResponse.setFinancialUserId(financialUserId);
            return this;
        }

        public Builder userName(String userName) {
            financialSecurityUserResponse.setUserName(userName);
            return this;
        }

        public Builder nickName(String nickName) {
            financialSecurityUserResponse.setNickName(nickName);
            return this;
        }

        public FinancialSecurityUserResponse build() {
            return financialSecurityUserResponse;
        }
    }
}
