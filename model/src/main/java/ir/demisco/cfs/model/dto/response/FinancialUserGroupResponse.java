package ir.demisco.cfs.model.dto.response;

import java.time.LocalDateTime;

public class FinancialUserGroupResponse {
    private Long applicationUserId;
    private Long financialUserGroupId;
    private Long financialUserId;
    private String userName;
    private String nickName;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public Long getFinancialUserGroupId() {
        return financialUserGroupId;
    }

    public void setFinancialUserGroupId(Long financialUserGroupId) {
        this.financialUserGroupId = financialUserGroupId;
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
        private FinancialUserGroupResponse financialUserGroupResponse;

        private Builder() {
            financialUserGroupResponse = new FinancialUserGroupResponse();
        }


        public Builder applicationUserId(Long applicationUserId) {
            financialUserGroupResponse.setApplicationUserId(applicationUserId);
            return this;
        }

        public Builder financialUserGroupId(Long financialUserGroupId) {
            financialUserGroupResponse.setFinancialUserGroupId(financialUserGroupId);
            return this;
        }

        public Builder financialUserId(Long financialUserId) {
            financialUserGroupResponse.setFinancialUserId(financialUserId);
            return this;
        }

        public Builder userName(String userName) {
            financialUserGroupResponse.setUserName(userName);
            return this;
        }

        public Builder nickName(String nickName) {
            financialUserGroupResponse.setNickName(nickName);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialUserGroupResponse.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialUserGroupResponse.setDisableDate(disableDate);
            return this;
        }

        public FinancialUserGroupResponse build() {
            return financialUserGroupResponse;
        }
    }
}
