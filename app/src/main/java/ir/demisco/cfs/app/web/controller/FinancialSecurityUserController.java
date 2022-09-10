package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.response.FinancialSecurityUserResponse;
import ir.demisco.cfs.service.api.FinancialSecurityUserService;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api-financialSecurityUser")
public class FinancialSecurityUserController {
    private final FinancialSecurityUserService financialSecurityUserService;

    public FinancialSecurityUserController(FinancialSecurityUserService financialSecurityUserService) {
        this.financialSecurityUserService = financialSecurityUserService;
    }


    @PostMapping("/GetApplicationUsers")
    public ResponseEntity<DataSourceResult> responseEntity(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(financialSecurityUserService.getFinancialSecurityUser(dataSourceRequest));
    }
    @GetMapping("/GetFinancialUsers")
    public ResponseEntity<List<FinancialSecurityUserResponse>> responseEntity(){
        return ResponseEntity.ok(financialSecurityUserService.getGetFinancialUsersList());
    }
}
