package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeRequest;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
import ir.demisco.cfs.service.impl.FinancialAlternativeUsersGridProvider;
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
@RequestMapping("/api-financialAlternativeUsers")
public class FinancialAlternativeUsersController {
    private final FinancialAlternativeUsersService financialAlternativeUsersService;
    private final FinancialAlternativeUsersGridProvider financialAlternativeUsersGridProvider;
    private final GridFilterService gridFilterService;


    public FinancialAlternativeUsersController(FinancialAlternativeUsersService financialAlternativeUsersService,
                                               FinancialAlternativeUsersGridProvider financialAlternativeUsersGridProvider,
                                               GridFilterService gridFilterService) {
        this.financialAlternativeUsersService = financialAlternativeUsersService;
        this.financialAlternativeUsersGridProvider = financialAlternativeUsersGridProvider;
        this.gridFilterService = gridFilterService;
    }

    @PostMapping("/GetFinancialAlternativeUsers")
    public ResponseEntity<DataSourceResult> getFinancialAlternativeUsers(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, financialAlternativeUsersGridProvider));

    }

    @PostMapping("/SetAlternativeUserEndDate")
    public ResponseEntity<Boolean> setAlternativeUserEndDate(@RequestBody FinancialAlternativeUsersListRequest financialAlternativeUsersListRequest) {
        return ResponseEntity.ok(financialAlternativeUsersService.setAlternativeUserEndDate(financialAlternativeUsersListRequest));
    }

    @PostMapping("/setDisableDate")
    public ResponseEntity<Boolean> setAlternativeSetDisableDate(@RequestBody FinancialUserAlternativeInputModelRequest financialUserAlternativeInputModelRequest) {
        return ResponseEntity.ok(financialAlternativeUsersService.setAlternativeSetDisableDate(financialUserAlternativeInputModelRequest));
    }

    @PostMapping("/saveFinancialAlternativeUsers")
    public ResponseEntity<Boolean> saveFinancialAlternativeUsers(@Valid @RequestBody FinancialUserAlternativeRequest financialUserAlternativeRequest) {
        return ResponseEntity.ok(financialAlternativeUsersService.saveFinancialAlternativeUsers(financialUserAlternativeRequest));
    }
}
