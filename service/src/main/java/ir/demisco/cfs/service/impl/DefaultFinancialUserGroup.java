package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupRequest;
import ir.demisco.cfs.model.dto.response.FinancialUserGroupOutputModelResponse;
import ir.demisco.cfs.model.entity.FinancialGroup;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.model.entity.FinancialUserGroup;
import ir.demisco.cfs.service.api.FinancialUserGroupService;
import ir.demisco.cfs.service.repository.FinancialUserGroupRepository;
import ir.demisco.cloud.basic.model.entity.org.Organization;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.model.security.JwtSecurityPayloadKeys;
import ir.demisco.cloud.core.security.util.SecurityHelper;
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
            throw new RuleException("تاریخ پایان قبلا پر شده و یا با تاریخ شروع هماهنگی ندارد");
        }
        entityManager.createNativeQuery(" update FNSC.FINANCIAL_USER_GROUP T " +
                        "   set   T.DISABLE_DATE = :disableDate " +
                        "   WHERE T.ID = :financialUserGroupId ")
                .setParameter("disableDate", financialUserGroupInputRequest.getDisableDate())
                .setParameter("financialUserGroupId", financialUserGroupInputRequest.getUserGroupId()).executeUpdate();
        return true;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<FinancialUserGroupOutputModelResponse> getFinancialUserGroup(FinancialUserGroupInputModelRequest financialUserGroupInputModelRequest) {
        List<Object[]> financialUserGroupList = financialUserGroupRepository.getFinancialUserGroupByOrganizationId(SecurityHelper.getCurrentUser().getOrganizationId()
                , financialUserGroupInputModelRequest.getFinancialGroupId());
        return financialUserGroupList.stream().map(objects -> FinancialUserGroupOutputModelResponse.builder().applicationUserId(objects[0] == null ? null : Long.parseLong(objects[0].toString()))
                .financialUserId(objects[1] == null ? null : Long.parseLong(objects[1].toString()))
                .userName(objects[2] == null ? null : (objects[2].toString()))
                .nickName(objects[3] == null ? null : objects[3].toString())
                .build()).collect(Collectors.toList());
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
        Long organizationId = (Long) SecurityHelper.getCurrentUser().getAdditionalInformation().get(JwtSecurityPayloadKeys.ORGANIZATION_USER_ID.getValue());

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
                    .organizationId(daoService.findById(Organization.class, organizationId))
                    .build();
            Long countByFinancialUserGroupByEffectiveDate = getFinancialUserGroupByEffectiveDate(userId, financialUserGroupRequest.getGroupId(), financialUserGroupRequest.getEffectiveDate(), organizationId);
            Long countByFinancialUserGroupByDisableDate = getFinancialUserGroupByDisableDate(userId, financialUserGroupRequest.getGroupId(), financialUserGroupRequest.getDisableDate(), organizationId);


            if (countByFinancialUserGroupByEffectiveDate > 0 || countByFinancialUserGroupByDisableDate > 0) {
                continue;
            }
            financialUserGroupRepository.save(financialUserGroup);

        }

        return true;
    }
}
