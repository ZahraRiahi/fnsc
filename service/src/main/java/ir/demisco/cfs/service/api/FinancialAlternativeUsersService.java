package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
import ir.demisco.cfs.model.entity.FinancialUserAlternative;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FinancialAlternativeUsersService {
    List<FinancialAlternativeUsersOutputResponse> getFinancialAlternativeUsers(FinancialAlternativeUsersInputRequest financialAlternativeUsersInputRequest);

    Boolean setAlternativeUserEndDate(FinancialAlternativeUsersListRequest financialAlternativeUsersListRequest);

    Boolean setAlternativeSetDisableDate(FinancialUserAlternativeInputModelRequest financialUserAlternativeInputModelRequest);

    Long getFinancialUserAlternativeByFinancialUserAndOrganizationAndEffectiveDate(Long financialUserId,
                                                                                   Long organizationId,
                                                                                   LocalDateTime effectiveDate,
                                                                                   Long financialUserAlternativeId);

    Long getFinancialUserAlternativeByFinancialUserAndOrganizationAndDisableDate(Long financialUserId,
                                                                                 Long organizationId,
                                                                                 LocalDateTime disableDate,
                                                                                 Long financialUserAlternativeId);

    Optional<FinancialUserAlternative> getFinancialUserAlternativeByOrganizationId(Long mainFinancialUserId,
                                                                                   LocalDateTime effectiveDate,
                                                                                   Long organizationId);

    Boolean saveFinancialAlternativeUsers(FinancialUserAlternativeRequest financialUserAlternativeRequest);


}
