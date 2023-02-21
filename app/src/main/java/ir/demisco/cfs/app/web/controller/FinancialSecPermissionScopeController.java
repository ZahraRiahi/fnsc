package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputRequest;
import ir.demisco.cfs.model.dto.request.PermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.SaveCompletePermissionRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecPermissionScopeOutputResponse;
import ir.demisco.cfs.service.api.FinancialSecPermissionScopeService;
import ir.demisco.cfs.service.impl.UserPermissionGridProvider;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-financialSecPermissionScope")
public class FinancialSecPermissionScopeController {
    private final FinancialSecPermissionScopeService financialSecPermissionScopeService;
    private final GridFilterService gridFilterService;
    private final UserPermissionGridProvider userPermissionGridProvider;

    public FinancialSecPermissionScopeController(FinancialSecPermissionScopeService financialSecPermissionScopeService,
                                                 GridFilterService gridFilterService,
                                                 UserPermissionGridProvider userPermissionGridProvider) {
        this.financialSecPermissionScopeService = financialSecPermissionScopeService;
        this.gridFilterService = gridFilterService;
        this.userPermissionGridProvider = userPermissionGridProvider;
    }

    @PostMapping("/Get")
    public ResponseEntity<List<FinancialSecPermissionScopeOutputResponse>> responseEntity(@RequestBody FinancialSecPermissionScopeInputRequest financialSecPermissionScopeInputRequest) {
        return ResponseEntity.ok(financialSecPermissionScopeService.getFinancialSecPermissionScope(financialSecPermissionScopeInputRequest));
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteFinancialSecPermissionScope(@RequestBody FinancialSecPermissionScopeInputModelRequest financialSecPermissionScopeInputModelRequest) {
        return ResponseEntity.ok(financialSecPermissionScopeService.deleteFinancialSecPermissionScope(financialSecPermissionScopeInputModelRequest));
    }

    @PostMapping("/setDisableDate")
    public ResponseEntity<Boolean> setDisableDate(@RequestBody PermissionScopeInputModelRequest permissionScopeInputModelRequest) {
        return ResponseEntity.ok(financialSecPermissionScopeService.setDisableDate(permissionScopeInputModelRequest));
    }

    @PostMapping("/get")
    public ResponseEntity<DataSourceResult> getUserPermission(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, userPermissionGridProvider));
    }

//    @PostMapping("/save")
//    public ResponseEntity<Boolean> saveFinancialSecPermissionScope(@Valid @RequestBody SaveCompletePermissionRequest saveCompletePermissionRequest) {
//        return ResponseEntity.ok(financialSecPermissionScopeService.saveFinancialSecPermissionScope(saveCompletePermissionRequest));
//    }
}
