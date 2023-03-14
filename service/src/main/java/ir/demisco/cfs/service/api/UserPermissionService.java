package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialSecUserPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserPermissionInputModelRequest;
import ir.demisco.cfs.model.dto.request.UserPermissionRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface UserPermissionService {
    Boolean saveUserPermission(List<FinancialUserPermissionInputModelRequest> userPermissionInputModelRequestList);

    Boolean deleteUserPermission(FinancialSecUserPermissionScopeInputModelRequest financialSecUserPermissionScopeInputModelRequest);

    Long getUserPermissionByAllDocumentTypeFlagAndEffectiveDate(Long userPermissionScopeId,
                                                                Long financialUserIdCreator,
                                                                Long financialActivityTypeId,
                                                                Long financialDocumentTypeId,
                                                                Long financialPeriodId,
                                                                LocalDateTime effectiveDate,
                                                                Boolean allDocumentTypeFlag,
                                                                Boolean allFinancialPeriodFlag);

    Long getUserPermissionByAllDocumentTypeFlagAndDisableDate(
            Long userPermissionScopeId,
            Long financialUserIdCreator,
            Long financialActivityTypeId,
            Long financialDocumentTypeId,
            Long financialPeriodId,
            LocalDateTime disableDate,
            Boolean allDocumentTypeFlag,
            Boolean allFinancialPeriodFlag);

    Boolean setUserPermissionDisableDate(List<UserPermissionRequest> userPermissionRequestList);

    Long getUserPermissionByFinancialActivityTypeId(Long financialActivityTypeId);

    Long getUserPermissionByFinancialUserId(Long financialUserIdCreator);

}