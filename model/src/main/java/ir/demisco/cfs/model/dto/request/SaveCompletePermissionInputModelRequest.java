package ir.demisco.cfs.model.dto.request;

import java.util.List;

public class SaveCompletePermissionInputModelRequest {
    private List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList;
    private List<FinancialUserPermissionInputModelRequest> financialUserPermissionInputModelRequests;

    public List<FinancialSecPermissionScopeRequest> getFinancialSecPermissionScopeRequestList() {
        return financialSecPermissionScopeRequestList;
    }

    public void setFinancialSecPermissionScopeRequestList(List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList) {
        this.financialSecPermissionScopeRequestList = financialSecPermissionScopeRequestList;
    }

    public List<FinancialUserPermissionInputModelRequest> getFinancialUserPermissionInputModelRequests() {
        return financialUserPermissionInputModelRequests;
    }

    public void setFinancialUserPermissionInputModelRequests(List<FinancialUserPermissionInputModelRequest> financialUserPermissionInputModelRequests) {
        this.financialUserPermissionInputModelRequests = financialUserPermissionInputModelRequests;
    }
}
