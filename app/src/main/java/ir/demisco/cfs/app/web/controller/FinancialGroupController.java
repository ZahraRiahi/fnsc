package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialGroupInputModelRequest;
import ir.demisco.cfs.service.api.FinancialGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-financialGroup")
public class FinancialGroupController {
    private final FinancialGroupService financialGroupService;

    public FinancialGroupController(FinancialGroupService financialGroupService) {
        this.financialGroupService = financialGroupService;
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteFinancialGroup(@RequestBody FinancialGroupInputModelRequest financialGroupInputModelRequest) {
        return ResponseEntity.ok(financialGroupService.deleteFinancialGroup(financialGroupInputModelRequest));
    }
}
