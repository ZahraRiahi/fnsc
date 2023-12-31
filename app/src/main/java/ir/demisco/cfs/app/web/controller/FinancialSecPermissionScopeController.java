package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputRequest;
import ir.demisco.cfs.model.dto.request.SaveCompletePermissionRequest;
import ir.demisco.cfs.model.dto.request.UserPermissionScopeRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecPermissionScopeOutputResponse;
import ir.demisco.cfs.service.api.FinancialSecPermissionScopeService;
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


    public FinancialSecPermissionScopeController(FinancialSecPermissionScopeService financialSecPermissionScopeService) {
        this.financialSecPermissionScopeService = financialSecPermissionScopeService;
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
    public ResponseEntity<Boolean> setUserPermissionScopeDisableDate(@RequestBody List<UserPermissionScopeRequest> userPermissionScopeRequestList) {
        return ResponseEntity.ok(financialSecPermissionScopeService.setUserPermissionScopeDisableDate(userPermissionScopeRequestList));
    }


    @PostMapping("/save")
    public ResponseEntity<Boolean> saveFinancialSecPermissionScope(@Valid @RequestBody SaveCompletePermissionRequest saveCompletePermissionRequest) {
        return ResponseEntity.ok(financialSecPermissionScopeService.saveFinancialSecPermissionScope(saveCompletePermissionRequest));
    }
}
