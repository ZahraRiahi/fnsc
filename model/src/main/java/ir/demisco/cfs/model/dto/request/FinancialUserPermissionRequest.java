package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class FinancialUserPermissionRequest {
    private Long userPermissionScopeId;
    private Long financialUserIdCreator;
    private Long financialTypeActivityId;
    private Long financialDocumentTypeId;
    private Long financialPeriodId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Boolean allDocumentTypeFlag;
    private Boolean allFinancialPeriodFlag;


    public Long getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(Long userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
    }

    public Long getFinancialUserIdCreator() {
        return financialUserIdCreator;
    }

    public void setFinancialUserIdCreator(Long financialUserIdCreator) {
        this.financialUserIdCreator = financialUserIdCreator;
    }

    @NotNull(message = "fin.financialUserPermission.enterFinancialActivityTypeId")
    public Long getFinancialTypeActivityId() {
        return financialTypeActivityId;
    }

    public void setFinancialTypeActivityId(Long financialTypeActivityId) {
        this.financialTypeActivityId = financialTypeActivityId;
    }

    public Long getFinancialDocumentTypeId() {
        return financialDocumentTypeId;
    }

    public void setFinancialDocumentTypeId(Long financialDocumentTypeId) {
        this.financialDocumentTypeId = financialDocumentTypeId;
    }

    public Long getFinancialPeriodId() {
        return financialPeriodId;
    }

    public void setFinancialPeriodId(Long financialPeriodId) {
        this.financialPeriodId = financialPeriodId;
    }

    @NotNull(message = "fin.financialUserPermission.enterEffectiveDate")
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

    public Boolean getAllDocumentTypeFlag() {
        return allDocumentTypeFlag;
    }

    public void setAllDocumentTypeFlag(Boolean allDocumentTypeFlag) {
        this.allDocumentTypeFlag = allDocumentTypeFlag;
    }

    public Boolean getAllFinancialPeriodFlag() {
        return allFinancialPeriodFlag;
    }

    public void setAllFinancialPeriodFlag(Boolean allFinancialPeriodFlag) {
        this.allFinancialPeriodFlag = allFinancialPeriodFlag;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialUserPermissionRequest financialUserPermissionRequest;

        private Builder() {
            financialUserPermissionRequest = new FinancialUserPermissionRequest();
        }


        public Builder userPermissionScopeId(Long userPermissionScopeId) {
            financialUserPermissionRequest.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public Builder financialUserIdCreator(Long financialUserIdCreator) {
            financialUserPermissionRequest.setFinancialUserIdCreator(financialUserIdCreator);
            return this;
        }

        public Builder financialTypeActivityId(Long financialTypeActivityId) {
            financialUserPermissionRequest.setFinancialTypeActivityId(financialTypeActivityId);
            return this;
        }

        public Builder financialDocumentTypeId(Long financialDocumentTypeId) {
            financialUserPermissionRequest.setFinancialDocumentTypeId(financialDocumentTypeId);
            return this;
        }

        public Builder financialPeriodId(Long financialPeriodId) {
            financialUserPermissionRequest.setFinancialPeriodId(financialPeriodId);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialUserPermissionRequest.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialUserPermissionRequest.setDisableDate(disableDate);
            return this;
        }

        public Builder allDocumentTypeFlag(Boolean allDocumentTypeFlag) {
            financialUserPermissionRequest.setAllDocumentTypeFlag(allDocumentTypeFlag);
            return this;
        }

        public Builder allFinancialPeriodFlag(Boolean allFinancialPeriodFlag) {
            financialUserPermissionRequest.setAllFinancialPeriodFlag(allFinancialPeriodFlag);
            return this;
        }

        public FinancialUserPermissionRequest build() {
            return financialUserPermissionRequest;
        }
    }
}
