package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialActivityTypeRequest;
import ir.demisco.cfs.service.api.FinancialActivityTypeService;
import org.springframework.http.ResponseEntity;
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

    public FinancialActivityTypeController(FinancialActivityTypeService financialActivityTypeService) {
        this.financialActivityTypeService = financialActivityTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveActivityType(@Valid @RequestBody List<FinancialActivityTypeRequest> financialActivityTypeRequestList) {
        return ResponseEntity.ok(financialActivityTypeService.saveActivityType(financialActivityTypeRequestList));
    }

}
