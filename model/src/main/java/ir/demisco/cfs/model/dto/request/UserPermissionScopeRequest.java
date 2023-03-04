package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class UserPermissionScopeRequest {
    private Long userPermissionScopeId;
    private LocalDateTime disableDate;

    @NotNull(message = "لطفا شناسه محدوه دسترسی را وارد کنید.")
    public Long getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(Long userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
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
        private UserPermissionScopeRequest userPermissionScopeRequest;

        private Builder() {
            userPermissionScopeRequest = new UserPermissionScopeRequest();
        }


        public Builder userPermissionScopeId(Long userPermissionScopeId) {
            userPermissionScopeRequest.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            userPermissionScopeRequest.setDisableDate(disableDate);
            return this;
        }

        public UserPermissionScopeRequest build() {
            return userPermissionScopeRequest;
        }
    }

}
