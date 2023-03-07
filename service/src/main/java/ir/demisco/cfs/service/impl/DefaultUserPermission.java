package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialSecUserPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserPermissionInputModelRequest;
import ir.demisco.cfs.model.dto.request.UserPermissionRequest;
import ir.demisco.cfs.model.entity.UserPermission;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.repository.FinancialActivityTypeRepository;
import ir.demisco.cfs.service.repository.FinancialDocumentTypeRepository;
import ir.demisco.cfs.service.repository.FinancialUserRepository;
import ir.demisco.cfs.service.repository.UserPermissionScopeRepository;
import ir.demisco.cfs.service.repository.FinancialPeriodRepository;
import ir.demisco.cfs.service.repository.UserPermissionRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        Long s1 = financialUserPermissionInputModelRequest.get(0).getAllDocumentTypeFlag() == true ? 1L : 0L;
        Long s2 = financialUserPermissionInputModelRequest.get(0).getAllFinancialPeriodFlag() == true ? 1L : 0L;
        Object financialPeriodId = null;
        if (financialUserPermissionInputModelRequest.get(0).getFinancialPeriodId() != null) {
            financialPeriodId = "financialPeriodId";
        } else {
            financialUserPermissionInputModelRequest.get(0).setFinancialPeriodId(0L);
        }

        Object financialUserIdCreatorId = null;
        if (financialUserPermissionInputModelRequest.get(0).getFinancialUserIdCreator() != null) {
            financialUserIdCreatorId = "financialUserIdCreatorId";
        } else {
            financialUserPermissionInputModelRequest.get(0).setFinancialUserIdCreator(0L);
        }
        Object financialDocumentTypeId = null;
        if (financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId() != null) {
            financialDocumentTypeId = "financialDocumentTypeId";
        } else {
            financialUserPermissionInputModelRequest.get(0).setFinancialDocumentTypeId(0L);
        }
        Long countEffectiveDate = userPermissionRepository.getPermissionByScopeIdAndFlgAndEffectiveDate(financialUserPermissionInputModelRequest.get(0).getUserPermissionScopeId(),
                financialUserPermissionInputModelRequest.get(0).getFinancialTypeActivityId(),
                financialUserIdCreatorId, financialUserPermissionInputModelRequest.get(0).getFinancialUserIdCreator(), financialDocumentTypeId, financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId(),
                s1, s2, financialUserPermissionInputModelRequest.get(0).getEffectiveDate(), financialPeriodId, financialUserPermissionInputModelRequest.get(0).getFinancialPeriodId());
        if (countEffectiveDate != 0) {
            throw new RuleException(" رکوردی با این اطلاعات قبلا ثبت شده است");
        }

        Long countDisableDate = userPermissionRepository.getPermissionByScopeIdAndFlgAndDisableDate(financialUserPermissionInputModelRequest.get(0).getUserPermissionScopeId(),
                financialUserPermissionInputModelRequest.get(0).getFinancialTypeActivityId(),
                financialUserPermissionInputModelRequest.get(0).getDisableDate() == null ? LocalDateTime.now() : financialUserPermissionInputModelRequest.get(0).getDisableDate()
                , financialUserIdCreatorId, financialUserPermissionInputModelRequest.get(0).getFinancialUserIdCreator(), financialDocumentTypeId, financialUserPermissionInputModelRequest.get(0).getFinancialDocumentTypeId(),
                financialPeriodId, financialUserPermissionInputModelRequest.get(0).getFinancialPeriodId(), s1,
                s2);
        if (countDisableDate != 0) {
            throw new RuleException(" رکوردی با این اطلاعات قبلا ثبت شده است");
        }
        financialUserPermissionInputModelRequest.forEach((FinancialUserPermissionInputModelRequest e) -> {
            UserPermission userPermission = new UserPermission();
            userPermission.setUserPermissionScopeId(userPermissionScopeRepository.getOne(e.getUserPermissionScopeId()));
            userPermission.setFinancialUserIdCreator(e.getFinancialUserIdCreator() == 0L ? null : financialUserRepository.getOne(e.getFinancialUserIdCreator()));
            userPermission.setFinancialTypeActivityId(financialActivityTypeRepository.getOne(e.getFinancialTypeActivityId()));
            userPermission.setFinancialDocumentTypeId(e.getFinancialDocumentTypeId() == 0L ? null : financialDocumentTypeRepository.getOne(e.getFinancialDocumentTypeId()));
            userPermission.setFinancialPeriodId(e.getFinancialPeriodId() == 0L ? null : financialPeriodRepository.getOne(e.getFinancialPeriodId()));
            userPermission.setEffectiveDate(e.getEffectiveDate());
            userPermission.setDisableDate(e.getDisableDate() == null ? null : e.getDisableDate());
            userPermission.setAllDocumentTypeFlag(e.getAllDocumentTypeFlag() == true ? true : false);
            userPermission.setAllFinancialPeriodFlag(e.getAllFinancialPeriodFlag() == true ? true : false);
            userPermissionRepository.save(userPermission);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean deleteUserPermission(FinancialSecUserPermissionScopeInputModelRequest financialSecUserPermissionScopeInputModelRequest) {
        financialSecUserPermissionScopeInputModelRequest.getUserPermissionId().forEach(aLong -> userPermissionRepository.deleteById(aLong));
        return true;
    }

    @Override
    public Long getUserPermissionByAllDocumentTypeFlagAndEffectiveDate(Long userPermissionScopeId, Long financialUserIdCreator, Long financialActivityTypeId, Long financialDocumentTypeId, Long financialPeriodId, LocalDateTime effectiveDate, Boolean allDocumentTypeFlag, Boolean allFinancialPeriodFlag) {
        return userPermissionRepository.findUserPermissionByAllDocumentTypeFlagAndEffectiveDate(userPermissionScopeId,
                financialUserIdCreator,
                financialActivityTypeId, financialDocumentTypeId,
                financialPeriodId, effectiveDate, allDocumentTypeFlag, allFinancialPeriodFlag);
    }

    @Override
    public Long getUserPermissionByAllDocumentTypeFlagAndDisableDate(Long userPermissionScopeId, Long financialUserIdCreator, Long financialActivityTypeId, Long financialDocumentTypeId, Long financialPeriodId, LocalDateTime disableDate, Boolean allDocumentTypeFlag, Boolean allFinancialPeriodFlag) {
        return userPermissionRepository.findUserPermissionByAllDocumentTypeFlagAndDisableDate(userPermissionScopeId,
                financialUserIdCreator, financialActivityTypeId, financialDocumentTypeId, financialPeriodId,
                disableDate, allDocumentTypeFlag, allFinancialPeriodFlag);
    }

    @Override
    public Boolean setUserPermissionDisableDate(List<UserPermissionRequest> userPermissionRequestList) {
        for (UserPermissionRequest userPermissionRequest : userPermissionRequestList) {
            UserPermission oldUserPermission = userPermissionRepository.getOne(userPermissionRequest.getUserPermissionId());
            if (oldUserPermission.getDisableDate() == null) {
                oldUserPermission.setDisableDate(userPermissionRequest.getDisableDate());
                userPermissionRepository.save(oldUserPermission);
            }
        }
        return true;
    }

    @Override
    public Long getUserPermissionByFinancialActivityTypeId(Long financialActivityTypeId) {
        return userPermissionRepository.findUserPermissionByFinancialActivityTypeId(financialActivityTypeId);
    }

    @Override
    public Long getUserPermissionByFinancialUserId(Long financialUserIdCreator) {
        return userPermissionRepository.findUserPermissionByFinancialUserId(financialUserIdCreator);
    }

}
