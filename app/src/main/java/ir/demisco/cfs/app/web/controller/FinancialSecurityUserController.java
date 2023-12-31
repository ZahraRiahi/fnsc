package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialUsersInputModelRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecurityUserResponse;
import ir.demisco.cfs.service.api.FinancialSecurityUserService;
import ir.demisco.cfs.service.impl.ApplicationUsersGridProvider;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api-financialSecurityUser")
public class FinancialSecurityUserController {
    private final FinancialSecurityUserService financialSecurityUserService;
    private final GridFilterService gridFilterService;
    private final ApplicationUsersGridProvider applicationUsersGridProvider;

    public FinancialSecurityUserController(FinancialSecurityUserService financialSecurityUserService,
                                           GridFilterService gridFilterService,
                                           ApplicationUsersGridProvider applicationUsersGridProvider) {
        this.financialSecurityUserService = financialSecurityUserService;
        this.gridFilterService = gridFilterService;
        this.applicationUsersGridProvider = applicationUsersGridProvider;
    }


    @PostMapping("/GetApplicationUsers")
    public ResponseEntity<DataSourceResult> getApplicationUsers(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, applicationUsersGridProvider));

    }

    @GetMapping("/GetFinancialUsers")
    public ResponseEntity<List<FinancialSecurityUserResponse>> responseEntity() {
        return ResponseEntity.ok(financialSecurityUserService.getGetFinancialUsersList());
    }

    @PostMapping("/SaveFinancialUsers")
    public ResponseEntity<Boolean> saveFinancialUsers(@RequestBody FinancialUsersInputModelRequest financialUsersInputModelRequest) {
        return ResponseEntity.ok(financialSecurityUserService.saveFinancialUsers(financialUsersInputModelRequest));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteFinancialUser(@PathVariable("id") Long financialUserId) {
        Boolean deleteFinancialUser = financialSecurityUserService.deleteFinancialUser(financialUserId);
        return ResponseEntity.ok(deleteFinancialUser);
    }
}
