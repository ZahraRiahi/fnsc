package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputRequest;
import ir.demisco.cfs.model.dto.request.UserPermissionScopeRequest;
import ir.demisco.cfs.model.dto.request.SaveCompletePermissionRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecPermissionScopeOutputResponse;

import java.time.LocalDateTime;
import java.util.List;


public interface FinancialSecPermissionScopeService {
    List<FinancialSecPermissionScopeOutputResponse> getFinancialSecPermissionScope(FinancialSecPermissionScopeInputRequest financialSecPermissionScopeInputRequest);

    Boolean deleteFinancialSecPermissionScope(FinancialSecPermissionScopeInputModelRequest financialSecPermissionScopeInputModelRequest);

    Long getUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(Long financialUserId,
                                                                    Long financialLedgerTypeId,
                                                                    Long financialDepartmentId,
                                                                    Long departmentId,
                                                                    LocalDateTime effectiveDate,
                                                                    Long organizationId,
                                                                    Boolean allLedgerTypesFlag,
                                                                    Boolean allFncDepartmentFlag,
                                                                    Long financialGroupId);

    Long getUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(Long financialUserId,
                                                                  Long financialLedgerTypeId,
                                                                  Long financialDepartmentId,
                                                                  Long departmentId,
                                                                  LocalDateTime disableDate,
                                                                  Long organizationId,
                                                                  Boolean allLedgerTypesFlag,
                                                                  Boolean allFncDepartmentFlag,
                                                                  Long financialGroupId);

    Boolean saveFinancialSecPermissionScope(SaveCompletePermissionRequest saveCompletePermissionRequest);

    Boolean setUserPermissionScopeDisableDate(List<UserPermissionScopeRequest> userPermissionScopeRequestList);
}
