package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeRequest;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.model.entity.FinancialUserAlternative;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
import ir.demisco.cfs.service.repository.FinancialUsersAlternativeRepository;
import ir.demisco.cloud.basic.model.dto.org.response.OrganizationResponse;
import ir.demisco.cloud.basic.model.entity.org.Organization;
import ir.demisco.cloud.basic.organization.service.api.OrganizationService;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.model.security.JwtSecurityPayloadKeys;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import ir.demisco.core.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialAlternativeUsers implements FinancialAlternativeUsersService {
    private final FinancialUsersAlternativeRepository financialUsersAlternativeRepository;
    private final DaoService daoService;
    private final OrganizationService organizationService;
    private final EntityManager entityManager;

    public DefaultFinancialAlternativeUsers(FinancialUsersAlternativeRepository financialUsersAlternativeRepository, DaoService daoService, OrganizationService organizationService, EntityManager entityManager
    ) {
        this.financialUsersAlternativeRepository = financialUsersAlternativeRepository;
        this.daoService = daoService;
        this.organizationService = organizationService;
        this.entityManager = entityManager;
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

    @Override
    public Long getFinancialUserAlternativeByFinancialUserAndOrganizationAndEffectiveDate(Long financialUserId, Long organizationId,
                                                                                          LocalDateTime effectiveDate,
                                                                                          Long financialUserAlternativeId) {
        return financialUsersAlternativeRepository
                .findFinancialUserAlternativeByFinancialUserAndOrganizationAndEffectiveDate(financialUserId,
                        organizationId,
                        effectiveDate,
                        financialUserAlternativeId);
    }

    @Override
    public Long getFinancialUserAlternativeByFinancialUserAndOrganizationAndDisableDate(Long financialUserId,
                                                                                        Long organizationId,
                                                                                        LocalDateTime disableDate,
                                                                                        Long financialUserAlternativeId) {
        return financialUsersAlternativeRepository
                .findFinancialUserAlternativeByFinancialUserAndOrganizationAndDisableDate(financialUserId,
                        organizationId,
                        disableDate,
                        financialUserAlternativeId);
    }

    @Override
    public Optional<FinancialUserAlternative> getFinancialUserAlternativeByOrganizationId(Long mainFinancialUserId,
                                                                                          LocalDateTime effectiveDate,
                                                                                          Long organizationId) {
        return financialUsersAlternativeRepository.findFinancialUserAlternativeByOrganizationId(mainFinancialUserId,
                effectiveDate,
                organizationId);
    }

    @Override
    @Transactional
    public Boolean saveFinancialAlternativeUsers(FinancialUserAlternativeRequest financialUserAlternativeRequest) {
        LocalDateTime effectiveDate = DateUtil.truncate(financialUserAlternativeRequest.getEffectiveDate());
        LocalDateTime disableDate = DateUtil.truncate(financialUserAlternativeRequest.getDisableDate());
        if (financialUserAlternativeRequest.getFlagAllOrganization()) {
            financialUserAlternativeRequest.setOrganizationId(organizationService
                    .getAllOrganizationsByUserAndApplicationSystem(SecurityHelper.getCurrentUser().getUserId(),
                            (Long) SecurityHelper.getCurrentUser().getAdditionalInformation().get(JwtSecurityPayloadKeys.APPLICATION_SYSTEM_ID.getValue()))
                    .stream()
                    .map(OrganizationResponse::getId).collect(Collectors.toList()));

        }
        List<Long> applicationUserId = financialUserAlternativeRequest.getApplicationUserId();
        List<Long> organizationList = financialUserAlternativeRequest.getOrganizationId().stream().filter(o ->
                !getFinancialUserAlternativeByOrganizationId(financialUserAlternativeRequest.getMainFinancialUserId(),
                        effectiveDate, o).isPresent()).collect(Collectors.toList());

        if (organizationList.isEmpty()) {
            throw new RuleException("کاربر/کاربران جایگزین در بازه ی زمانی مورد نظر نمی توانند  به عنوان جایگزین کاربر اصلی درج شوند.");
        }

        for (Long o : organizationList) {
            for (Long a : applicationUserId) {
                FinancialUserAlternative userAlternative = FinancialUserAlternative.builder()
                        .organization(daoService.findById(Organization.class, o))
                        .financialUser(daoService.findById(FinancialUser.class, financialUserAlternativeRequest.getMainFinancialUserId()))
                        .alternative(daoService.findById(FinancialUser.class, a))
                        .effectiveDate(financialUserAlternativeRequest.getEffectiveDate())
                        .build();
                Long countByFinancialUserAndOrganizationAndEffectiveDate = getFinancialUserAlternativeByFinancialUserAndOrganizationAndEffectiveDate(financialUserAlternativeRequest.getMainFinancialUserId(),
                        o, effectiveDate, a);
                Long countByFinancialUserAndOrganizationAndDisableDate = getFinancialUserAlternativeByFinancialUserAndOrganizationAndDisableDate(financialUserAlternativeRequest.getMainFinancialUserId(),
                        o, disableDate, a);
                if (countByFinancialUserAndOrganizationAndDisableDate > 0 || countByFinancialUserAndOrganizationAndEffectiveDate > 0) {
                    continue;
                }
                financialUsersAlternativeRepository.save(userAlternative);

            }
        }
        return true;
    }

    @Override
    public Long getFinancialUserAlternativeByFinancialUserId(Long financialUserId) {
        return financialUsersAlternativeRepository.findFinancialUserAlternativeByFinancialUserId(financialUserId);
    }
}