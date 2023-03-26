package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserPermissionRequest {
    private Long userPermissionId;
    private LocalDateTime disableDate;

    @NotNull(message = "fin.userPermission.enterUserPermissionId")
    public Long getUserPermissionId() {
        return userPermissionId;
    }

    public void setUserPermissionId(Long userPermissionId) {
        this.userPermissionId = userPermissionId;
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
        private UserPermissionRequest userPermissionRequest;

        private Builder() {
            userPermissionRequest = new UserPermissionRequest();
        }


        public Builder userPermissionId(Long userPermissionId) {
            userPermissionRequest.setUserPermissionId(userPermissionId);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            userPermissionRequest.setDisableDate(disableDate);
            return this;
        }

        public UserPermissionRequest build() {
            return userPermissionRequest;
        }
    }

}
