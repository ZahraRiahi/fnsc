package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialSecUserPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserPermissionInputModelRequest;
import ir.demisco.cfs.model.entity.UserPermission;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.repository.FinancialActivityTypeRepository;
import ir.demisco.cfs.service.repository.FinancialDocumentTypeRepository;
import ir.demisco.cfs.service.repository.FinancialPeriodRepository;
import ir.demisco.cfs.service.repository.FinancialUserRepository;
import ir.demisco.cfs.service.repository.UserPermissionRepository;
import ir.demisco.cfs.service.repository.UserPermissionScopeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultUserPermission implements UserPermissionService {
    private final UserPermissionScopeRepository userPermissionScopeRepository;
    private final FinancialUserRepository financialUserRepository;
    private final FinancialActivityTypeRepository financialActivityTypeRepository;
    private final FinancialDocumentTypeRepository financialDocumentTypeRepository;
    private final FinancialPeriodRepository financialPeriodRepository;
    private final UserPermissionRepository userPermissionRepository;

    public DefaultUserPermission(UserPermissionScopeRepository userPermissionScopeRepository, FinancialUserRepository financialUserRepository, FinancialActivityTypeRepository financialActivityTypeRepository, FinancialDocumentTypeRepository financialDocumentTypeRepository, FinancialPeriodRepository financialPeriodRepository, UserPermissionRepository userPermissionRepository) {
        this.userPermissionScopeRepository = userPermissionScopeRepository;

        this.financialUserRepository = financialUserRepository;
        this.financialActivityTypeRepository = financialActivityTypeRepository;
        this.financialDocumentTypeRepository = financialDocumentTypeRepository;
        this.financialPeriodRepository = financialPeriodRepository;
        this.userPermissionRepository = userPermissionRepository;
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean saveUserPermission(List<FinancialUserPermissionInputModelRequest> financialUserPermissionInputModelRequest) {
        if (financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId() == null && financialUserPermissionInputModelRequest.get(0).getAllDocumentTypeFlag().equals(false)) {
            throw new RuleException(" در این حالت میبایستی شناسه ی نوع سند مخالف null بوده و یا فلگ دسترسی به همه ی انوع سند true باشد");
        } else if (financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId() != null && financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId().equals(true)) {
            throw new RuleException(" در این حالت میبایستی شناسه ی نوع سند null بوده و یا فلگ دسترسی به همه ی انوع سند false باشد");
        }

        Long countEffectiveDate = userPermissionRepository.getPermissionByScopeIdAndFlgAndEffectiveDate(financialUserPermissionInputModelRequest.get(0).getUserPermissionScopeId(),
                financialUserPermissionInputModelRequest.get(0).getFinancialTypeActivityId(), financialUserPermissionInputModelRequest.get(0).getEffectiveDate()
                , financialUserPermissionInputModelRequest.get(0).getFinancialUserIdCreator(), financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId(),
                financialUserPermissionInputModelRequest.get(0).getFinancialPeriodId(), financialUserPermissionInputModelRequest.get(0).getAllDocumentTypeFlag(),
                financialUserPermissionInputModelRequest.get(0).getAllFinancialPeriodFlag());
        if (countEffectiveDate == 0) {
            throw new RuleException(" رکوردی با این اطلاعات قبلا ثبت شده است");
        }

        Long countDisableDate = userPermissionRepository.getPermissionByScopeIdAndFlgAndDisableDate(financialUserPermissionInputModelRequest.get(0).getUserPermissionScopeId(),
                financialUserPermissionInputModelRequest.get(0).getFinancialTypeActivityId(), financialUserPermissionInputModelRequest.get(0).getDisableDate()
                , financialUserPermissionInputModelRequest.get(0).getFinancialUserIdCreator(), financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId(),
                financialUserPermissionInputModelRequest.get(0).getFinancialPeriodId(), financialUserPermissionInputModelRequest.get(0).getAllDocumentTypeFlag(),
                financialUserPermissionInputModelRequest.get(0).getAllFinancialPeriodFlag());
        if (countDisableDate == 0) {
            throw new RuleException(" رکوردی با این اطلاعات قبلا ثبت شده است");
        }
        financialUserPermissionInputModelRequest.forEach((FinancialUserPermissionInputModelRequest e) -> {
            UserPermission userPermission = new UserPermission();
            userPermission.setUserPermissionScopeId(userPermissionScopeRepository.getOne(e.getUserPermissionScopeId()));
            userPermission.setFinancialUserIdCreator(e.getFinancialUserIdCreator() == null ? null : financialUserRepository.getOne(e.getFinancialUserIdCreator()));
            userPermission.setFinancialTypeActivityId(financialActivityTypeRepository.getOne(e.getFinancialTypeActivityId()));
            userPermission.setFinancialDocumentTypeId(e.getFinancialDocumentTypeId() == null ? null : financialDocumentTypeRepository.getOne(e.getFinancialDocumentTypeId()));
            userPermission.setFinancialPeriodId(e.getFinancialPeriodId() == null ? null : financialPeriodRepository.getOne(e.getFinancialPeriodId()));
            userPermission.setEffectiveDate(e.getEffectiveDate());
            userPermission.setDisableDate(e.getFinancialPeriodId() == null ? null : e.getDisableDate());
            userPermission.setAllDocumentTypeFlag(e.getAllDocumentTypeFlag());
            userPermission.setAllFinancialPeriodFlag(e.getAllFinancialPeriodFlag());
            userPermissionRepository.save(userPermission);
        });

        return true;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean deleteUserPermission(FinancialSecUserPermissionScopeInputModelRequest financialSecUserPermissionScopeInputModelRequest) {
        financialSecUserPermissionScopeInputModelRequest.getUserPermissionId().forEach(aLong -> userPermissionRepository.deleteById(aLong));
        return  true;
    }
}
