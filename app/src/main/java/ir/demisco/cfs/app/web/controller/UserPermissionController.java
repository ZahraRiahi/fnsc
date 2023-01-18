package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialUserPermissionInputModelRequest;
import ir.demisco.cfs.service.api.UserPermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-userPermission")
public class UserPermissionController {
    private final UserPermissionService userPermissionService;

    public UserPermissionController(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveUserPermission(@RequestBody List<FinancialUserPermissionInputModelRequest> financialUserPermissionInputModelRequest) {
        return ResponseEntity.ok(userPermissionService.saveUserPermission(financialUserPermissionInputModelRequest));
    }
}
