package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeRequest;
import ir.demisco.cfs.model.entity.FinancialUserAlternative;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FinancialAlternativeUsersService {

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
