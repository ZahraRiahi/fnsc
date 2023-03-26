package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class FinancialUserAlternativeRequest {
    private List<Long> applicationUserId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private List<Long> organizationId;
    private Boolean flagAllOrganization;
    private Long mainFinancialUserId;

    @NotNull(message = "fin.financialUserAlternative.enterApplicationUserId")
    public List<Long> getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(List<Long> applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    @NotNull(message = "fin.financialUserAlternative.enterEffectiveDate")
    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public List<Long> getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(List<Long> organizationId) {
        this.organizationId = organizationId;
    }

    @NotNull(message = "fin.financialUserAlternative.enterFlagAllOrganization")
    public Boolean getFlagAllOrganization() {
        return flagAllOrganization;
    }

    public void setFlagAllOrganization(Boolean flagAllOrganization) {
        this.flagAllOrganization = flagAllOrganization;
    }

    @NotNull(message = "fin.financialUserAlternative.enterMainFinancialUserId")
    public Long getMainFinancialUserId() {
        return mainFinancialUserId;
    }

    public void setMainFinancialUserId(Long mainFinancialUserId) {
        this.mainFinancialUserId = mainFinancialUserId;
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
        private FinancialUserAlternativeRequest financialUserAlternativeRequest;

        private Builder() {
            financialUserAlternativeRequest = new FinancialUserAlternativeRequest();
        }


        public Builder applicationUserId(List<Long> applicationUserId) {
            financialUserAlternativeRequest.setApplicationUserId(applicationUserId);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialUserAlternativeRequest.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialUserAlternativeRequest.setDisableDate(disableDate);
            return this;
        }

        public Builder organizationId(List<Long> organizationId) {
            financialUserAlternativeRequest.setOrganizationId(organizationId);
            return this;
        }

        public Builder flagAllOrganization(Boolean flagAllOrganization) {
            financialUserAlternativeRequest.setFlagAllOrganization(flagAllOrganization);
            return this;
        }

        public Builder mainFinancialUserId(Long mainFinancialUserId) {
            financialUserAlternativeRequest.setMainFinancialUserId(mainFinancialUserId);
            return this;
        }

        public FinancialUserAlternativeRequest build() {
            return financialUserAlternativeRequest;
        }
    }
}


