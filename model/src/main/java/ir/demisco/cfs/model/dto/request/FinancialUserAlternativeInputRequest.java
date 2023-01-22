package ir.demisco.cfs.model.dto.request;

import java.time.LocalDateTime;
import java.util.List;

public class FinancialUserAlternativeInputRequest {
    private Long organizationId;
    private Long financialUserId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private List<Long> financialUserIdAlternative;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getFinancialUserId() {
        return financialUserId;
    }

    public void setFinancialUserId(Long financialUserId) {
        this.financialUserId = financialUserId;
    }

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

    public List<Long> getFinancialUserIdAlternative() {
        return financialUserIdAlternative;
    }

    public void setFinancialUserIdAlternative(List<Long> financialUserIdAlternative) {
        this.financialUserIdAlternative = financialUserIdAlternative;
    }
}
