package ir.demisco.cfs.model.dto.response;

public class FinancialUserGroupOutputModelResponse {
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
    public static FinancialUserGroupOutputModelResponse.Builder builder() {
        return new FinancialUserGroupOutputModelResponse.Builder();
    }
    public static final class Builder {
        private FinancialUserGroupOutputModelResponse financialUserGroupOutputModelResponse;

        private Builder() {
            financialUserGroupOutputModelResponse = new FinancialUserGroupOutputModelResponse();
        }

        public static Builder financialUserGroupOutputModelResponse() {
            return new Builder();
        }

        public Builder applicationUserId(Long applicationUserId) {
            financialUserGroupOutputModelResponse.setApplicationUserId(applicationUserId);
            return this;
        }

        public Builder financialUserId(Long financialUserId) {
            financialUserGroupOutputModelResponse.setFinancialUserId(financialUserId);
            return this;
        }

        public Builder userName(String userName) {
            financialUserGroupOutputModelResponse.setUserName(userName);
            return this;
        }

        public Builder nickName(String nickName) {
            financialUserGroupOutputModelResponse.setNickName(nickName);
            return this;
        }

        public FinancialUserGroupOutputModelResponse build() {
            return financialUserGroupOutputModelResponse;
        }
    }
}
