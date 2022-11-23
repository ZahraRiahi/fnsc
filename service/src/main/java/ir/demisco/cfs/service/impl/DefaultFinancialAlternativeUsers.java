package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
import ir.demisco.cfs.service.repository.FinancialUsersAlternativeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAlternativeUsers implements FinancialAlternativeUsersService {
    private final FinancialUsersAlternativeRepository financialUsersAlternativeRepository;
    private final EntityManager entityManager;

    public DefaultFinancialAlternativeUsers(FinancialUsersAlternativeRepository financialUsersAlternativeRepository, EntityManager entityManager) {
        this.financialUsersAlternativeRepository = financialUsersAlternativeRepository;
        this.entityManager = entityManager;
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
        List<Long> uniqueEffectiveDate = financialUsersAlternativeRepository.getCountByFinancialUserAlternateByEffectiveDateAndOrg(financialAlternativeUsersListRequest.getFinancialAlternativeId().get(0), financialAlternativeUsersListRequest.getFinancialAlternativeId(), financialAlternativeUsersListRequest.getDisableDate()
                , SecurityHelper.getCurrentUser().getOrganizationId());
        if (uniqueEffectiveDate != null) {
            throw new RuleException("با این اطلاعات قبلا رکوردی ثبت شده است");
        }
        List<Object> resultList = entityManager.createNativeQuery(" UPDATE FNSC.FINANCIAL_USER_ALTERNATIVE T " +
                "   SET T.DISABLE_DATE = :disableDate " +
                " WHERE T.DISABLE_DATE IS NULL " +
                "   AND T.ID IN " +
                "       (:financialAlternativeUsersList)  ").setParameter("disableDate", financialAlternativeUsersListRequest.getDisableDate())
                .setParameter("financialAlternativeUsersList", financialAlternativeUsersListRequest.getFinancialAlternativeId())
                .getResultList();

        return true;
    }

}

