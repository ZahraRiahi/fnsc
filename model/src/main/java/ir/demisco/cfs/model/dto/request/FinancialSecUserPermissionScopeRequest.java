package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;

public class FinancialSecUserPermissionScopeRequest {
    private Long userPermissionScopeId;

    @NotNull(message = "لطفا شناسه محدوده دسترسی را وارد کنید.")
    public Long getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(Long userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialSecUserPermissionScopeRequest financialSecUserPermissionScopeRequest;

        private Builder() {
            financialSecUserPermissionScopeRequest = new FinancialSecUserPermissionScopeRequest();
        }


        public Builder userPermissionScopeId(Long userPermissionScopeId) {
            financialSecUserPermissionScopeRequest.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public FinancialSecUserPermissionScopeRequest build() {
            return financialSecUserPermissionScopeRequest;
        }
    }
}
