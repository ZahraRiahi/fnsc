package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialGroupRequest;
import ir.demisco.cfs.service.api.FinancialGroupService;
import ir.demisco.cfs.service.impl.FinancialGroupGridProvider;
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
@RequestMapping("/api-financialGroup")
public class FinancialGroupController {
    private final FinancialGroupService financialGroupService;
    private final GridFilterService gridFilterService;
    private final FinancialGroupGridProvider financialGroupGridProvider;

    public FinancialGroupController(FinancialGroupService financialGroupService, GridFilterService gridFilterService, FinancialGroupGridProvider financialGroupGridProvider) {
        this.financialGroupService = financialGroupService;
        this.gridFilterService = gridFilterService;
        this.financialGroupGridProvider = financialGroupGridProvider;
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteFinancialGroup(@RequestBody FinancialGroupInputModelRequest financialGroupInputModelRequest) {
        return ResponseEntity.ok(financialGroupService.deleteFinancialGroup(financialGroupInputModelRequest));
    }

    @PostMapping("/get")
    public ResponseEntity<DataSourceResult> getFinancialGroup(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, financialGroupGridProvider));

    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveFinancialGroup(@Valid @RequestBody List<FinancialGroupRequest> financialGroupRequestList) {
        return ResponseEntity.ok(financialGroupService.saveFinancialGroup(financialGroupRequestList));
    }
}
