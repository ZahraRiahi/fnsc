package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupRequest;
import ir.demisco.cfs.model.entity.FinancialGroup;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.model.entity.FinancialUserGroup;
import ir.demisco.cfs.service.api.FinancialUserGroupService;
import ir.demisco.cfs.service.repository.FinancialUserGroupRepository;
import ir.demisco.cloud.basic.model.entity.org.Organization;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.core.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialUserGroup implements FinancialUserGroupService {
    private final FinancialUserGroupRepository financialUserGroupRepository;
    private final EntityManager entityManager;
    private final DaoService daoService;

    public DefaultFinancialUserGroup(FinancialUserGroupRepository financialUserGroupRepository, EntityManager entityManager, DaoService daoService) {
        this.financialUserGroupRepository = financialUserGroupRepository;
        this.entityManager = entityManager;
        this.daoService = daoService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean setFinancialUserGroup(FinancialUserGroupInputRequest financialUserGroupInputRequest) {
        Long count = financialUserGroupRepository.findByFinancialUserGroupByIdAndDisableDate(financialUserGroupInputRequest.getUserGroupId(), financialUserGroupInputRequest.getDisableDate());
        if (count != null) {
            throw new RuleException("fin.financialUserGroup.inconsistencyOfDates");
        }
        entityManager.createNativeQuery(" update FNSC.FINANCIAL_USER_GROUP T " +
                        "   set   T.DISABLE_DATE = :disableDate " +
                        "   WHERE T.ID = :financialUserGroupId ")
                .setParameter("disableDate", financialUserGroupInputRequest.getDisableDate())
                .setParameter("financialUserGroupId", financialUserGroupInputRequest.getUserGroupId()).executeUpdate();
        return true;
    }

    @Override
    public Long getFinancialUserGroupByEffectiveDate(Long financialUserId, Long financialGroupId, LocalDateTime effectiveDate, Long organizationId) {
        return financialUserGroupRepository.findFinancialUserGroupByEffectiveDate(financialUserId, financialGroupId, effectiveDate, organizationId);
    }

    @Override
    public Long getFinancialUserGroupByDisableDate(Long financialUserId, Long financialGroupId, LocalDateTime disableDate, Long organizationId) {
        return financialUserGroupRepository.findFinancialUserGroupByDisableDate(financialUserId, financialGroupId, disableDate, organizationId);
    }

    @Override
    public Optional<FinancialUserGroup> getFinancialUserGroupByEffectiveDateAndDisableDate(Long groupId, Long financialUserId, LocalDateTime effectiveDate, LocalDateTime disableDate) {
        return financialUserGroupRepository.findFinancialUserGroupByEffectiveDateAndDisableDate(groupId, financialUserId, effectiveDate, disableDate);
    }

    @Override
    public Boolean saveFinancialUserGroup(FinancialUserGroupRequest financialUserGroupRequest) {
        LocalDateTime effectiveDate = DateUtil.truncate(financialUserGroupRequest.getEffectiveDate());
        LocalDateTime disableDate = DateUtil.truncate(financialUserGroupRequest.getDisableDate());
        List<Long> financialUserId = financialUserGroupRequest.getFinancialUserId().stream().filter(financialUser ->
                !getFinancialUserGroupByEffectiveDateAndDisableDate(financialUserGroupRequest.getGroupId(), financialUser,
                        effectiveDate, disableDate).isPresent()).collect(Collectors.toList());
        if (financialUserId.isEmpty()) {
            throw new RuleException("fin.financialUserGroup.overlapping.dates");

        }
        for (Long userId : financialUserId) {
            FinancialUserGroup financialUserGroup = FinancialUserGroup.builder()
                    .financialGroupId(daoService.findById(FinancialGroup.class, financialUserGroupRequest.getGroupId()))
                    .financialUserId(daoService.findById(FinancialUser.class, userId))
                    .effectiveDate(financialUserGroupRequest.getEffectiveDate())
                    .disableDate(financialUserGroupRequest.getDisableDate() == null ? null : financialUserGroupRequest.getDisableDate())
                    .organizationId(financialUserGroupRequest.getOrganizationId() != null ?
                            daoService.findById(Organization.class, financialUserGroupRequest.getOrganizationId()) : null)
                    .build();
            Long countByFinancialUserGroupByEffectiveDate = getFinancialUserGroupByEffectiveDate(userId, financialUserGroupRequest.getGroupId(), financialUserGroupRequest.getEffectiveDate(), financialUserGroupRequest.getOrganizationId());
            Long countByFinancialUserGroupByDisableDate = getFinancialUserGroupByDisableDate(userId, financialUserGroupRequest.getGroupId(), financialUserGroupRequest.getDisableDate(), financialUserGroupRequest.getOrganizationId());

            if (countByFinancialUserGroupByEffectiveDate > 0) {
                throw new RuleException("fin.financialUserGroup.EffectiveDateHasBeenRegistered");
            }
            if (countByFinancialUserGroupByDisableDate > 0) {
                throw new RuleException("fin.financialUserGroup.DisableDateDateHasBeenRegistered");
            }
            financialUserGroupRepository.save(financialUserGroup);

        }

        return true;
    }

    @Override
    public Long getFinancialUserGroupByFinancialUserId(Long financialUserId) {
        return financialUserGroupRepository.findFinancialUserGroupByFinancialUserId(financialUserId);
    }
}
