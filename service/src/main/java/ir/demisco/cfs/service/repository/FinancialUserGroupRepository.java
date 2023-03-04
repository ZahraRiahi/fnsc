package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialUserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FinancialUserGroupRepository extends JpaRepository<FinancialUserGroup, Long> {
    @Query(value = " SELECT 1 " +
            "              FROM FNSC.FINANCIAL_USER_GROUP T " +
            "             WHERE T.ID = :financialUserGroupId " +
            "             and (T.DISABLE_DATE IS NOT NULL " +
            "                OR :disableDate < T.EFFECTIVE_DATE) ", nativeQuery = true)
    Long findByFinancialUserGroupByIdAndDisableDate(Long financialUserGroupId, LocalDateTime disableDate);


    @Query(value = "select fug from  FinancialUserGroup fug where fug.financialGroupId.id in (:financialGroupId)")
    List<FinancialUserGroup> findByFinancialGroupId(List<Long> financialGroupId);

    @Query("select count(fug.id) " +
            "  from FinancialUserGroup fug " +
            " inner join fug.financialUserId fu " +
            " inner join fug.financialGroupId fg " +
            "  left join fug.organizationId o " +
            " where fug.financialUserId.id =  :financialUserId " +
            "   and fug.financialGroupId.id=  :financialGroupId " +
            "   and trunc(fug.effectiveDate) =  :effectiveDate " +
            "   and fug.organizationId.id =  :organizationId")
    Long findFinancialUserGroupByEffectiveDate(@Param("financialUserId") Long financialUserId,
                                               @Param("financialGroupId") Long financialGroupId,
                                               @Param("effectiveDate") LocalDateTime effectiveDate,
                                               @Param("organizationId") Long organizationId);

    @Query("select count(fug.id) " +
            "  from FinancialUserGroup fug " +
            " inner join fug.financialUserId fu " +
            " inner join fug.financialGroupId fg " +
            "  left join fug.organizationId o " +
            " where fug.financialUserId.id =  :financialUserId " +
            "   and fug.financialGroupId.id=  :financialGroupId " +
            "   and trunc(fug.disableDate) =  :disableDate " +
            "   and fug.organizationId.id =  :organizationId")
    Long findFinancialUserGroupByDisableDate(@Param("financialUserId") Long financialUserId,
                                             @Param("financialGroupId") Long financialGroupId,
                                             @Param("disableDate") LocalDateTime disableDate,
                                             @Param("organizationId") Long organizationId);

    @Query("select 1 " +
            "                              from FinancialUserGroup fg " +
            "                             where fg.financialGroupId.id =  :groupId " +
            "                               and fg.financialUserId.id =  :financialUserId " +
            "                               and ((fg.disableDate is null and " +
            "                                    :disableDate is null) or " +
            "                                   ( :effectiveDate between " +
            "                                   trunc(fg.effectiveDate) and trunc(fg.disableDate)) or " +
            "                                   ( :disableDate between trunc(fg.effectiveDate) and " +
            "                                   trunc(fg.disableDate)))")
    Optional<FinancialUserGroup> findFinancialUserGroupByEffectiveDateAndDisableDate(@Param("groupId") Long groupId,
                                                                                     @Param("financialUserId") Long financialUserId,
                                                                                     @Param("effectiveDate") LocalDateTime effectiveDate,
                                                                                     @Param("disableDate") LocalDateTime disableDate);


}
