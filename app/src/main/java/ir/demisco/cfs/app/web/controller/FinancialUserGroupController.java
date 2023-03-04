package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupRequest;
import ir.demisco.cfs.service.api.FinancialUserGroupService;
import ir.demisco.cfs.service.impl.FinancialUserGroupGridProvider;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-financialUserGroup")
public class FinancialUserGroupController {
    private final FinancialUserGroupService financialUserGroupService;
    private final FinancialUserGroupGridProvider financialUserGroupGridProvider;
    private final GridFilterService gridFilterService;

    public FinancialUserGroupController(FinancialUserGroupService financialUserGroupService,
                                        FinancialUserGroupGridProvider financialUserGroupGridProvider,
                                        GridFilterService gridFilterService) {
        this.financialUserGroupService = financialUserGroupService;
        this.financialUserGroupGridProvider = financialUserGroupGridProvider;
        this.gridFilterService = gridFilterService;
    }

    @PostMapping("/setDisableDate")
    public ResponseEntity<Boolean> setFinancialUserGroup(@RequestBody FinancialUserGroupInputRequest financialUserGroupInputRequest) {
        return ResponseEntity.ok(financialUserGroupService.setFinancialUserGroup(financialUserGroupInputRequest));
    }

    @PostMapping("/get")
    public ResponseEntity<DataSourceResult> getFinancialUserGroup(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, financialUserGroupGridProvider));

    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveFinancialUserGroup(@Valid @RequestBody FinancialUserGroupRequest financialUserGroupRequest) {
        return ResponseEntity.ok(financialUserGroupService.saveFinancialUserGroup(financialUserGroupRequest));
    }
}
