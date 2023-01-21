package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialDocumentType;
import ir.demisco.cfs.model.entity.FinancialGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialGroupRepository extends JpaRepository<FinancialGroup, Long> {
}
