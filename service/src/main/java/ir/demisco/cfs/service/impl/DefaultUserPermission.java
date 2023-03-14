package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialSecUserPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserPermissionInputModelRequest;
import ir.demisco.cfs.model.dto.request.UserPermissionRequest;
import ir.demisco.cfs.model.entity.FinancialActivityType;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.model.entity.UserPermission;
import ir.demisco.cfs.model.entity.UserPermissionScope;
import ir.demisco.cfs.model.entity.FinancialDocumentType;
import ir.demisco.cfs.model.entity.FinancialPeriod;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.repository.UserPermissionRepository;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.core.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefaultUserPermission implements UserPermissionService {
    private final UserPermissionRepository userPermissionRepository;
    private final DaoService daoService;

    public DefaultUserPermission(UserPermissionRepository userPermissionRepository, DaoService daoService) {
        this.userPermissionRepository = userPermissionRepository;
        this.daoService = daoService;
    }


    @Override
    public Boolean saveUserPermission(List<FinancialUserPermissionInputModelRequest> userPermissionInputModelRequestList) {
        for (FinancialUserPermissionInputModelRequest financialUserPermissionInputModelRequest : userPermissionInputModelRequestList) {
            checkUniqueUserPermission(userPermissionInputModelRequestList);
            UserPermission userPermission = UserPermission.builder()
                    .userPermissionScopeId(daoService.findById(UserPermissionScope.class, financialUserPermissionInputModelRequest.getUserPermissionScopeId()))
                    .financialUserIdCreator(financialUserPermissionInputModelRequest.getFinancialUserIdCreator() != null ?
                            daoService.findById(FinancialUser.class, financialUserPermissionInputModelRequest.getFinancialUserIdCreator()) : null)
                    .financialTypeActivityId(daoService.findById(FinancialActivityType.class, financialUserPermissionInputModelRequest.getFinancialActivityTypeId()))
                    .financialDocumentTypeId(financialUserPermissionInputModelRequest.getFinancialDocumentTypeId() != null ?
                            daoService.findById(FinancialDocumentType.class, financialUserPermissionInputModelRequest.getFinancialDocumentTypeId()) : null)
                    .financialPeriodId(financialUserPermissionInputModelRequest.getFinancialPeriodId() != null ?
                            daoService.findById(FinancialPeriod.class, financialUserPermissionInputModelRequest.getFinancialPeriodId()) : null)
                    .effectiveDate(financialUserPermissionInputModelRequest.getEffectiveDate())
                    .disableDate(financialUserPermissionInputModelRequest.getDisableDate() == null ? null :
                            financialUserPermissionInputModelRequest.getDisableDate())
                    .allDocumentTypeFlag(financialUserPermissionInputModelRequest.getAllDocumentTypeFlag())
                    .allFinancialPeriodFlag(financialUserPermissionInputModelRequest.getAllFinancialPeriodFlag())
                    .build();
            Long userPermissionByAllDocumentTypeFlagAndEffectiveDate = getUserPermissionByAllDocumentTypeFlagAndEffectiveDate(financialUserPermissionInputModelRequest.getUserPermissionScopeId(),
                    financialUserPermissionInputModelRequest.getFinancialUserIdCreator() == null ? null :
                            financialUserPermissionInputModelRequest.getFinancialUserIdCreator(),
                    financialUserPermissionInputModelRequest.getFinancialActivityTypeId(),
                    financialUserPermissionInputModelRequest.getFinancialDocumentTypeId() == null ? null :
                            financialUserPermissionInputModelRequest.getFinancialDocumentTypeId()
                    , financialUserPermissionInputModelRequest.getFinancialPeriodId(),
                    DateUtil.truncate(financialUserPermissionInputModelRequest.getEffectiveDate()),
                    financialUserPermissionInputModelRequest.getAllDocumentTypeFlag(),
                    financialUserPermissionInputModelRequest.getAllFinancialPeriodFlag());
            Long userPermissionByAllDocumentTypeFlagAndDisableDate = getUserPermissionByAllDocumentTypeFlagAndDisableDate(financialUserPermissionInputModelRequest.getUserPermissionScopeId(),
                    financialUserPermissionInputModelRequest.getFinancialUserIdCreator() == null ? null :
                            financialUserPermissionInputModelRequest.getFinancialUserIdCreator(),
                    financialUserPermissionInputModelRequest.getFinancialActivityTypeId(),
                    financialUserPermissionInputModelRequest.getFinancialDocumentTypeId() == null ? null :
                            financialUserPermissionInputModelRequest.getFinancialDocumentTypeId(),
                    financialUserPermissionInputModelRequest.getFinancialPeriodId(),
                    financialUserPermissionInputModelRequest.getDisableDate() == null ? null :
                            DateUtil.truncate(financialUserPermissionInputModelRequest.getDisableDate()),
                    financialUserPermissionInputModelRequest.getAllDocumentTypeFlag(), financialUserPermissionInputModelRequest.getAllFinancialPeriodFlag());
            if (userPermissionByAllDocumentTypeFlagAndEffectiveDate > 0 || userPermissionByAllDocumentTypeFlagAndDisableDate > 0) {
                continue;
            }
            userPermissionRepository.save(userPermission);

        }
        return true;
    }

    private void checkUniqueUserPermission(List<FinancialUserPermissionInputModelRequest> userPermissionInputModelRequestList) {
        if (userPermissionInputModelRequestList.stream()
                .anyMatch(i -> (i.getFinancialDocumentTypeId() == null && !i.getAllDocumentTypeFlag()) ||
                        (i.getFinancialDocumentTypeId() != null && i.getAllDocumentTypeFlag()))) {
            throw new RuleException(" فلگ دسترسی به همه انواع سند و فیلد نوع سند همخوانی ندارد. ");
        }
        if (userPermissionInputModelRequestList.stream()
                .anyMatch(i -> (i.getFinancialPeriodId() == null && !i.getAllFinancialPeriodFlag()) ||
                        (i.getFinancialPeriodId() != null && i.getAllFinancialPeriodFlag()))) {
            throw new RuleException(" فلگ دسترسی به همه دوره های مالی  و فیلد دوره مالی همخوانی ندارد. ");
        }
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
                financialPeriodId, DateUtil.truncate(effectiveDate), allDocumentTypeFlag, allFinancialPeriodFlag);
    }

    @Override
    public Long getUserPermissionByAllDocumentTypeFlagAndDisableDate(Long userPermissionScopeId, Long financialUserIdCreator, Long financialActivityTypeId, Long financialDocumentTypeId, Long financialPeriodId, LocalDateTime disableDate, Boolean allDocumentTypeFlag, Boolean allFinancialPeriodFlag) {
        return userPermissionRepository.findUserPermissionByAllDocumentTypeFlagAndDisableDate(userPermissionScopeId,
                financialUserIdCreator, financialActivityTypeId, financialDocumentTypeId, financialPeriodId,
                DateUtil.truncate(disableDate), allDocumentTypeFlag, allFinancialPeriodFlag);
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
