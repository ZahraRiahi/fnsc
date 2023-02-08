package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialLedgerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialLedgerTypeRepository extends JpaRepository<FinancialLedgerType, Long> {

}
