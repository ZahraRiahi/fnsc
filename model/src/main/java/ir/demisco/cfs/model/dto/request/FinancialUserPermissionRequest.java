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
    private Long allDocumentTypeFlag;
    private Long allFinancialPeriodFlag;

    @NotNull(message = "لطفا شناسه محدوده دسترسی کاربر راوارد کنید.")
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

    @NotNull(message = "لطفا شناسه نوع فعالیت راوارد کنید.")
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

    @NotNull(message = "لطفا تاریخ شروع  را وارد کنید.")
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

    @NotNull(message = "لطفا وضعیت فلگ دسترسی به همه انواع سند را مشخص کنید.")
    public Long getAllDocumentTypeFlag() {
        return allDocumentTypeFlag;
    }

    public void setAllDocumentTypeFlag(Long allDocumentTypeFlag) {
        this.allDocumentTypeFlag = allDocumentTypeFlag;
    }

    @NotNull(message = "لطفا وضعیت فلگ دسترسی به همه دوره های مالی را مشخص کنید.")
    public Long getAllFinancialPeriodFlag() {
        return allFinancialPeriodFlag;
    }

    public void setAllFinancialPeriodFlag(Long allFinancialPeriodFlag) {
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


        public Builder withUserPermissionScopeId(Long userPermissionScopeId) {
            financialUserPermissionRequest.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public Builder withFinancialUserIdCreator(Long financialUserIdCreator) {
            financialUserPermissionRequest.setFinancialUserIdCreator(financialUserIdCreator);
            return this;
        }

        public Builder withFinancialTypeActivityId(Long financialTypeActivityId) {
            financialUserPermissionRequest.setFinancialTypeActivityId(financialTypeActivityId);
            return this;
        }

        public Builder withFinancialDocumentTypeId(Long financialDocumentTypeId) {
            financialUserPermissionRequest.setFinancialDocumentTypeId(financialDocumentTypeId);
            return this;
        }

        public Builder withFinancialPeriodId(Long financialPeriodId) {
            financialUserPermissionRequest.setFinancialPeriodId(financialPeriodId);
            return this;
        }

        public Builder withEffectiveDate(LocalDateTime effectiveDate) {
            financialUserPermissionRequest.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder withDisableDate(LocalDateTime disableDate) {
            financialUserPermissionRequest.setDisableDate(disableDate);
            return this;
        }

        public Builder withAllDocumentTypeFlag(Long allDocumentTypeFlag) {
            financialUserPermissionRequest.setAllDocumentTypeFlag(allDocumentTypeFlag);
            return this;
        }

        public Builder withAllFinancialPeriodFlag(Long allFinancialPeriodFlag) {
            financialUserPermissionRequest.setAllFinancialPeriodFlag(allFinancialPeriodFlag);
            return this;
        }

        public FinancialUserPermissionRequest build() {
            return financialUserPermissionRequest;
        }
    }
}
