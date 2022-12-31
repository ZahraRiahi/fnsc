package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.service.api.FinancialUserGroupService;
import ir.demisco.cfs.service.repository.FinancialUserGroupRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class DefaultFinancialUserGroup implements FinancialUserGroupService {
    private final FinancialUserGroupRepository financialUserGroupRepository;
    private final EntityManager entityManager;

    public DefaultFinancialUserGroup(FinancialUserGroupRepository financialUserGroupRepository, EntityManager entityManager) {
        this.financialUserGroupRepository = financialUserGroupRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean setFinancialUserGroup(FinancialUserGroupInputRequest financialUserGroupInputRequest) {
        Long count = financialUserGroupRepository.findByFinancialUserGroupByIdAndDisableDate(financialUserGroupInputRequest.getUserGroupId(), financialUserGroupInputRequest.getDisableDate());
        if (count != null) {
            throw new RuleException("تاریخ پایان قبلا پر شده و یا با تاریخ شروع هماهنگی ندارد");
        }
        entityManager.createNativeQuery(" update FNSC.FINANCIAL_USER_GROUP T " +
                "   set   T.DISABLE_DATE = :disableDate " +
                "   WHERE T.ID = :financialUserGroupId ")
                .setParameter("disableDate", financialUserGroupInputRequest.getDisableDate())
                .setParameter("financialUserGroupId", financialUserGroupInputRequest.getUserGroupId()).executeUpdate();
        return true;
    }
}
