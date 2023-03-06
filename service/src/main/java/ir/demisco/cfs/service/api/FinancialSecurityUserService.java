package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialUsersInputModelRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecurityUserResponse;

import java.util.List;

public interface FinancialSecurityUserService {

    List<FinancialSecurityUserResponse> getGetFinancialUsersList();

    Boolean saveFinancialUsers(FinancialUsersInputModelRequest financialUsersInputModelRequest);

    Boolean deleteFinancialUser(Long financialUserId);

}
