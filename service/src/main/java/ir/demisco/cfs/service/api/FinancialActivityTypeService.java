package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialActivityTypeRequest;
import ir.demisco.cfs.model.entity.FinancialActivityType;

import java.util.List;
import java.util.Optional;

public interface FinancialActivityTypeService {
    Boolean saveActivityType(List<FinancialActivityTypeRequest> financialActivityTypeRequestList);

    Optional<FinancialActivityType> getFinancialActivityTypeByFinancialSystem(Long financialSystemId,
                                                                               Long financialSystemSubjectId,
                                                                               String code);
}
