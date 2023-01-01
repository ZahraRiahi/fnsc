package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
import ir.demisco.cfs.model.entity.FinancialUserAlternative;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
import ir.demisco.cfs.service.repository.FinancialUsersAlternativeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        financialAlternativeUsersListRequest.getFinancialAlternativeId().forEach((Long aLong) -> {
            Optional<FinancialUserAlternative> alternativeRepository = financialUsersAlternativeRepository.findById(aLong);
            alternativeRepository.get().setDisableDate(financialAlternativeUsersListRequest.getDisableDate());
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean setAlternativeSetDisableDate(FinancialUserAlternativeInputModelRequest financialUserAlternativeInputModelRequest) {
        List<Long> count = financialUsersAlternativeRepository.getFinancialUserAlternativeByIdList(financialUserAlternativeInputModelRequest.getUserAlternativeIdList(),
                financialUserAlternativeInputModelRequest.getDisableDate());
        if (!count.isEmpty()) {
            throw new RuleException("برای کاربران جایگزین نمیبایست همپوشانی تاریخ وجود داشته باشد");
        }
        entityManager.createNativeQuery(" update FNSC.FINANCIAL_USER_ALTERNATIVE  T " +
                "   set   T.DISABLE_DATE = :disableDate " +
                "   WHERE T.ID in (:userAlternativeIdList) ")
                .setParameter("disableDate", financialUserAlternativeInputModelRequest.getDisableDate())
                .setParameter("userAlternativeIdList", financialUserAlternativeInputModelRequest.getUserAlternativeIdList()).executeUpdate();
        return true;
    }

}

