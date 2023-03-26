package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class FinancialUserGroupRequest {
    private Long groupId;
    private List<Long> financialUserId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Long organizationId;

    @NotNull(message = "fin.financialUserGroup.enterFinancialUserGroup")
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @NotNull(message = "fin.financialUserGroup.enterFinancialUserId")
    public List<Long> getFinancialUserId() {
        return financialUserId;
    }

    public void setFinancialUserId(List<Long> financialUserId) {
        this.financialUserId = financialUserId;
    }

    @NotNull(message = "fin.financialUserGroup.enterEffectiveDate")
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

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

        public Builder organizationId(Long organizationId) {
            financialUserGroupRequest.setOrganizationId(organizationId);
            return this;
        }

        public FinancialUserGroupRequest build() {
            return financialUserGroupRequest;
        }
    }
}
