package ir.demisco.cfs.model.entity;

import ir.demisco.cloud.basic.model.entity.domain.AuditModel;
import ir.demisco.cloud.basic.model.entity.sec.User;

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
@Table(name = "FINANCIAL_USER", schema = "fnsc")
public class FinancialUser extends AuditModel<Long> {
    private User applicationUser;

    @Override
    @Id
    @SequenceGenerator(schema = "fnsc", name = "financial_user_generator", sequenceName = "sq_financial_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_user_generator")
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_USER_ID")
    public User getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(User applicationUser) {
        this.applicationUser = applicationUser;
    }
}
