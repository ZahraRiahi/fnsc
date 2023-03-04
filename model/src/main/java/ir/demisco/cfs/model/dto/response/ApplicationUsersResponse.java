package ir.demisco.cfs.model.dto.response;

public class ApplicationUsersResponse {
    private Long applicationUserId;
    private String userName;
    private String nickName;

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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ApplicationUsersResponse applicationUsersResponse;

        private Builder() {
            applicationUsersResponse = new ApplicationUsersResponse();
        }


        public Builder applicationUserId(Long applicationUserId) {
            applicationUsersResponse.setApplicationUserId(applicationUserId);
            return this;
        }

        public Builder userName(String userName) {
            applicationUsersResponse.setUserName(userName);
            return this;
        }

        public Builder nickName(String nickName) {
            applicationUsersResponse.setNickName(nickName);
            return this;
        }

        public ApplicationUsersResponse build() {
            return applicationUsersResponse;
        }
    }
}
