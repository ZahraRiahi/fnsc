package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialSecurityFilterRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecurityOutputResponse;
import ir.demisco.cfs.service.api.FinancialSecurityService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-financialSecurity")
public class FinancialSecurityController {
    private final FinancialSecurityService financialSecurityService;

    public FinancialSecurityController(FinancialSecurityService financialSecurityService) {
        this.financialSecurityService = financialSecurityService;
    }

    @PostMapping("/HasPermission")
    public ResponseEntity<FinancialSecurityOutputResponse> responseEntitySetStatus(@RequestBody FinancialSecurityFilterRequest financialSecurityFilterRequest,Long organizationId) {
        return ResponseEntity.ok(financialSecurityService.hasPermission(financialSecurityFilterRequest, SecurityHelper.getCurrentUser().getOrganizationId()));
    }
}
