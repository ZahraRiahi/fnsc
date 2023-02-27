package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialGroupInputModelRequest;
import ir.demisco.cfs.model.dto.request.FinancialGroupRequest;

import java.util.List;

public interface FinancialGroupService {
    Boolean deleteFinancialGroup(FinancialGroupInputModelRequest financialGroupInputModelRequest);

     Boolean saveFinancialGroup(List<FinancialGroupRequest> financialGroupRequestList);

    Long getFinancialGroupByCode(Long organizationId,
                                 String code);

    Long getFinancialGroupByDescription(Long organizationId,
                                        String description);


}
