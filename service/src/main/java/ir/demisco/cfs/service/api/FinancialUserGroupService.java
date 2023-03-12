package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupRequest;
import ir.demisco.cfs.model.entity.FinancialUserGroup;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FinancialUserGroupService {
    Boolean setFinancialUserGroup(FinancialUserGroupInputRequest financialUserGroupInputRequest);


    Long getFinancialUserGroupByEffectiveDate(Long financialUserId,
                                              Long financialGroupId,
                                              LocalDateTime effectiveDate,
                                              Long organizationId);

    Long getFinancialUserGroupByDisableDate(Long financialUserId,
                                            Long financialGroupId,
                                            LocalDateTime disableDate,
                                            Long organizationId);

    Optional<FinancialUserGroup> getFinancialUserGroupByEffectiveDateAndDisableDate(Long groupId,
                                                                                    Long financialUserId,
                                                                                    LocalDateTime effectiveDate,
                                                                                    LocalDateTime disableDate);

    Boolean saveFinancialUserGroup(FinancialUserGroupRequest financialUserGroupRequest);

    Long getFinancialUserGroupByFinancialUserId(Long financialUserId);


}
