package ir.demisco.cfs.model.dto.request;

import javax.validation.Valid;
import java.util.List;

public class SaveCompletePermissionRequest {
    private List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList;
    private List<FinancialUserPermissionRequest> financialUserPermissionRequestList;

    @Valid
    public List<FinancialSecPermissionScopeRequest> getFinancialSecPermissionScopeRequestList() {
        return financialSecPermissionScopeRequestList;
    }

    public void setFinancialSecPermissionScopeRequestList(List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList) {
        this.financialSecPermissionScopeRequestList = financialSecPermissionScopeRequestList;
    }

    @Valid
    public List<FinancialUserPermissionRequest> getFinancialUserPermissionRequestList() {
        return financialUserPermissionRequestList;
    }

    public void setFinancialUserPermissionRequestList(List<FinancialUserPermissionRequest> financialUserPermissionRequestList) {
        this.financialUserPermissionRequestList = financialUserPermissionRequestList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private SaveCompletePermissionRequest saveCompletePermissionRequest;

        private Builder() {
            saveCompletePermissionRequest = new SaveCompletePermissionRequest();
        }


        public Builder financialSecPermissionScopeRequestList(List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList) {
            saveCompletePermissionRequest.setFinancialSecPermissionScopeRequestList(financialSecPermissionScopeRequestList);
            return this;
        }

        public Builder financialUserPermissionRequestList(List<FinancialUserPermissionRequest> financialUserPermissionRequestList) {
            saveCompletePermissionRequest.setFinancialUserPermissionRequestList(financialUserPermissionRequestList);
            return this;
        }

        public SaveCompletePermissionRequest build() {
            return saveCompletePermissionRequest;
        }
    }
}
