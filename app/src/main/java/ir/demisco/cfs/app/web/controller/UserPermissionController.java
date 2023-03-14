package ir.demisco.cfs.app.web.controller;

import ir.demisco.cfs.model.dto.request.FinancialSecUserPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserPermissionInputModelRequest;
import ir.demisco.cfs.model.dto.request.UserPermissionRequest;
import ir.demisco.cfs.model.dto.request.ValidList;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.impl.UserPermissionGridProvider;
import ir.demisco.cloud.core.middle.model.dto.DataSourceRequest;
import ir.demisco.cloud.core.middle.model.dto.DataSourceResult;
import ir.demisco.cloud.core.middle.service.business.api.core.GridFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-userPermission")
public class UserPermissionController {
    private final UserPermissionService userPermissionService;
    private final GridFilterService gridFilterService;
    private final UserPermissionGridProvider userPermissionGridProvider;

    public UserPermissionController(UserPermissionService userPermissionService, GridFilterService gridFilterService, UserPermissionGridProvider userPermissionGridProvider) {
        this.userPermissionService = userPermissionService;
        this.gridFilterService = gridFilterService;
        this.userPermissionGridProvider = userPermissionGridProvider;
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> saveUserPermission(@Valid @RequestBody ValidList<FinancialUserPermissionInputModelRequest> userPermissionInputModelRequestList) {
        return ResponseEntity.ok(userPermissionService.saveUserPermission(userPermissionInputModelRequestList));
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteUserPermission(@RequestBody FinancialSecUserPermissionScopeInputModelRequest financialSecUserPermissionScopeInputModelRequest) {
        return ResponseEntity.ok(userPermissionService.deleteUserPermission(financialSecUserPermissionScopeInputModelRequest));
    }

    @PostMapping("/get")
    public ResponseEntity<DataSourceResult> getUserPermission(@RequestBody DataSourceRequest dataSourceRequest) {
        return ResponseEntity.ok(gridFilterService.filter(dataSourceRequest, userPermissionGridProvider));
    }

    @PostMapping("/setDisableDate")
    public ResponseEntity<Boolean> setUserPermissionDisableDate(@RequestBody List<UserPermissionRequest> userPermissionRequestList) {
        return ResponseEntity.ok(userPermissionService.setUserPermissionDisableDate(userPermissionRequestList));
    }
}
