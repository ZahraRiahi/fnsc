package ir.demisco.cfs.service.api;

import ir.demisco.cfs.model.dto.request.FinancialGroupInputModelRequest;

public interface FinancialGroupService {
    Boolean deleteFinancialGroup(FinancialGroupInputModelRequest financialGroupInputModelRequest);

}
