package ir.demisco.cfs.model.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class PermissionScopeInputModelRequest {
    private List<Long> permissionScopeIdList;
    private LocalDateTime disableDate;

    public List<Long> getPermissionScopeIdList() {
        return permissionScopeIdList;
    }

    public void setPermissionScopeIdList(List<Long> permissionScopeIdList) {
        this.permissionScopeIdList = permissionScopeIdList;
    }

    public LocalDateTime getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(LocalDateTime disableDate) {
        this.disableDate = disableDate;
    }
}
