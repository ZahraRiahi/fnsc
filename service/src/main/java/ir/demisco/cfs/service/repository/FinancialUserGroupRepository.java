package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface FinancialUserGroupRepository extends JpaRepository<FinancialUserGroup, Long> {
    @Query(value = " SELECT 1 " +
            "              FROM FNSC.FINANCIAL_USER_GROUP T " +
            "             WHERE T.ID = :financialUserGroupId " +
            "             and (T.DISABLE_DATE IS NOT NULL " +
            "                OR :disableDate < T.EFFECTIVE_DATE) ", nativeQuery = true)
    Long findByFinancialUserGroupByIdAndDisableDate(Long financialUserGroupId, LocalDateTime disableDate);
}
