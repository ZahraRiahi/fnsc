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

@Entity
@Table(name = "FINANCIAL_USER_ALTERNATIVE", schema = "fnsc")
public class FinancialUserAlternative extends AuditModel<Long> {
    private FinancialUser financialUser;
    private FinancialUser alternative;
    private LocalDateTime disableDate;
    private LocalDateTime effectiveDate;
    private Organization organization;

    @Override
    @Id
    @SequenceGenerator(schema = "fnsc", name = "financial_user_alternative_generator", sequenceName = "sq_financial_user_alternative")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_user_alternative_generator")
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
    @JoinColumn(name = "FINANCIAL_USER_ID_ALTERNATE")
    public FinancialUser getAlternative() {
        return alternative;
    }

    public void setAlternative(FinancialUser alternative) {
        this.alternative = alternative;
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
        private FinancialUserAlternative financialUserAlternative;

        private Builder() {
            financialUserAlternative = new FinancialUserAlternative();
        }


        public Builder financialUser(FinancialUser financialUser) {
            financialUserAlternative.setFinancialUser(financialUser);
            return this;
        }

        public Builder alternative(FinancialUser alternative) {
            financialUserAlternative.setAlternative(alternative);
            return this;
        }

        public Builder disableDate(LocalDateTime disableDate) {
            financialUserAlternative.setDisableDate(disableDate);
            return this;
        }

        public Builder effectiveDate(LocalDateTime effectiveDate) {
            financialUserAlternative.setEffectiveDate(effectiveDate);
            return this;
        }

        public Builder organization(Organization organization) {
            financialUserAlternative.setOrganization(organization);
            return this;
        }

        public FinancialUserAlternative build() {
            return financialUserAlternative;
        }
    }
}
