package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
import java.util.List;

public interface FinancialAlternativeUsersService {
    List<FinancialAlternativeUsersOutputResponse> getFinancialAlternativeUsers(FinancialAlternativeUsersInputRequest financialAlternativeUsersInputRequest);

    Boolean setAlternativeUserEndDate(FinancialAlternativeUsersListRequest financialAlternativeUsersListRequest);

    Boolean setAlternativeSetDisableDate(FinancialUserAlternativeInputModelRequest financialUserAlternativeInputModelRequest);

}
