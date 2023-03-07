package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialUsersInputModelRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecurityUserResponse;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
import ir.demisco.cfs.service.api.FinancialSecPermissionScopeService;
import ir.demisco.cfs.service.api.FinancialSecurityUserService;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.repository.ApplicationUserRepository;
import ir.demisco.cfs.service.repository.FinancialUserRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialSecurityUser implements FinancialSecurityUserService {
    private final ApplicationUserRepository applicationUserRepository;
    private final FinancialUserRepository financialUserRepository;
    private final FinancialAlternativeUsersService financialAlternativeUsersService;
    private final UserPermissionService userPermissionService;
    private final FinancialSecPermissionScopeService financialSecPermissionScopeService;

    public DefaultFinancialSecurityUser(ApplicationUserRepository applicationUserRepository, FinancialUserRepository financialUserRepository,
                                        FinancialAlternativeUsersService financialAlternativeUsersService, UserPermissionService userPermissionService,
                                        FinancialSecPermissionScopeService financialSecPermissionScopeService) {
        this.applicationUserRepository = applicationUserRepository;
        this.financialUserRepository = financialUserRepository;
        this.financialAlternativeUsersService = financialAlternativeUsersService;
        this.userPermissionService = userPermissionService;
        this.financialSecPermissionScopeService = financialSecPermissionScopeService;
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<FinancialSecurityUserResponse> getGetFinancialUsersList() {
        List<Object[]> financialUserSecurityList = financialUserRepository.getFinancialUserList();
        return financialUserSecurityList.stream().map(objects -> FinancialSecurityUserResponse.builder()
                .applicationUserId(Long.parseLong(objects[0].toString()))
                .financialUserId(Long.parseLong(objects[1].toString()))
                .userName(objects[2].toString())
                .nickName(objects[3].toString())
                .build()).collect(Collectors.toList());

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Boolean saveFinancialUsers(FinancialUsersInputModelRequest financialUsersInputModelRequest) {
        financialUsersInputModelRequest.getApplicationUserId().forEach((Long aLong) -> {
            Long byFinancialUserByListUserId = financialUserRepository.findByFinancialUserByListUserId(aLong);
            if (byFinancialUserByListUserId == null) {
                FinancialUser financialUser = new FinancialUser();
                financialUser.setApplicationUser(applicationUserRepository.getById(aLong));
                financialUserRepository.save(financialUser);
            }
        });

        return true;
    }

    @Override
    public Boolean deleteFinancialUser(Long financialUserId) {
        Long financialUserAlternativeByFinancialUserId = financialAlternativeUsersService.getFinancialUserAlternativeByFinancialUserId(financialUserId);
        if (financialUserAlternativeByFinancialUserId > 0L) {
            throw new RuleException("امکان حذف این کاربر مالی وجود ندارد.");
        }
        Long userPermissionByFinancialUserId = userPermissionService.getUserPermissionByFinancialUserId(financialUserId);
        if (userPermissionByFinancialUserId > 0L) {
            throw new RuleException("امکان حذف این کاربر مالی وجود ندارد.");
        }
        Long userPermissionScopeByFinancialUserId = financialSecPermissionScopeService.findUserPermissionScopeByFinancialUserId(financialUserId);
        if (userPermissionScopeByFinancialUserId > 0L) {
            throw new RuleException("امکان حذف این کاربر مالی وجود ندارد.");
        }
        financialUserRepository.deleteById(financialUserId);
        return true;
    }

}

