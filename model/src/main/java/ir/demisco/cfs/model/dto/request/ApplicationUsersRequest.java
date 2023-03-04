package ir.demisco.cfs.model.dto.request;

public class ApplicationUsersRequest {
    private String nickName;
    private String userName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ApplicationUsersRequest applicationUsersRequest;

        private Builder() {
            applicationUsersRequest = new ApplicationUsersRequest();
        }


        public Builder nickName(String nickName) {
            applicationUsersRequest.setNickName(nickName);
            return this;
        }

        public Builder userName(String userName) {
            applicationUsersRequest.setUserName(userName);
            return this;
        }

        public ApplicationUsersRequest build() {
            return applicationUsersRequest;
        }
    }
}
