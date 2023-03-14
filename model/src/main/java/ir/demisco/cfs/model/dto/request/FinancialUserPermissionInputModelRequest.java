package ir.demisco.cfs.model.dto.request;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class FinancialUserPermissionInputModelRequest {
    private Long userPermissionScopeId;
    private Long financialUserIdCreator;
    private Long financialActivityTypeId;
    private Long financialDocumentTypeId;
    private Long financialPeriodId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Boolean allDocumentTypeFlag;
    private Boolean allFinancialPeriodFlag;
    private Long userPermissionId;
    private Boolean allUsersCreator;

    @NotNull(message = "لطفا  شناسه محدوده دسترسی کاربر را وارد کنید.")
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
    public Long getFinancialActivityTypeId() {
        return financialActivityTypeId;
    }

    public void setFinancialActivityTypeId(Long financialActivityTypeId) {
        this.financialActivityTypeId = financialActivityTypeId;
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

    @NotNull(message = "لطفا تاریخ شروع را وارد کنید.")
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

    public Long getUserPermissionId() {
        return userPermissionId;
    }

    public void setUserPermissionId(Long userPermissionId) {
        this.userPermissionId = userPermissionId;
    }

    public Boolean getAllUsersCreator() {
        return allUsersCreator;
    }

    public void setAllUsersCreator(Boolean allUsersCreator) {
        this.allUsersCreator = allUsersCreator;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialUserPermissionInputModelRequest financialUserPermissionInputModelRequest;

        private Builder() {
            financialUserPermissionInputModelRequest = new FinancialUserPermissionInputModelRequest();
        }


        public Builder userPermissionScopeId(Long userPermissionScopeId) {
            financialUserPermissionInputModelRequest.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public Builder financialUserIdCreator(Long financialUserIdCreator) {
            financialUserPermissionInputModelRequest.setFinancialUserIdCreator(financialUserIdCreator);
            return this;
        }

        public Builder financialActivityTypeId(Long financialActivityTypeId) {
            financialUserPermissionInputModelRequest.setFinancialActivityTypeId(financialActivityTypeId);
            return this;
        }

        public Builder financialDocumentTypeId(Long financialDocumentTypeId) {
            financialUserPermissionInputModelRequest.setFinancialDocumentTypeId(financialDocumentTypeId);
            return this;
        }

        public Builder financialPeriodId(Long financialPeriodId) {
            financialUserPermissionInputModelRequest.setFinancialPeriodId(financialPeriodId);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialUserPermissionInputModelRequest.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialUserPermissionInputModelRequest.setDisableDate(disableDate);
            return this;
        }

        public Builder allDocumentTypeFlag(Boolean allDocumentTypeFlag) {
            financialUserPermissionInputModelRequest.setAllDocumentTypeFlag(allDocumentTypeFlag);
            return this;
        }

        public Builder allFinancialPeriodFlag(Boolean allFinancialPeriodFlag) {
            financialUserPermissionInputModelRequest.setAllFinancialPeriodFlag(allFinancialPeriodFlag);
            return this;
        }

        public Builder userPermissionId(Long userPermissionId) {
            financialUserPermissionInputModelRequest.setUserPermissionId(userPermissionId);
            return this;
        }

        public Builder allUsersCreator(Boolean allUsersCreator) {
            financialUserPermissionInputModelRequest.setAllUsersCreator(allUsersCreator);
            return this;
        }

        public FinancialUserPermissionInputModelRequest build() {
            return financialUserPermissionInputModelRequest;
        }
    }
}
