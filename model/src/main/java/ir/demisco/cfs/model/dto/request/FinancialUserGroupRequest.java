package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class FinancialUserGroupRequest {
    private Long groupId;
    private List<Long> financialUserId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;

    @NotNull(message = "لطفا شناسه گروه را وارد کنید.")
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @NotNull(message = "لطفا لیست شناسه های کاربران مالی را وارد کنید.")
    public List<Long> getFinancialUserId() {
        return financialUserId;
    }

    public void setFinancialUserId(List<Long> financialUserId) {
        this.financialUserId = financialUserId;
    }

    @NotNull(message = "لطفا تاریخ شروع تاثیر را وارد کنید.")
    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
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
        private FinancialUserGroupRequest financialUserGroupRequest;

        private Builder() {
            financialUserGroupRequest = new FinancialUserGroupRequest();
        }


        public Builder groupId(Long groupId) {
            financialUserGroupRequest.setGroupId(groupId);
            return this;
        }

        public Builder financialUserId(List<Long> financialUserId) {
            financialUserGroupRequest.setFinancialUserId(financialUserId);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialUserGroupRequest.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialUserGroupRequest.setDisableDate(disableDate);
            return this;
        }

        public FinancialUserGroupRequest build() {
            return financialUserGroupRequest;
        }
    }
}
