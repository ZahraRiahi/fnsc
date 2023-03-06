package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FinancialActivityTypeRepository extends JpaRepository<FinancialActivityType, Long> {
    @Query(" select fa.id,fa.description,fa.financialSystem.id,fa.financialSystemSubject.id from FinancialActivityType fa " +
            " where fa.code= :activityCode")
    List<Object[]> getFinancialActivityTypeByActivityCode(String activityCode);

    @Query("SELECT 1 " +
            "  FROM FinancialActivityType FNAT " +
            " WHERE FNAT.financialSystem.id =  :financialSystemId " +
            "   AND FNAT.financialSystemSubject.id =  :financialSystemSubjectId " +
            "   AND FNAT.code =  :code")
    Optional<FinancialActivityType> findFinancialActivityTypeByFinancialSystem(@Param("financialSystemId") Long financialSystemId,
                                                                               @Param("financialSystemSubjectId") Long financialSystemSubjectId,
                                                                               @Param("code") String code);
}
