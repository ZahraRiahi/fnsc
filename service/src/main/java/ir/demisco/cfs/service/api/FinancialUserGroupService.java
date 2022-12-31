package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.response.FinancialUserGroupOutputModelResponse;

import java.util.List;

public interface FinancialUserGroupService {
    Boolean setFinancialUserGroup(FinancialUserGroupInputRequest financialUserGroupInputRequest);

    List<FinancialUserGroupOutputModelResponse> getFinancialUserGroup(FinancialUserGroupInputModelRequest financialUserGroupInputModelRequest);

}
