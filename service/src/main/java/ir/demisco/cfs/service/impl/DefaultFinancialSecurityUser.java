package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialUsersInputModelRequest;
import ir.demisco.cfs.model.dto.response.ApplicationUsersOutputResponse;
import ir.demisco.cfs.model.dto.response.FinancialSecurityUserResponse;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.service.api.FinancialSecurityUserService;
import ir.demisco.cfs.service.repository.ApplicationUserRepository;
import ir.demisco.cfs.service.repository.FinancialUserRepository;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultFinancialSecurityUser implements FinancialSecurityUserService {
    private final ApplicationUserRepository applicationUserRepository;
    private final FinancialUserRepository financialUserRepository;

    public DefaultFinancialSecurityUser(ApplicationUserRepository applicationUserRepository, FinancialUserRepository financialUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
        this.financialUserRepository = financialUserRepository;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DataSourceResult getFinancialSecurityUser(DataSourceRequest dataSourceRequest) {
        Pageable pageable = PageRequest.of(dataSourceRequest.getSkip(), dataSourceRequest.getTake());
        Page<Object[]> list = applicationUserRepository.getUserList(pageable);
        List<ApplicationUsersOutputResponse> applicationUsersOutputResponses = list.stream().map(item ->
                ApplicationUsersOutputResponse.builder()
                        .applicationUserId(Long.parseLong(item[0].toString()))
                        .userName(item[1] == null ? null : item[1].toString())
                        .nickName(item[2] == null ? null : item[2].toString())
                        .build()).collect(Collectors.toList());
        DataSourceResult dataSourceResult = new DataSourceResult();
        dataSourceResult.setData(applicationUsersOutputResponses);
        dataSourceResult.setTotal(list.getTotalElements());
        return dataSourceResult;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<FinancialSecurityUserResponse> getGetFinancialUsersList() {
        List<Object[]> financialUserSecurityList=financialUserRepository.getFinancialUserList();
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
        financialUsersInputModelRequest.getApplicationUserId().forEach(aLong -> {
            Long byFinancialUserByListUserId = financialUserRepository.findByFinancialUserByListUserId(aLong);
            if (byFinancialUserByListUserId == null){
                FinancialUser financialUser=new FinancialUser();
                financialUser.setApplicationUser(applicationUserRepository.getById(aLong));
                financialUserRepository.save(financialUser);
            }
        });

        return true;
    }

}

