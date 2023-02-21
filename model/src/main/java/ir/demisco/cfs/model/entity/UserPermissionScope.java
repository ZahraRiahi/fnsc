package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;
import ir.demisco.cloud.basic.model.entity.org.Department;
import ir.demisco.cloud.basic.model.entity.org.Organization;


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
@Table(name = "USER_PERMISSION_SCOPE", schema = "fnsc")
public class UserPermissionScope extends AuditModel<Long> {
    private FinancialUser financialUser;
    private FinancialLedgerType financialLedgerType;
    private FinancialDepartment financialDepartment;
    private Department department;
    private LocalDateTime disableDate;
    private LocalDateTime effectiveDate;
    private Boolean allLedgerTypesFlag;
    private Boolean allFncDepartmentFlag;
    private FinancialGroup financialGroup;
    private Organization organization;

    @Override
    @Id
    @SequenceGenerator(schema = "fnsc", name = "user_permission_scope_generator", sequenceName = "sq_user_permission_scope")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_permission_scope_generator")
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_USER_ID")
    public FinancialUser getFinancialUser() {
        return financialUser;
    }

    public void setFinancialUser(FinancialUser financialUser) {
        this.financialUser = financialUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_LEDGER_TYPE_ID")
    public FinancialLedgerType getFinancialLedgerType() {
        return financialLedgerType;
    }

    public void setFinancialLedgerType(FinancialLedgerType financialLedgerType) {
        this.financialLedgerType = financialLedgerType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_DEPARTMENT_ID")
    public FinancialDepartment getFinancialDepartment() {
        return financialDepartment;
    }

    public void setFinancialDepartment(FinancialDepartment financialDepartment) {
        this.financialDepartment = financialDepartment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Column(name = "DISABLE_DATE")
    public LocalDateTime getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(LocalDateTime disableDate) {
        this.disableDate = disableDate;
    }

    @Column(name = "EFFECTIVE_DATE")
    public LocalDateTime getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDateTime effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "ALL_LEDGER_TYPES_FLAG")
    public Boolean getAllLedgerTypesFlag() {
        return allLedgerTypesFlag;
    }

    public void setAllLedgerTypesFlag(Boolean allLedgerTypesFlag) {
        this.allLedgerTypesFlag = allLedgerTypesFlag;
    }

    @Column(name = "ALL_FNC_DEPARTMENT_FLAG")
    public Boolean getAllFncDepartmentFlag() {
        return allFncDepartmentFlag;
    }

    public void setAllFncDepartmentFlag(Boolean allFncDepartmentFlag) {
        this.allFncDepartmentFlag = allFncDepartmentFlag;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_GROUP_ID")
    public FinancialGroup getFinancialGroup() {
        return financialGroup;
    }

    public void setFinancialGroup(FinancialGroup financialGroup) {
        this.financialGroup = financialGroup;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UserPermissionScope userPermissionScope;

        private Builder() {
            userPermissionScope = new UserPermissionScope();
        }


        public Builder financialUser(FinancialUser financialUser) {
            userPermissionScope.setFinancialUser(financialUser);
            return this;
        }

        public Builder financialLedgerType(FinancialLedgerType financialLedgerType) {
            userPermissionScope.setFinancialLedgerType(financialLedgerType);
            return this;
        }

        public Builder financialDepartment(FinancialDepartment financialDepartment) {
            userPermissionScope.setFinancialDepartment(financialDepartment);
            return this;
        }

        public Builder department(Department department) {
            userPermissionScope.setDepartment(department);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            userPermissionScope.setDisableDate(disableDate);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            userPermissionScope.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder allLedgerTypesFlag(Boolean allLedgerTypesFlag) {
            userPermissionScope.setAllLedgerTypesFlag(allLedgerTypesFlag);
            return this;
        }

        public Builder allFncDepartmentFlag(Boolean allFncDepartmentFlag) {
            userPermissionScope.setAllFncDepartmentFlag(allFncDepartmentFlag);
            return this;
        }

        public Builder financialGroup(FinancialGroup financialGroup) {
            userPermissionScope.setFinancialGroup(financialGroup);
            return this;
        }

        public Builder organization(Organization organization) {
            userPermissionScope.setOrganization(organization);
            return this;
        }

        public UserPermissionScope build() {
            return userPermissionScope;
        }
    }
}
