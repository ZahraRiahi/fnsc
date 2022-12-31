package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;

public interface FinancialUserGroupService {
    Boolean setFinancialUserGroup(FinancialUserGroupInputRequest financialUserGroupInputRequest);
}
