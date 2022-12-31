package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecPermissionScopeOutputResponse;

import java.util.List;


public interface FinancialSecPermissionScopeService {
    List<FinancialSecPermissionScopeOutputResponse> getFinancialSecPermissionScope(FinancialSecPermissionScopeInputRequest financialSecPermissionScopeInputRequest);

}