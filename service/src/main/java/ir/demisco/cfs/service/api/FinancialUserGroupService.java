package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupRequest;
import ir.demisco.cfs.model.dto.response.FinancialUserGroupOutputModelResponse;
import ir.demisco.cfs.model.entity.FinancialUserGroup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FinancialUserGroupService {
    Boolean setFinancialUserGroup(FinancialUserGroupInputRequest financialUserGroupInputRequest);

    List<FinancialUserGroupOutputModelResponse> getFinancialUserGroup(FinancialUserGroupInputModelRequest financialUserGroupInputModelRequest);

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


}
