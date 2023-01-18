package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialUserPermissionInputModelRequest;

import java.util.List;

public interface UserPermissionService {
    Boolean saveUserPermission(List<FinancialUserPermissionInputModelRequest> financialUserPermissionInputModelRequest);

}
