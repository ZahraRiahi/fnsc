package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserGroupInputRequest;
import ir.demisco.cfs.model.dto.response.FinancialUserGroupOutputModelResponse;
import ir.demisco.cfs.service.api.FinancialUserGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-financialUserGroup")
public class FinancialUserGroupController {
    private final FinancialUserGroupService financialUserGroupService;

    public FinancialUserGroupController(FinancialUserGroupService financialUserGroupService) {
        this.financialUserGroupService = financialUserGroupService;
    }

    @PostMapping("/setDisableDate")
    public ResponseEntity<Boolean> setFinancialUserGroup(@RequestBody FinancialUserGroupInputRequest financialUserGroupInputRequest) {
        return ResponseEntity.ok(financialUserGroupService.setFinancialUserGroup(financialUserGroupInputRequest));
    }

    @PostMapping("/get")
    public ResponseEntity<List<FinancialUserGroupOutputModelResponse>> responseEntityFinancialUserGroup(@RequestBody FinancialUserGroupInputModelRequest financialUserGroupInputModelRequest) {
        return ResponseEntity.ok(financialUserGroupService.getFinancialUserGroup(financialUserGroupInputModelRequest));
    }
}
