package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialActivityTypeRequest;
import ir.demisco.cfs.service.api.FinancialActivityTypeService;
import ir.demisco.cfs.service.impl.FinancialActivityTypeGridProvider;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-financialActivityType")
public class FinancialActivityTypeController {
    private final FinancialActivityTypeService financialActivityTypeService;
    private final GridFilterService gridFilterService;
    private final FinancialActivityTypeGridProvider financialActivityTypeGridProvider;

    public FinancialActivityTypeController(FinancialActivityTypeService financialActivityTypeService,
                                           GridFilterService gridFilterService,
                                           FinancialActivityTypeGridProvider financialActivityTypeGridProvider) {
        this.financialActivityTypeService = financialActivityTypeService;
        this.gridFilterService = gridFilterService;
        this.financialActivityTypeGridProvider = financialActivityTypeGridProvider;
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveActivityType(@Valid @RequestBody List<FinancialActivityTypeRequest> financialActivityTypeRequestList) {
        return ResponseEntity.ok(financialActivityTypeService.saveActivityType(financialActivityTypeRequestList));
    }

    @PostMapping("/get")
    public ResponseEntity<DataSourceResult> getFinancialActivityType(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, financialActivityTypeGridProvider));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteFinancialActivityType(@PathVariable("id") Long financialActivityTypeId) {
        Boolean deleteFinancialActivityType = financialActivityTypeService.deleteFinancialActivityType(financialActivityTypeId);
        return ResponseEntity.ok(deleteFinancialActivityType);
    }

}
