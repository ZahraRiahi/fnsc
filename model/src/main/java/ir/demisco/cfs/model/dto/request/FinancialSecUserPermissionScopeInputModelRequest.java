package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class FinancialSecUserPermissionScopeInputModelRequest {
    private List<Long> userPermissionId;

    public List<Long> getUserPermissionId() {
        return userPermissionId;
    }

    public void setUserPermissionId(List<Long> userPermissionId) {
        this.userPermissionId = userPermissionId;
    }
}
