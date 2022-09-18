package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-financialAlternativeUsers")
public class FinancialAlternativeUsersController {
    private final FinancialAlternativeUsersService financialAlternativeUsersService;

    public FinancialAlternativeUsersController(FinancialAlternativeUsersService financialAlternativeUsersService) {
        this.financialAlternativeUsersService = financialAlternativeUsersService;
    }

    @PostMapping("/GetFinancialAlternativeUsers")
    public ResponseEntity<List<FinancialAlternativeUsersOutputResponse>> responseEntityFinancialAlternativeUsers(@RequestBody FinancialAlternativeUsersInputRequest financialAlternativeUsersInputRequest, Long organizationId) {
        return ResponseEntity.ok(financialAlternativeUsersService.getFinancialAlternativeUsers(financialAlternativeUsersInputRequest, SecurityHelper.getCurrentUser().getOrganizationId()));
    }
}
