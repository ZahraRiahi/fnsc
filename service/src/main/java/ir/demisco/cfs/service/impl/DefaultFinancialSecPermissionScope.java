package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.*;
import ir.demisco.cfs.model.dto.response.FinancialSecPermissionScopeOutputResponse;
import ir.demisco.cfs.model.entity.FinancialDepartment;
import ir.demisco.cfs.model.entity.FinancialLedgerType;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cfs.model.entity.UserPermissionScope;
import ir.demisco.cfs.service.api.FinancialSecPermissionScopeService;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.repository.*;
import ir.demisco.cloud.basic.model.entity.org.Department;
import ir.demisco.cloud.basic.organization.repository.jpa.DepartmentRepository;
import ir.demisco.cloud.basic.organization.repository.jpa.OrganizationRepository;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.coyote.http11.Constants.a;

@Service
public class DefaultFinancialSecPermissionScope implements FinancialSecPermissionScopeService {
    private final UserPermissionScopeRepository userPermissionScopeRepository;
    private final EntityManager entityManager;
    private final FinancialUserRepository financialUserRepository;
    private final FinancialLedgerTypeRepository financialLedgerTypeRepository;
    private final FinancialDepartmentRepository financialDepartmentRepository;
    private final DepartmentRepository departmentRepository;
    private final OrganizationRepository organizationRepository;
    private final UserPermissionRepository userPermissionRepository;
    private final UserPermissionService userPermissionService;
    private final DaoService daoService;

    public DefaultFinancialSecPermissionScope(UserPermissionScopeRepository userPermissionScopeRepository,
                                              EntityManager entityManager, FinancialUserRepository financialUserRepository,
                                              FinancialLedgerTypeRepository financialLedgerTypeRepository,
                                              FinancialDepartmentRepository financialDepartmentRepository,
                                              DepartmentRepository departmentRepository,
                                              OrganizationRepository organizationRepository,
                                              UserPermissionRepository userPermissionRepository,
                                              UserPermissionService userPermissionService,
                                              DaoService daoService) {
        this.userPermissionScopeRepository = userPermissionScopeRepository;
        this.entityManager = entityManager;
        this.financialUserRepository = financialUserRepository;
        this.financialLedgerTypeRepository = financialLedgerTypeRepository;
        this.financialDepartmentRepository = financialDepartmentRepository;
        this.departmentRepository = departmentRepository;
        this.organizationRepository = organizationRepository;
        this.userPermissionRepository = userPermissionRepository;
        this.userPermissionService = userPermissionService;
        this.daoService = daoService;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public List<FinancialSecPermissionScopeOutputResponse> getFinancialSecPermissionScope(FinancialSecPermissionScopeInputRequest financialSecPermissionScopeInputRequest) {
        Object financialUser = null;
        if (financialSecPermissionScopeInputRequest.getFinancialGroupId() == null && financialSecPermissionScopeInputRequest.getFinancialUserId() == null) {
            throw new RuleException("باید یکی از دو شناسه ی گروه یا شناسه ی کاربر مالی پر شده باشد");
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
                SecurityHelper.getCurrentUser().getOrganizationId(), financialUser, financialSecPermissionScopeInputRequest.getFinancialUserId(), financialGroup,
                financialSecPermissionScopeInputRequest.getFinancialGroupId(), financialSecPermissionScopeInputRequest.getAllFinancialDepartmentFlg(), financialSecPermissionScopeInputRequest.getAllFinancialLedgersFlg());
        return controlFinancialAccountObject.stream().map(objects -> FinancialSecPermissionScopeOutputResponse.builder().userPermissionScopeId(objects[0] == null ? null : Long.parseLong(objects[0].toString()))
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
                .ledgerTypeDescription(objects[18] == null ? null : objects[18].toString())
                .build()).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialSecPermissionScope(FinancialSecPermissionScopeInputModelRequest financialSecPermissionScopeInputModelRequest) {
        List<Long> userPermissionCount = userPermissionRepository.getUserPermissionByUserPermissionScopeId(financialSecPermissionScopeInputModelRequest.getUserPermissionScopeId().get(0));
        if (!userPermissionCount.isEmpty()) {
            throw new RuleException("امکان حذف این محدوده ی دسترسی کاربر وجود ندارد.");
        }
        financialSecPermissionScopeInputModelRequest.getUserPermissionScopeId().forEach(aLong -> userPermissionScopeRepository.deleteById(aLong));
        return true;
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean setDisableDate(PermissionScopeInputModelRequest permissionScopeInputModelRequest) {
        entityManager.createNativeQuery(" update FNSC.USER_PERMISSION_SCOPE T " +
                        "   set   T.DISABLE_DATE = :disableDate " +
                        "   WHERE T.DISABLE_DATE IS NULL " +
                        " and  T.ID in (:permissionScopeIdList) ")
                .setParameter("disableDate", permissionScopeInputModelRequest.getDisableDate())
                .setParameter("permissionScopeIdList", permissionScopeInputModelRequest.getPermissionScopeIdList()).executeUpdate();
        return true;
    }

    @Override
    public Long getUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(Long financialUserId, Long financialLedgerTypeId,
                                                                           Long financialDepartmentId,
                                                                           Long departmentId, LocalDateTime effectiveDate,
                                                                           Long organizationId, Boolean allLedgerTypesFlag,
                                                                           Boolean allFncDepartmentFlag, Long financialGroupId) {
        return userPermissionScopeRepository.findUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(financialUserId, financialLedgerTypeId,
                financialDepartmentId,
                departmentId,
                effectiveDate,
                organizationId, allLedgerTypesFlag,
                allFncDepartmentFlag, financialGroupId);
    }

    @Override
    public Long getUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(Long financialUserId, Long financialLedgerTypeId, Long financialDepartmentId, Long departmentId, LocalDateTime disableDate, Long organizationId, Boolean allLedgerTypesFlag, Boolean allFncDepartmentFlag, Long financialGroupId) {
        return userPermissionScopeRepository.findUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(financialUserId, financialLedgerTypeId,
                financialDepartmentId,
                departmentId, disableDate,
                organizationId,
                allLedgerTypesFlag,
                allFncDepartmentFlag, financialGroupId);
    }

//    @Override
//    public Boolean saveFinancialSecPermissionScope(SaveCompletePermissionRequest saveCompletePermissionRequest) {
//        List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList = getFinancialSecPermissionScopeRequests(saveCompletePermissionRequest);
//        List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequests = new ArrayList<>();
//        for (FinancialSecPermissionScopeRequest permissionScopeRequest : financialSecPermissionScopeRequestList) {
//            if (!permissionScopeRequest.getFinancialUserId().isEmpty()) {
//                for (Long financialUserId : permissionScopeRequest.getFinancialUserId()) {
//                    UserPermissionScope userPermissionScope = UserPermissionScope.builder()
//                            .financialUser(daoService.findById(FinancialUser.class, permissionScopeRequest.getFinancialUserId()))
//                            .financialLedgerType(daoService.findById(FinancialLedgerType.class, permissionScopeRequest.getFinancialLedgerTypeId()))
//                            .financialDepartment(daoService.findById(FinancialDepartment.class, permissionScopeRequest.getFinancialDepartmentId()))
//                            .department(daoService.findById(Department.class, permissionScopeRequest.getFinancialDepartmentId()))
//                            .effectiveDate(permissionScopeRequest.getEffectiveDate())
//                            .disableDate(permissionScopeRequest.getDisableDate())
//                            .allLedgerTypesFlag(permissionScopeRequest.getAllLedgerTypesFlag())
//                            .allFncDepartmentFlag(permissionScopeRequest.getAllFncDepartmentFlag())
//                            .financialGroup(permissionScopeRequest.getFinancialGroupId())
//                            .build();
//                    Long countByUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate = getUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate(permissionScopeRequest.getFinancialUserId(),
//                            permissionScopeRequest.getFinancialLedgerTypeId(), permissionScopeRequest.getFinancialDepartmentId(),
//                            permissionScopeRequest.getDepartmentId(),
//                            permissionScopeRequest.getEffectiveDate(),
//                            permissionScopeRequest.getOrganizationId(),
//                            permissionScopeRequest.getAllLedgerTypesFlag(),
//                            permissionScopeRequest.getAllFncDepartmentFlag(),
//                            permissionScopeRequest.getFinancialGroupId());
//                    Long countByUserPermissionScopeByAllLedgerTypesFlagAndDisableDate = getUserPermissionScopeByAllLedgerTypesFlagAndDisableDate(permissionScopeRequest.getFinancialUserId(),
//                            permissionScopeRequest.getFinancialLedgerTypeId(), permissionScopeRequest.getFinancialDepartmentId(),
//                            permissionScopeRequest.getDepartmentId(),
//                            permissionScopeRequest.getDisableDate(),
//                            permissionScopeRequest.getOrganizationId(),
//                            permissionScopeRequest.getAllLedgerTypesFlag(),
//                            permissionScopeRequest.getAllFncDepartmentFlag(),
//                            permissionScopeRequest.getFinancialGroupId());
//                    if (countByUserPermissionScopeByAllLedgerTypesFlagAndEffectiveDate > 0 || countByUserPermissionScopeByAllLedgerTypesFlagAndDisableDate > 0) {
//                        continue;
//                    }
//                }
//            }
//            else if(){
//
//            }
//        }
//        return true;
//    }

    private List<FinancialSecPermissionScopeRequest> getFinancialSecPermissionScopeRequests(SaveCompletePermissionRequest saveCompletePermissionRequest) {
        List<FinancialSecPermissionScopeRequest> financialSecPermissionScopeRequestList = saveCompletePermissionRequest.getFinancialSecPermissionScopeRequestList();
        if (financialSecPermissionScopeRequestList.stream().anyMatch(i -> !i.getFinancialGroupId().isEmpty() && !i.getFinancialUserId().isEmpty())) {
            throw new RuleException("یکی از دو شناسه گروه و یا شناسه کاربر مالی باید  پر شده باشد ");
        }
        return financialSecPermissionScopeRequestList;
    }

}



