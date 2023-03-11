package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.service.impl.FinancialSystemSubjectGridProvider;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-financialSystemSubject")
public class FinancialSystemSubjectController {
    private final GridFilterService gridFilterService;
    private final FinancialSystemSubjectGridProvider financialSystemSubjectGridProvider;

    public FinancialSystemSubjectController(GridFilterService gridFilterService,
                                            FinancialSystemSubjectGridProvider financialSystemSubjectGridProvider) {
        this.gridFilterService = gridFilterService;
        this.financialSystemSubjectGridProvider = financialSystemSubjectGridProvider;
    }

    @PostMapping("/get")
    public ResponseEntity<DataSourceResult> getUserPermission(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, financialSystemSubjectGridProvider));
    }
}
