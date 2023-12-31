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

@Entity
@Table(name = "FINANCIAL_GROUP", schema = "fnsc")
public class FinancialGroup extends AuditModel<Long> {
    private String code;
    private String description;
    private Organization organization;
    private FinancialGroup parentFinancialGroup;

    @Override
    @Id
    @SequenceGenerator(schema = "fnsc", name = "financial_group_generator", sequenceName = "sq_financial_groups")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_group_generator")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORGANIZATION_ID")
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FINANCIAL_GROUP_ID_PARRENT")
    public FinancialGroup getParentFinancialGroup() {
        return parentFinancialGroup;
    }

    public void setParentFinancialGroup(FinancialGroup parentFinancialGroup) {
        this.parentFinancialGroup = parentFinancialGroup;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FinancialGroup financialGroup;

        private Builder() {
            financialGroup = new FinancialGroup();
        }


        public Builder code(String code) {
            financialGroup.setCode(code);
            return this;
        }

        public Builder description(String description) {
            financialGroup.setDescription(description);
            return this;
        }

        public Builder organization(Organization organization) {
            financialGroup.setOrganization(organization);
            return this;
        }

        public Builder parentFinancialGroup(FinancialGroup parentFinancialGroup) {
            financialGroup.setParentFinancialGroup(parentFinancialGroup);
            return this;
        }

        public FinancialGroup build() {
            return financialGroup;
        }
    }
}
