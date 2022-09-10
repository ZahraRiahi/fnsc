package ir.demisco.cfs.model.dto.response;

import java.util.Map;

public class ApplicationUsersOutputResponse {
    private Long applicationUserId;
    private String userName;
    private String nickName;
    Map<String, Object> paramMap;

    public Long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(Long applicationUserId) {
        this.applicationUserId = applicationUserId;
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

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public static ApplicationUsersOutputResponse.Builder builder() {
        return new ApplicationUsersOutputResponse.Builder();
    }

    public static final class Builder {
        private ApplicationUsersOutputResponse applicationUsersOutputResponse;

        private Builder() {
            applicationUsersOutputResponse = new ApplicationUsersOutputResponse();
        }

        public static Builder applicationUsersOutputResponse() {
            return new Builder();
        }

        public Builder applicationUserId(Long applicationUserId) {
            applicationUsersOutputResponse.setApplicationUserId(applicationUserId);
            return this;
        }

        public Builder userName(String userName) {
            applicationUsersOutputResponse.setUserName(userName);
            return this;
        }

        public Builder nickName(String nickName) {
            applicationUsersOutputResponse.setNickName(nickName);
            return this;
        }

        public ApplicationUsersOutputResponse build() {
            return applicationUsersOutputResponse;
        }
    }
}
