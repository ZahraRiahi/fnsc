package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;
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
import java.util.Date;

@Entity
@Table(name = "FINANCIAL_USER_GROUP", schema = "fnsc")
public class FinancialUserGroup extends AuditModel<Long> {
    private FinancialGroup financialGroupId;
    private FinancialUser financialUserId;
    private LocalDateTime effectiveDate;
    private LocalDateTime disableDate;
    private Organization organizationId;

    @Override
    @Id
    @SequenceGenerator(schema = "fnsc", name = "financial_user_group_generator", sequenceName = "sq_financial_user_group")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_user_group_generator")
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_GROUP_ID")
    public FinancialGroup getFinancialGroupId() {
        return financialGroupId;
    }

    public void setFinancialGroupId(FinancialGroup financialGroupId) {
        this.financialGroupId = financialGroupId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_USER_ID")
    public FinancialUser getFinancialUserId() {
        return financialUserId;
    }

    public void setFinancialUserId(FinancialUser financialUserId) {
        this.financialUserId = financialUserId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }
}
