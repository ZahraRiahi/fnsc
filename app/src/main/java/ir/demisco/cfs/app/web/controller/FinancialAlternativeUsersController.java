package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersInputRequest;
import ir.demisco.cfs.model.dto.request.FinancialAlternativeUsersListRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserAlternativeInputModelRequest;
import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersOutputResponse;
import ir.demisco.cfs.service.api.FinancialAlternativeUsersService;
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
    public ResponseEntity<List<FinancialAlternativeUsersOutputResponse>> responseEntityFinancialAlternativeUsers(@RequestBody FinancialAlternativeUsersInputRequest financialAlternativeUsersInputRequest) {
        return ResponseEntity.ok(financialAlternativeUsersService.getFinancialAlternativeUsers(financialAlternativeUsersInputRequest));
    }
    @PostMapping("/SetAlternativeUserEndDate")
    public ResponseEntity<Boolean> setAlternativeUserEndDate(@RequestBody FinancialAlternativeUsersListRequest financialAlternativeUsersListRequest) {
         return ResponseEntity.ok(financialAlternativeUsersService.setAlternativeUserEndDate(financialAlternativeUsersListRequest));
    }

    @PostMapping("/setDisableDate")
    public ResponseEntity<Boolean> setAlternativeSetDisableDate(@RequestBody FinancialUserAlternativeInputModelRequest financialUserAlternativeInputModelRequest) {
        return ResponseEntity.ok(financialAlternativeUsersService.setAlternativeSetDisableDate(financialUserAlternativeInputModelRequest));
    }
}
