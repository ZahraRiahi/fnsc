package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.response.FinancialSecurityUserResponse;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;

import java.util.List;

public interface FinancialSecurityUserService {
    DataSourceResult getFinancialSecurityUser(DataSourceRequest dataSourceRequest);

    List<FinancialSecurityUserResponse> getGetFinancialUsersList();

}
