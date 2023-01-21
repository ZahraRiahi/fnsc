package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FinancialUserGroupRepository extends JpaRepository<FinancialUserGroup, Long> {
    @Query(value = " SELECT 1 " +
            "              FROM FNSC.FINANCIAL_USER_GROUP T " +
            "             WHERE T.ID = :financialUserGroupId " +
            "             and (T.DISABLE_DATE IS NOT NULL " +
            "                OR :disableDate < T.EFFECTIVE_DATE) ", nativeQuery = true)
    Long findByFinancialUserGroupByIdAndDisableDate(Long financialUserGroupId, LocalDateTime disableDate);

    @Query(value = " SELECT APPLICATION_USER_ID, FINANCIAL_USER_ID, USER_NAME, NICK_NAME" +
            "  FROM FNSC.FINANCIAL_USER_GROUP UG " +
            " INNER JOIN FNSC.FINANCIAL_USER FU " +
            "    ON FU.ID = UG.FINANCIAL_USER_ID " +
            " INNER JOIN SEC.APPLICATION_USER AU " +
            "    ON AU.ID = FU.APPLICATION_USER_ID " +
            " WHERE UG.ORGANIZATION_ID = :organizationId " +
            "   AND UG.FINANCIAL_GROUP_ID = :financialGroupId ", nativeQuery = true)
    List<Object[]> getFinancialUserGroupByOrganizationId(Long organizationId,Long financialGroupId);

    @Query(value = "select fug from  FinancialUserGroup fug where fug.financialGroupId.id in (:financialGroupId)")
    List<FinancialUserGroup> findByFinancialGroupId(List<Long> financialGroupId);


}
