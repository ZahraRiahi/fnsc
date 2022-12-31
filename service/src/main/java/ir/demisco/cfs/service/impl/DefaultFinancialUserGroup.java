package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.response.FinancialUserGroupOutputModelResponse;
import ir.demisco.cfs.service.api.FinancialUserGroupService;
import ir.demisco.cfs.service.repository.FinancialUserGroupRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

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
}
