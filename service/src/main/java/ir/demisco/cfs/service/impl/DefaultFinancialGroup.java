package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialGroupRequest;
import ir.demisco.cfs.model.entity.FinancialGroup;
import ir.demisco.cfs.model.entity.FinancialUserGroup;
import ir.demisco.cfs.service.api.FinancialGroupService;
import ir.demisco.cfs.service.repository.FinancialGroupRepository;
import ir.demisco.cfs.service.repository.FinancialUserGroupRepository;
import ir.demisco.cloud.basic.model.entity.org.Organization;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DefaultFinancialGroup implements FinancialGroupService {
    private final FinancialGroupRepository financialGroupRepository;
    private final FinancialUserGroupRepository financialUserGroupRepository;
    private final DaoService daoService;

    public DefaultFinancialGroup(FinancialGroupRepository financialGroupRepository, FinancialUserGroupRepository financialUserGroupRepository,
                                 DaoService daoService) {
        this.financialGroupRepository = financialGroupRepository;
        this.financialUserGroupRepository = financialUserGroupRepository;
        this.daoService = daoService;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialGroup(FinancialGroupInputModelRequest financialGroupInputModelRequest) {
        List<FinancialUserGroup> financialUserGroupList = financialUserGroupRepository.findByFinancialGroupId(financialGroupInputModelRequest.getGroupId());
        if (!financialUserGroupList.isEmpty()) {
            throw new RuleException("fin.financialGroup.notBeDeleted");
        }
        financialUserGroupList.forEach(along1 -> financialUserGroupRepository.deleteById(along1.getFinancialGroupId().getId()));
        financialGroupInputModelRequest.getGroupId().forEach(aLong -> financialGroupRepository.deleteById(aLong));
        return true;
    }


    @Override
    public Boolean saveFinancialGroup(List<FinancialGroupRequest> financialGroupRequestList) {
        for (FinancialGroupRequest groupRequest : financialGroupRequestList) {
            if (groupRequest.getFinancialGroupId() == null) {
                FinancialGroup financialGroup = FinancialGroup.builder()
                        .code(groupRequest.getFinancialGroupCode())
                        .description(groupRequest.getFinancialGroupDescription())
                        .organization(groupRequest.getOrganizationId() != null ? daoService.findById(Organization.class, groupRequest.getOrganizationId()) : null)
                        .build();
                Long countByFinancialGroupByCode = getFinancialGroupByCode(groupRequest.getOrganizationId() != null ? groupRequest.getOrganizationId() : null, groupRequest.getFinancialGroupCode());
                Long countByFinancialGroupByDescription = getFinancialGroupByDescription(groupRequest.getOrganizationId() != null ? groupRequest.getOrganizationId() : null, groupRequest.getFinancialGroupDescription());
                if (countByFinancialGroupByCode > 0 || countByFinancialGroupByDescription > 0) {
                    continue;
                }
                financialGroupRepository.save(financialGroup);
            } else {
                updateFinancialGroup(financialGroupRequestList);

            }
        }
        return true;
    }

    @Override
    public Long getFinancialGroupByCode(Long organizationId, String code) {
        return financialGroupRepository.findFinancialGroupByCode(organizationId, code);
    }

    @Override
    public Long getFinancialGroupByDescription(Long organizationId, String description) {
        return financialGroupRepository.findFinancialGroupByDescription(organizationId, description);
    }

    private Boolean updateFinancialGroup(List<FinancialGroupRequest> financialGroupRequestList) {
        for (FinancialGroupRequest groupRequest : financialGroupRequestList) {
            FinancialGroup oldFinancialGroup = financialGroupRepository.getOne(groupRequest.getFinancialGroupId());
            oldFinancialGroup.setCode(groupRequest.getFinancialGroupCode());
            oldFinancialGroup.setDescription(groupRequest.getFinancialGroupDescription());
            oldFinancialGroup.setOrganization(groupRequest.getOrganizationId() != null ? daoService.findById(Organization.class, groupRequest.getOrganizationId()) : null);
            Long countByFinancialGroupByCode = getFinancialGroupByCode(groupRequest.getOrganizationId() != null ? groupRequest.getOrganizationId() : null, groupRequest.getFinancialGroupCode());
            Long countByFinancialGroupByDescription = getFinancialGroupByDescription(groupRequest.getOrganizationId() != null ? groupRequest.getOrganizationId() : null, groupRequest.getFinancialGroupDescription());
            if (countByFinancialGroupByCode > 0 || countByFinancialGroupByDescription > 0) {
                continue;
            }
            financialGroupRepository.save(oldFinancialGroup);
        }
        return true;
    }

}


