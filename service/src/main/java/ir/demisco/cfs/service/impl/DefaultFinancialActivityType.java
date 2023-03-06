package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialActivityTypeRequest;
import ir.demisco.cfs.model.entity.FinancialActivityType;
import ir.demisco.cfs.model.entity.FinancialSystem;
import ir.demisco.cfs.model.entity.FinancialSystemSubject;
import ir.demisco.cfs.service.api.FinancialActivityTypeService;
import ir.demisco.cfs.service.repository.FinancialActivityTypeRepository;
import ir.demisco.cloud.basic.service.api.DaoService;
import ir.demisco.cloud.core.middle.exception.RuleException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultFinancialActivityType implements FinancialActivityTypeService {
    private final FinancialActivityTypeRepository financialActivityTypeRepository;
    private final DaoService daoService;

    public DefaultFinancialActivityType(FinancialActivityTypeRepository financialActivityTypeRepository, DaoService daoService) {
        this.financialActivityTypeRepository = financialActivityTypeRepository;
        this.daoService = daoService;
    }

    @Override
    public Boolean saveActivityType(List<FinancialActivityTypeRequest> financialActivityTypeRequestList) {
        financialActivityTypeRequestList.forEach(i -> {
                    if (getFinancialActivityTypeByFinancialSystem(i.getFinancialSystemId(), i.getFinancialSystemSubjectId(), i.getCode()).isPresent()) {
                        throw new RuleException("کد تکراری است ");
                    } else {
                        FinancialActivityType financialActivityType = FinancialActivityType.builder()
                                .code(i.getCode())
                                .description(i.getDescription())
                                .insertFlag(i.getInsertFlag())
                                .updateFlag(i.getUpdateFlag())
                                .deleteFlag(i.getDeleteFlag())
                                .queryFlag(i.getQueryFlag())
                                .financialSystemSubject(daoService.findById(FinancialSystemSubject.class, i.getFinancialSystemSubjectId()))
                                .financialSystem(daoService.findById(FinancialSystem.class, i.getFinancialSystemId()))
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
}
