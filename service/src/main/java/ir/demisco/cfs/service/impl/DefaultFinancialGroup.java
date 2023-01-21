package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialGroupInputModelRequest;
import ir.demisco.cfs.model.entity.FinancialUserGroup;
import ir.demisco.cfs.service.api.FinancialGroupService;
import ir.demisco.cfs.service.repository.FinancialGroupRepository;
import ir.demisco.cfs.service.repository.FinancialUserGroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DefaultFinancialGroup implements FinancialGroupService {
    private final FinancialGroupRepository financialGroupRepository;
    private final FinancialUserGroupRepository financialUserGroupRepository;

    public DefaultFinancialGroup(FinancialGroupRepository financialGroupRepository, FinancialUserGroupRepository financialUserGroupRepository) {
        this.financialGroupRepository = financialGroupRepository;
        this.financialUserGroupRepository = financialUserGroupRepository;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialGroup(FinancialGroupInputModelRequest financialGroupInputModelRequest) {
        List<FinancialUserGroup> financialUserGroupList = financialUserGroupRepository.findByFinancialGroupId(financialGroupInputModelRequest.getGroupId());
        financialUserGroupList.forEach(along1 -> financialUserGroupRepository.deleteById(along1.getFinancialGroupId().getId()));
        financialGroupInputModelRequest.getGroupId().forEach(aLong -> financialGroupRepository.deleteById(aLong));
        return true;
    }
}
