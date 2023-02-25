package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
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
import java.util.Date;
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
    public List<FinancialAlternativeUsersOutputResponse> getFinancialAlternativeUsers(FinancialAlternativeUsersInputRequest financialAlternativeUsersInputRequest) {
        Object mainFinancialUserId = null;
        if (financialAlternativeUsersInputRequest.getMainFinancialUserId() != null) {
            mainFinancialUserId = "mainFinancialUserId";
        } else {
            financialAlternativeUsersInputRequest.setMainFinancialUserId(0L);
        }
        List<Object[]> financialUsersAlternativeList = financialUsersAlternativeRepository.getFinancialUserAlternativeByUserIdAndFlgAndOrgan(mainFinancialUserId, financialAlternativeUsersInputRequest.getMainFinancialUserId()
                , financialAlternativeUsersInputRequest.getFlgAllOrganizations(), SecurityHelper.getCurrentUser().getOrganizationId());
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
        Long currentOrganizationId = (Long) SecurityHelper.getCurrentUser().getAdditionalInformation().get(JwtSecurityPayloadKeys.ORGANIZATION_USER_ID.getValue());
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
                        currentOrganizationId, effectiveDate, a);
                Long countByFinancialUserAndOrganizationAndDisableDate = getFinancialUserAlternativeByFinancialUserAndOrganizationAndDisableDate(financialUserAlternativeRequest.getMainFinancialUserId(),
                        currentOrganizationId, disableDate, a);
                if (countByFinancialUserAndOrganizationAndDisableDate > 0 || countByFinancialUserAndOrganizationAndEffectiveDate > 0) {
                    continue;
                }
                financialUsersAlternativeRepository.save(userAlternative);

            }
        }
        return true;
    }
}