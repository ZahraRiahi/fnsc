package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_PERMISSION", schema = "fnsc")
public class UserPermission extends AuditModel<Long> {
    private UserPermissionScope userPermissionScopeId;
    private FinancialUser financialUserIdCreator;
    private FinancialActivityType financialTypeActivityId;
    private FinancialDocumentType financialDocumentTypeId;
    private FinancialPeriod financialPeriodId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Boolean allDocumentTypeFlag;
    private Boolean allFinancialPeriodFlag;

    @Override
    @Id
    @SequenceGenerator(schema = "fnsc", name = "user_permission_generator", sequenceName = "sq_user_permission")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_permission_generator")
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_PERMISSION_SCOPE_ID")
    public UserPermissionScope getUserPermissionScopeId() {
        return userPermissionScopeId;
    }

    public void setUserPermissionScopeId(UserPermissionScope userPermissionScopeId) {
        this.userPermissionScopeId = userPermissionScopeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_USER_ID_CREATOR")
    public FinancialUser getFinancialUserIdCreator() {
        return financialUserIdCreator;
    }

    public void setFinancialUserIdCreator(FinancialUser financialUserIdCreator) {
        this.financialUserIdCreator = financialUserIdCreator;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_ACTIVITY_TYPE_ID")
    public FinancialActivityType getFinancialTypeActivityId() {
        return financialTypeActivityId;
    }

    public void setFinancialTypeActivityId(FinancialActivityType financialTypeActivityId) {
        this.financialTypeActivityId = financialTypeActivityId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_DOCUMENT_TYPE_ID")
    public FinancialDocumentType getFinancialDocumentTypeId() {
        return financialDocumentTypeId;
    }

    public void setFinancialDocumentTypeId(FinancialDocumentType financialDocumentTypeId) {
        this.financialDocumentTypeId = financialDocumentTypeId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_PERIOD_ID")
    public FinancialPeriod getFinancialPeriodId() {
        return financialPeriodId;
    }

    public void setFinancialPeriodId(FinancialPeriod financialPeriodId) {
        this.financialPeriodId = financialPeriodId;
    }

    @Column(name = "EFFECTIVE_DATE")
    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "DISABLE_DATE")
    public LocalDateTime getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(LocalDateTime disableDate) {
        this.disableDate = disableDate;
    }

    @Column(name = "ALL_DOCUMENT_TYPE_FLAG")
    public Boolean getAllDocumentTypeFlag() {
        return allDocumentTypeFlag;
    }

    public void setAllDocumentTypeFlag(Boolean allDocumentTypeFlag) {
        this.allDocumentTypeFlag = allDocumentTypeFlag;
    }

    @Column(name = "ALL_FINANCIAL_PRIOD_FLAG")
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
        private UserPermission userPermission;

        private Builder() {
            userPermission = new UserPermission();
        }


        public Builder userPermissionScopeId(UserPermissionScope userPermissionScopeId) {
            userPermission.setUserPermissionScopeId(userPermissionScopeId);
            return this;
        }

        public Builder financialUserIdCreator(FinancialUser financialUserIdCreator) {
            userPermission.setFinancialUserIdCreator(financialUserIdCreator);
            return this;
        }

        public Builder financialTypeActivityId(FinancialActivityType financialTypeActivityId) {
            userPermission.setFinancialTypeActivityId(financialTypeActivityId);
            return this;
        }

        public Builder financialDocumentTypeId(FinancialDocumentType financialDocumentTypeId) {
            userPermission.setFinancialDocumentTypeId(financialDocumentTypeId);
            return this;
        }

        public Builder financialPeriodId(FinancialPeriod financialPeriodId) {
            userPermission.setFinancialPeriodId(financialPeriodId);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            userPermission.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            userPermission.setDisableDate(disableDate);
            return this;
        }

        public Builder allDocumentTypeFlag(Boolean allDocumentTypeFlag) {
            userPermission.setAllDocumentTypeFlag(allDocumentTypeFlag);
            return this;
        }

        public Builder allFinancialPeriodFlag(Boolean allFinancialPeriodFlag) {
            userPermission.setAllFinancialPeriodFlag(allFinancialPeriodFlag);
            return this;
        }

        public UserPermission build() {
            return userPermission;
        }
    }
}
