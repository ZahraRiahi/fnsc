package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FinancialGroupRepository extends JpaRepository<FinancialGroup, Long> {
    @Query("select count(fg.id) " +
            "  from FinancialGroup fg " +
            "  left join fg.organization o " +
            " where fg.organization.id =  :organizationId " +
            "   and fg.code =  :code")
    Long findFinancialGroupByCode(@Param("organizationId") Long organizationId,
                                  @Param("code") String code);

    @Query(" select count(fg.id) " +
            "          from FinancialGroup fg " +
            "          left join fg.organization o " +
            "         where fg.organization.id =  :organizationId " +
            "           and fg.description =  :description ")
    Long findFinancialGroupByDescription(@Param("organizationId") Long organizationId,
                                         @Param("description") String description);
}
