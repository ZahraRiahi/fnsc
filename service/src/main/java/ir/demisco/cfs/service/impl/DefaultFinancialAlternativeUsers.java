package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
import ir.demisco.cfs.model.entity.FinancialUserAlternative;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
import ir.demisco.cfs.service.repository.FinancialUsersAlternativeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAlternativeUsers implements FinancialAlternativeUsersService {
    private final FinancialUsersAlternativeRepository financialUsersAlternativeRepository;

    public DefaultFinancialAlternativeUsers(FinancialUsersAlternativeRepository financialUsersAlternativeRepository) {
        this.financialUsersAlternativeRepository = financialUsersAlternativeRepository;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<FinancialAlternativeUsersOutputResponse> getFinancialAlternativeUsers(FinancialAlternativeUsersInputRequest financialAlternativeUsersInputRequest) {
        Object mainFinancialUserId = null;
        if (financialAlternativeUsersInputRequest.getMainFinancialUserId() != null) {
            mainFinancialUserId = "mainFinancialUserId";
        } else {
            financialAlternativeUsersInputRequest.setMainFinancialUserId(0L);
        }
        List<Object[]> financialUsersAlternativeList = financialUsersAlternativeRepository.getFinancialUserAlternativeByUserIdAndFlgAndOrgan(mainFinancialUserId, financialAlternativeUsersInputRequest.getMainFinancialUserId()
                , financialAlternativeUsersInputRequest.getFlgAllOrganizations(), financialAlternativeUsersInputRequest.getOrganizationId());
        return financialUsersAlternativeList.stream().map(objects -> FinancialAlternativeUsersOutputResponse.builder().financialAlternativeId(Long.parseLong(objects[0].toString()))
                .mainUserId(Long.parseLong(objects[1].toString()))
                .mainUserName(objects[2].toString())
                .mainNickName(objects[3].toString())
                .financialUserIdAlternative(Long.parseLong(objects[4].toString()))
                .alternativeUserName(objects[5].toString())
                .alternativeNickName(objects[6].toString())
                .effectiveDate((Date) objects[7])
                .disableDate((Date) objects[8])
                .organizationId(Long.parseLong(objects[9].toString()))
                .organizationName(objects[10].toString())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean setAlternativeUserEndDate(FinancialAlternativeUsersListRequest financialAlternativeUsersListRequest) {
        financialAlternativeUsersListRequest.getFinancialAlternativeId().forEach(aLong -> {
            Optional<FinancialUserAlternative> alternativeRepository = financialUsersAlternativeRepository.findById(aLong);
            alternativeRepository.get().setDisableDate(financialAlternativeUsersListRequest.getDisableDate());

        });


        return true;
    }

}

