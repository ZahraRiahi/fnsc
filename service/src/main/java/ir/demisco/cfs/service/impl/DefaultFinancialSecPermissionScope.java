package ir.demisco.cfs.service.impl;


import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeInputRequest;
import ir.demisco.cfs.model.dto.request.SaveCompletePermissionRequest;
import ir.demisco.cfs.model.dto.request.UserPermissionScopeRequest;
import ir.demisco.cfs.model.dto.request.FinancialSecPermissionScopeRequest;
import ir.demisco.cfs.model.dto.request.FinancialUserPermissionRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecPermissionScopeOutputResponse;
import ir.demisco.cfs.model.entity.FinancialDepartment;
import ir.demisco.cfs.model.entity.FinancialLedgerType;
import ir.demisco.cfs.model.entity.UserPermission;
import ir.demisco.cfs.model.entity.UserPermissionScope;
import ir.demisco.cfs.model.entity.FinancialActivityType;
import ir.demisco.cfs.model.entity.FinancialGroup;
import ir.demisco.cfs.model.entity.FinancialPeriod;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.model.entity.FinancialDocumentType;
import ir.demisco.cfs.service.api.FinancialSecPermissionScopeService;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.repository.UserPermissionRepository;
import ir.demisco.cfs.service.repository.UserPermissionScopeRepository;
import ir.demisco.cloud.basic.model.entity.org.Department;
import ir.demisco.cloud.basic.model.entity.org.Organization;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.core.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class DefaultFinancialSecPermissionScope implements FinancialSecPermissionScopeService {
    private final UserPermissionScopeRepository userPermissionScopeRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final DaoService daoService;
    private final UserPermissionService userPermissionService;

    public DefaultFinancialSecPermissionScope(UserPermissionScopeRepository userPermissionScopeRepository,
                                              UserPermissionRepository userPermissionRepository, DaoService daoService, UserPermissionService userPermissionService) {
        this.userPermissionScopeRepository = userPermissionScopeRepository;
        this.userPermissionRepository = userPermissionRepository;

        this.daoService = daoService;
        this.userPermissionService = userPermissionService;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<FinancialSecPermissionScopeOutputResponse> getFinancialSecPermissionScope(FinancialSecPermissionScopeInputRequest financialSecPermissionScopeInputRequest) {
        Object financialUser = null;
        if (financialSecPermissionScopeInputRequest.getFinancialGroupId() == null && financialSecPermissionScopeInputRequest.getFinancialUserId() == null) {
            throw new RuleException("fin.financialSecPermissionScope.oneShouldBeFilled");
        }
        if (financialSecPermissionScopeInputRequest.getFinancialUserId() != null) {
            financialUser = "financialUser";
        } else {
            financialSecPermissionScopeInputRequest.setFinancialUserId(0L);
        }

        Object financialGroup = null;
        if (financialSecPermissionScopeInputRequest.getFinancialGroupId() != null) {
            financialGroup = "financialGroup";
        } else {
            financialSecPermissionScopeInputRequest.setFinancialGroupId(0L);
        }

        List<Object[]> controlFinancialAccountObject = userPermissionScopeRepository.findByUserPermissionScopeAndOrgAndAllFinancialLedgersFlg(financialSecPermissionScopeInputRequest.getFilterMode(),
                financialSecPermissionScopeInputRequest.getOrganizationId(),
                financialUser,
                financialSecPermissionScopeInputRequest.getFinancialUserId(),
                financialGroup,
                financialSecPermissionScopeInputRequest.getFinancialGroupId(),
                financialSecPermissionScopeInputRequest.getAllFinancialDepartmentFlg(),
                financialSecPermissionScopeInputRequest.getAllFinancialLedgersFlg());
        return controlFinancialAccountObject.stream().map(objects -> FinancialSecPermissionScopeOutputResponse
                        .builder()
                        .userPermissionScopeId(objects[0] == null ? null : Long.parseLong(objects[0].toString()))
                        .financialUserId(objects[1] == null ? null : Long.parseLong(objects[1].toString()))
                        .financialGroupId(objects[2] == null ? null : Long.parseLong(objects[2].toString()))
                        .financialDepartmentId(objects[3] == null ? null : Long.parseLong(objects[3].toString()))
                        .departmentId(objects[4] == null ? null : Long.parseLong(objects[4].toString()))
                        .financialLedgerTypeId(objects[5] == null ? null : Long.parseLong(objects[5].toString()))
                        .allLedgerTypesFlag(objects[6] == null ? null : ((Integer.parseInt(objects[6].toString())) == 1))
                        .allFncDepartmentFlag(objects[7] == null ? null : ((Integer.parseInt(objects[7].toString())) == 1))
                        .effectiveDate(objects[8] == null ? null : ((Timestamp) objects[8]).toLocalDateTime())
                        .disableDate(objects[9] == null ? null : ((Timestamp) objects[9]).toLocalDateTime())
                        .applicationUserId(objects[10] == null ? null : Long.parseLong(objects[10].toString()))
                        .financialUserName(objects[11] == null ? null : objects[11].toString())
                        .financialGroupCode(objects[12] == null ? null : objects[12].toString())
                        .financialGroupDescription(objects[13] == null ? null : objects[13].toString())
                        .departmentCode(objects[14] == null ? null : objects[14].toString())
                        .departmentName(objects[15] == null ? null : objects[15].toString())
                        .financialDepartmentCode(objects[16] == null ? null : objects[16].toString())
                        .financialDepartmentName(objects[17] == null ? null : objects[17].toString())
                        .ledgerTypeDescription(objects[18] == null ? null : objects[18].toString()).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialSecPermissionScope(FinancialSecPermissionScopeInputModelRequest financialSecPermissionScopeInputModelRequest) {
        List<Long> userPermissionCount = userPermissionRepository.getUserPermissionByUserPermissionScopeId(financialSecPermissionScopeInputModelRequest.getUserPermissionScopeId().get(0));
        if (!userPermissionCount.isEmpty()) {
            throw new RuleException("fin.financialSecPermissionScope.notBeDeleted");
        }
        financialSecPermissionScopeInputModelRequest.getUserPermissionScopeId().forEach(aLong -> userPermissionScopeRepository.deleteById(aLong));
        return true;
    }


    @Override
    public Long getUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(Long financialUserId, Long financialLedgerTypeId,
                                                                           Long financialDepartmentId, Long departmentId,
                                                                           LocalDateTime effectiveDate, Long organizationId,
                                                                           Boolean allLedgerTypesFlag, Boolean allFncDepartmentFlag,
                                                                           Long financialGroupId) {
        return userPermissionScopeRepository.findUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(financialUserId,
                financialLedgerTypeId, financialDepartmentId,
                departmentId, DateUtil.truncate(effectiveDate),
                organizationId, allLedgerTypesFlag,
                allFncDepartmentFlag, financialGroupId);
    }

    @Override
    public Long getUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(Long financialUserId, Long financialLedgerTypeId,
                                                                         Long financialDepartmentId,
                                                                         Long departmentId, LocalDateTime disableDate,
                                                                         Long organizationId, Boolean allLedgerTypesFlag,
                                                                         Boolean allFncDepartmentFlag, Long financialGroupId) {
        return userPermissionScopeRepository.findUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(financialUserId, financialLedgerTypeId,
                financialDepartmentId, departmentId, DateUtil.truncate(disableDate), organizationId, allLedgerTypesFlag,
                allFncDepartmentFlag, financialGroupId);
    }


    @Override
    public Boolean setUserPermissionScopeDisableDate(List<UserPermissionScopeRequest> userPermissionScopeRequestList) {
        for (UserPermissionScopeRequest userPermissionScopeRequest : userPermissionScopeRequestList) {
            UserPermissionScope oldUserPermissionScope = userPermissionScopeRepository.getOne(userPermissionScopeRequest.getUserPermissionScopeId());
            if (oldUserPermissionScope.getDisableDate() == null) {
                oldUserPermissionScope.setDisableDate(userPermissionScopeRequest.getDisableDate());
                UserPermissionScope userPermissionScope = userPermissionScopeRepository.save(oldUserPermissionScope);
                updateUserPermission(userPermissionScopeRequest, userPermissionScope);
            }
        }
        return true;
    }


    private void updateUserPermission(UserPermissionScopeRequest userPermissionScopeRequest, UserPermissionScope userPermissionScope) {
        for (UserPermission userPermission : userPermissionScope.getUserPermissions()) {
            UserPermission oldUserPermission = userPermissionRepository.getOne(userPermission.getId());
            if (oldUserPermission.getDisableDate() == null) {
                oldUserPermission.setDisableDate(userPermissionScopeRequest.getDisableDate());
                userPermissionRepository.save(oldUserPermission);
            }
        }
    }

    @Override
    public Boolean saveFinancialSecPermissionScope(SaveCompletePermissionRequest saveCompletePermissionRequest) {
        List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList = checkFinancialSecPermissionScopeRequest(saveCompletePermissionRequest);
        for (FinancialSecPermissionScopeRequest permissionScopeRequest : financialSecPermissionScopeRequestList) {
            if (!permissionScopeRequest.getFinancialUserId().isEmpty()) {
                checkNotEmptyFinancialUser(saveCompletePermissionRequest, permissionScopeRequest);

            } else {
                checkNotEmptyFinancialGroup(saveCompletePermissionRequest, permissionScopeRequest);
            }

        }
        return true;
    }

    private void checkNotEmptyFinancialGroup(SaveCompletePermissionRequest saveCompletePermissionRequest, FinancialSecPermissionScopeRequest permissionScopeRequest) {
        for (Long financialGroupId : permissionScopeRequest.getFinancialGroupId()) {
            UserPermissionScope userPermissionScope = UserPermissionScope.builder()
                    .financialLedgerType(permissionScopeRequest.getFinancialLedgerTypeId() != null ?
                            daoService.findById(FinancialLedgerType.class, permissionScopeRequest.getFinancialLedgerTypeId()) : null)
                    .financialDepartment(permissionScopeRequest.getFinancialDepartmentId() != null ?
                            daoService.findById(FinancialDepartment.class, permissionScopeRequest.getFinancialDepartmentId()) : null)
                    .department(daoService.findById(Department.class, permissionScopeRequest.getDepartmentId()))
                    .effectiveDate(permissionScopeRequest.getEffectiveDate())
                    .disableDate(permissionScopeRequest.getDisableDate() == null ? null : permissionScopeRequest.getDisableDate())
                    .allLedgerTypesFlag(permissionScopeRequest.getAllLedgerTypesFlag())
                    .allFncDepartmentFlag(permissionScopeRequest.getAllFncDepartmentFlag())
                    .financialGroup(daoService.findById(FinancialGroup.class, financialGroupId))
                    .organization(daoService.findById(Organization.class, permissionScopeRequest.getOrganizationId()))
                    .build();
            Long countByUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate = getUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(null,
                    permissionScopeRequest.getFinancialLedgerTypeId(),
                    permissionScopeRequest.getFinancialDepartmentId(),
                    permissionScopeRequest.getDepartmentId(),
                    DateUtil.truncate(permissionScopeRequest.getEffectiveDate()),
                    permissionScopeRequest.getOrganizationId(),
                    permissionScopeRequest.getAllLedgerTypesFlag(),
                    permissionScopeRequest.getAllFncDepartmentFlag(),
                    financialGroupId);
            Long countByUserPermissionScopeByAllLedgerTypesFlagAndDisableDate = getUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(null,
                    permissionScopeRequest.getFinancialLedgerTypeId(),
                    permissionScopeRequest.getFinancialDepartmentId(),
                    permissionScopeRequest.getDepartmentId(),
                    DateUtil.truncate(permissionScopeRequest.getDisableDate()),
                    permissionScopeRequest.getOrganizationId(),
                    permissionScopeRequest.getAllLedgerTypesFlag(),
                    permissionScopeRequest.getAllFncDepartmentFlag(), financialGroupId);
            if (countByUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate > 0 || countByUserPermissionScopeByAllLedgerTypesFlagAndDisableDate > 0) {
                continue;
            }
            UserPermissionScope save = userPermissionScopeRepository.save(userPermissionScope);
            Long userPermissionScopeId = save.getId();
            saveUserPermission(saveCompletePermissionRequest, userPermissionScopeId);
        }
    }

    private void checkNotEmptyFinancialUser(SaveCompletePermissionRequest saveCompletePermissionRequest,
                                            FinancialSecPermissionScopeRequest permissionScopeRequest) {
        for (Long financialUserId : permissionScopeRequest.getFinancialUserId()) {
            UserPermissionScope userPermissionScope = UserPermissionScope.builder()
                    .financialUser(daoService.findById(FinancialUser.class, financialUserId))
                    .financialLedgerType(permissionScopeRequest.getFinancialLedgerTypeId() != null ?
                            daoService.findById(FinancialLedgerType.class, permissionScopeRequest.getFinancialLedgerTypeId()) : null)
                    .financialDepartment(permissionScopeRequest.getFinancialDepartmentId() != null ?
                            daoService.findById(FinancialDepartment.class, permissionScopeRequest.getFinancialDepartmentId()) : null)
                    .department(daoService.findById(Department.class, permissionScopeRequest.getDepartmentId()))
                    .effectiveDate(permissionScopeRequest.getEffectiveDate())
                    .disableDate(permissionScopeRequest.getDisableDate() == null ? null : permissionScopeRequest.getDisableDate())
                    .allLedgerTypesFlag(permissionScopeRequest.getAllLedgerTypesFlag())
                    .allFncDepartmentFlag(permissionScopeRequest.getAllFncDepartmentFlag())
                    .organization(daoService.findById(Organization.class, permissionScopeRequest.getOrganizationId()))
                    .build();
            Long countByUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate = getUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(financialUserId,
                    permissionScopeRequest.getFinancialLedgerTypeId() == null ? null : permissionScopeRequest.getFinancialLedgerTypeId(),
                    permissionScopeRequest.getFinancialDepartmentId(),
                    permissionScopeRequest.getDepartmentId(),
                    DateUtil.truncate(permissionScopeRequest.getEffectiveDate()),
                    permissionScopeRequest.getOrganizationId(),
                    permissionScopeRequest.getAllLedgerTypesFlag(),
                    permissionScopeRequest.getAllFncDepartmentFlag(), null);
            Long countByUserPermissionScopeByAllLedgerTypesFlagAndDisableDate = getUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(financialUserId,
                    permissionScopeRequest.getFinancialLedgerTypeId() == null ? null : permissionScopeRequest.getFinancialLedgerTypeId(),
                    permissionScopeRequest.getFinancialDepartmentId(),
                    permissionScopeRequest.getDepartmentId(),
                    DateUtil.truncate(permissionScopeRequest.getDisableDate()),
                    permissionScopeRequest.getOrganizationId(),
                    permissionScopeRequest.getAllLedgerTypesFlag(),
                    permissionScopeRequest.getAllFncDepartmentFlag(), null);
            if (countByUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate > 0 || countByUserPermissionScopeByAllLedgerTypesFlagAndDisableDate > 0) {
                continue;
            }
            UserPermissionScope save = userPermissionScopeRepository.save(userPermissionScope);
            Long userPermissionScopeId = save.getId();
            saveUserPermission(saveCompletePermissionRequest, userPermissionScopeId);
        }
    }


    private void saveUserPermission(SaveCompletePermissionRequest saveCompletePermissionRequest, Long userPermissionScopeId) {
        for (FinancialUserPermissionRequest financialUserPermissionRequest : saveCompletePermissionRequest.getFinancialUserPermissionRequestList()) {
            if (!saveCompletePermissionRequest.getFinancialUserPermissionRequestList().isEmpty()) {
                checkUserPermissionRequest(saveCompletePermissionRequest);

                UserPermission userPermission = UserPermission.builder()
                        .userPermissionScopeId(daoService.findById(UserPermissionScope.class, userPermissionScopeId))
                        .financialUserIdCreator(financialUserPermissionRequest.getFinancialUserIdCreator() != null ? daoService.findById(FinancialUser.class,
                                financialUserPermissionRequest.getFinancialUserIdCreator()) : null)
                        .financialTypeActivityId(daoService.findById(FinancialActivityType.class,
                                financialUserPermissionRequest.getFinancialTypeActivityId()))
                        .financialDocumentTypeId(financialUserPermissionRequest.getFinancialDocumentTypeId() != null ?
                                daoService.findById(FinancialDocumentType.class, financialUserPermissionRequest.getFinancialDocumentTypeId()) : null)
                        .financialPeriodId(financialUserPermissionRequest.getFinancialPeriodId() != null ?
                                daoService.findById(FinancialPeriod.class, financialUserPermissionRequest.getFinancialPeriodId()) : null)
                        .effectiveDate(financialUserPermissionRequest.getEffectiveDate())
                        .disableDate(financialUserPermissionRequest.getDisableDate() == null ? null : financialUserPermissionRequest.getDisableDate())
                        .allDocumentTypeFlag(financialUserPermissionRequest.getAllDocumentTypeFlag())
                        .allFinancialPeriodFlag(financialUserPermissionRequest.getAllFinancialPeriodFlag())
                        .build();
                Long countByUserPermissionByAllDocumentTypeFlagAndEffectiveDate = userPermissionService
                        .getUserPermissionByAllDocumentTypeFlagAndEffectiveDate(
                                financialUserPermissionRequest.getUserPermissionScopeId(),
                                financialUserPermissionRequest.getFinancialUserIdCreator(),
                                financialUserPermissionRequest.getFinancialDocumentTypeId(),
                                financialUserPermissionRequest.getFinancialDocumentTypeId(),
                                financialUserPermissionRequest.getFinancialPeriodId(),
                                DateUtil.truncate(financialUserPermissionRequest.getEffectiveDate()),
                                financialUserPermissionRequest.getAllDocumentTypeFlag(),
                                financialUserPermissionRequest.getAllFinancialPeriodFlag());
                Long countByUserPermissionByAllDocumentTypeFlagAndDisableDate = userPermissionService
                        .getUserPermissionByAllDocumentTypeFlagAndDisableDate(financialUserPermissionRequest.getUserPermissionScopeId(),
                                financialUserPermissionRequest.getFinancialUserIdCreator(),
                                financialUserPermissionRequest.getFinancialDocumentTypeId(),
                                financialUserPermissionRequest.getFinancialDocumentTypeId(),
                                financialUserPermissionRequest.getFinancialPeriodId(),
                                DateUtil.truncate(financialUserPermissionRequest.getDisableDate()),
                                financialUserPermissionRequest.getAllDocumentTypeFlag(),
                                financialUserPermissionRequest.getAllFinancialPeriodFlag());
                if (countByUserPermissionByAllDocumentTypeFlagAndEffectiveDate > 0 || countByUserPermissionByAllDocumentTypeFlagAndDisableDate > 0) {
                    continue;
                }
                userPermissionRepository.save(userPermission);
            }
        }
    }

    private void checkUserPermissionRequest(SaveCompletePermissionRequest saveCompletePermissionRequest) {
        if (saveCompletePermissionRequest.getFinancialUserPermissionRequestList().stream()
                .anyMatch(i -> (i.getFinancialDocumentTypeId() == null && !i.getAllDocumentTypeFlag()) ||
                        (i.getFinancialDocumentTypeId() != null && i.getAllDocumentTypeFlag()))) {
            throw new RuleException("fin.UserPermission.misMatchOfFinancialDocumentTypeId.allDocumentTypeFlag");
        }
        if (saveCompletePermissionRequest.getFinancialUserPermissionRequestList().stream()
                .anyMatch(i -> (i.getFinancialPeriodId() == null && !i.getAllFinancialPeriodFlag()) ||
                        (i.getFinancialPeriodId() != null && i.getAllFinancialPeriodFlag()))) {
            throw new RuleException("fin.UserPermission.misMatchOfFinancialPeriodId.AllFinancialPeriodFlag");
        }
    }

    private List<FinancialSecPermissionScopeRequest> checkFinancialSecPermissionScopeRequest(SaveCompletePermissionRequest saveCompletePermissionRequest) {
        List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList = saveCompletePermissionRequest.getFinancialSecPermissionScopeRequestList();
        if (financialSecPermissionScopeRequestList.stream().anyMatch(i -> (i.getFinancialUserId().isEmpty() && i.getFinancialGroupId().isEmpty()) ||
                (!i.getFinancialUserId().isEmpty() && !i.getFinancialGroupId().isEmpty()))) {
            throw new RuleException("fin.financialSecPermissionScope.oneOfShouldBeFilled");
        }
        if (financialSecPermissionScopeRequestList.stream().anyMatch(i -> (i.getFinancialDepartmentId() == null && !i.getAllFncDepartmentFlag()) ||
                (i.getFinancialDepartmentId() != null && i.getAllFncDepartmentFlag()))) {
            throw new RuleException("fin.UserPermissionScope.misMatchOfFinancialDepartmentId.AllFncDepartmentFlag");
        }
        if (financialSecPermissionScopeRequestList.stream().anyMatch(i -> (i.getFinancialLedgerTypeId() == null && !i.getAllLedgerTypesFlag()) ||
                (i.getFinancialLedgerTypeId() != null && i.getAllLedgerTypesFlag()))) {
            throw new RuleException("fin.UserPermissionScope.misMatchOfFinancialLedgerTypeId.AllLedgerTypesFlag");
        }

        return financialSecPermissionScopeRequestList;
    }

    @Override
    public Long findUserPermissionScopeByFinancialUserId(Long financialUserId) {
        return userPermissionScopeRepository.findUserPermissionScopeByFinancialUserId(financialUserId);
    }


}



