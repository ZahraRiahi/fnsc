package ir.demisco.cfs.model.dto.request;

import java.time.LocalDateTime;

public class FinancialUserGroupInputRequest {
    private Long userGroupId;
    private LocalDateTime disableDate;

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public LocalDateTime getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(LocalDateTime disableDate) {
        this.disableDate = disableDate;
    }
}
