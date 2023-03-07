package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialActivityTypeRequest;
import ir.demisco.cfs.model.entity.FinancialActivityType;
import ir.demisco.cfs.model.entity.FinancialSystem;
import ir.demisco.cfs.model.entity.FinancialSystemSubject;
import ir.demisco.cfs.service.api.FinancialActivityTypeService;
import ir.demisco.cfs.service.api.UserPermissionService;
import ir.demisco.cfs.service.repository.FinancialActivityTypeRepository;
import ir.demisco.cfs.service.repository.UserPermissionRepository;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultFinancialActivityType implements FinancialActivityTypeService {
    private final FinancialActivityTypeRepository financialActivityTypeRepository;
    private final DaoService daoService;
    private final UserPermissionService userPermissionService;


    public DefaultFinancialActivityType(FinancialActivityTypeRepository financialActivityTypeRepository, DaoService daoService, UserPermissionService userPermissionService, UserPermissionRepository userPermissionRepository) {
        this.financialActivityTypeRepository = financialActivityTypeRepository;
        this.daoService = daoService;
        this.userPermissionService = userPermissionService;
    }

    @Override
    public Boolean saveActivityType(List<FinancialActivityTypeRequest> financialActivityTypeRequestList) {
        financialActivityTypeRequestList.forEach((FinancialActivityTypeRequest financialActivityTypeObject) -> {
                    if (getFinancialActivityTypeByFinancialSystem(financialActivityTypeObject.getFinancialSystemId(),
                            financialActivityTypeObject.getFinancialSystemSubjectId(),
                            financialActivityTypeObject.getCode()).isPresent()) {
                        throw new RuleException("کد تکراری است ");
                    } else {
                        FinancialActivityType financialActivityType = FinancialActivityType.builder()
                                .code(financialActivityTypeObject.getCode())
                                .description(financialActivityTypeObject.getDescription())
                                .insertFlag(financialActivityTypeObject.getInsertFlag())
                                .updateFlag(financialActivityTypeObject.getUpdateFlag())
                                .deleteFlag(financialActivityTypeObject.getDeleteFlag())
                                .queryFlag(financialActivityTypeObject.getQueryFlag())
                                .financialSystemSubject(daoService.findById(FinancialSystemSubject.class,
                                        financialActivityTypeObject.getFinancialSystemSubjectId()))
                                .financialSystem(daoService.findById(FinancialSystem.class,
                                        financialActivityTypeObject.getFinancialSystemId()))
                                .build();
                        financialActivityTypeRepository.save(financialActivityType);

                    }
                }
        );

        return true;
    }

    @Override
    public Optional<FinancialActivityType> getFinancialActivityTypeByFinancialSystem(Long financialSystemId, Long financialSystemSubjectId, String code) {
        return financialActivityTypeRepository.findFinancialActivityTypeByFinancialSystem(financialSystemId, financialSystemSubjectId, code);
    }

    @Override
    @Transactional(rollbackOn = Throwable.class)
    public Boolean deleteFinancialActivityType(Long financialActivityTypeId) {
        Long userPermissionByFinancialActivityTypeId = userPermissionService.getUserPermissionByFinancialActivityTypeId(financialActivityTypeId);
        if (userPermissionByFinancialActivityTypeId > 0L) {
            throw new RuleException("فعالیت  مورد نظر در دسترسی های کاربران استفاده شده است");
        } else {
            financialActivityTypeRepository.deleteById(financialActivityTypeId);
        }
        return true;
    }
}
