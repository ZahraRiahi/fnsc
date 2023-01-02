package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class FinancialSecPermissionScopeInputModelRequest {
    private List<Long> userPermissionScopeId;

    public List<Long> getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(List<Long> userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
    }
}
