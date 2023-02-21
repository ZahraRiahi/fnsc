package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

    @Query(value = " select count(up.id) " +
            "  from fnsc.user_permission up " +
            " where up.user_permission_scope_id = :userPermissionScopeId " +
            "   and up.financial_activity_type_id = :financialActivityTypeId " +
            " and  (:financialUserIdCreator IS NULL OR " +
            "          up.financial_user_id_creator = :financialUserIdCreatorId )" +
            " and  (:financialDocumentType IS NULL OR " +
            "         up.financial_document_type_id = :financialDocumentTypeId )" +
            "   and up.all_document_type_flag = :allDocumentTypeFlag " +
            "   and up.all_financial_priod_flag = :allFinancialPeriodFlag " +
            "   and up.effective_date = trunc(:effectiveDate)" +
            " and  (:financialPeriod IS NULL OR " +
            "       up.financial_period_id = :financialPeriodId) "
            , nativeQuery = true)
    Long getPermissionByScopeIdAndFlgAndEffectiveDate(Long userPermissionScopeId, Long financialActivityTypeId, Object financialUserIdCreator, Long financialUserIdCreatorId,
                                                      Object financialDocumentType, Long financialDocumentTypeId, Long allDocumentTypeFlag, Long allFinancialPeriodFlag, LocalDateTime effectiveDate,
                                                      Object financialPeriod, Long financialPeriodId);

    @Query(value = " select count(up.id)" +
            "  from fnsc.user_permission up" +
            " where up.user_permission_scope_id = :userPermissionScopeId" +
            "   and up.financial_activity_type_id = :financialActivityTypeId" +
            "  and up.disable_date = NVL(:disableDate, up.disable_date)  " +
            "   and  (:financialUserIdCreator IS NULL OR " +
            "             up.financial_user_id_creator = :financialUserIdCreatorId )" +
            "   and  (:financialDocumentType IS NULL OR " +
            "           up.financial_document_type_id = :financialDocumentTypeId )" +
            "   and  (:financialPeriod IS NULL OR " +
            "          up.financial_period_id = :financialPeriodId)" +
            "   and up.all_document_type_flag = :allDocumentTypeFlag" +
            "   and up.all_financial_priod_flag = :allFinancialPeriodFlag"
            , nativeQuery = true)
    Long getPermissionByScopeIdAndFlgAndDisableDate(Long userPermissionScopeId, Long financialActivityTypeId, LocalDateTime disableDate, Object financialUserIdCreator, Long financialUserIdCreatorId,
                                                    Object financialDocumentType, Long financialDocumentTypeId, Object financialPeriod, Long financialPeriodId, Long allDocumentTypeFlag, Long allFinancialPeriodFlag);

    @Query(" select up.id from UserPermission up " +
            " join UserPermissionScope ups on ups.id=up.userPermissionScopeId.id " +
            " where ups.id=:userPermissionScopeId")
    List<Long> getUserPermissionByUserPermissionScopeId(Long userPermissionScopeId);

    @Query("  select count(up.id) " +
            "    from UserPermission up " +
            "   inner join up.userPermissionScopeId ups " +
            "    left join up.financialUserIdCreator fu " +
            "   inner join up.financialTypeActivityId fat " +
            "    left join up.financialDocumentTypeId fdt " +
            "    left join up.financialPeriodId fp " +
            "   where up.userPermissionScopeId =  :userPermissionScopeId " +
            "     and up.financialUserIdCreator =  :financialUserIdCreator " +
            "     and up.financialTypeActivityId =  :financialActivityTypeId " +
            "     and up.financialDocumentTypeId =  :financialDocumentTypeId " +
            "     and up.financialPeriodId =  :financialPeriodId " +
            "     and trunc(up.effectiveDate) =  :effectiveDate " +
            "     and up.allDocumentTypeFlag =  :allDocumentTypeFlag " +
            "     and up.allFinancialPeriodFlag =  :allFinancialPeriodFlag")
    Long findUserPermissionByAllDocumentTypeFlagAndEffectiveDate(@Param("userPermissionScopeId") Long userPermissionScopeId,
                                                                 @Param("financialUserIdCreator") Long financialUserIdCreator,
                                                                 @Param("financialActivityTypeId") Long financialActivityTypeId,
                                                                 @Param("financialDocumentTypeId") Long financialDocumentTypeId,
                                                                 @Param("financialPeriodId") Long financialPeriodId,
                                                                 @Param("effectiveDate") LocalDateTime effectiveDate,
                                                                 @Param("allDocumentTypeFlag") Boolean allDocumentTypeFlag,
                                                                 @Param("allFinancialPeriodFlag") Boolean allFinancialPeriodFlag);

    @Query("  select count(up.id) " +
            "    from UserPermission up " +
            "   inner join up.userPermissionScopeId ups " +
            "    left join up.financialUserIdCreator fu " +
            "   inner join up.financialTypeActivityId fat " +
            "    left join up.financialDocumentTypeId fdt " +
            "    left join up.financialPeriodId fp " +
            "   where up.userPermissionScopeId =  :userPermissionScopeId " +
            "     and up.financialUserIdCreator =  :financialUserIdCreator " +
            "     and up.financialTypeActivityId =  :financialActivityTypeId " +
            "     and up.financialDocumentTypeId =  :financialDocumentTypeId " +
            "     and up.financialPeriodId =  :financialPeriodId " +
            "     and trunc(up.disableDate) =  :disableDate " +
            "     and up.allDocumentTypeFlag =  :allDocumentTypeFlag " +
            "     and up.allFinancialPeriodFlag =  :allFinancialPeriodFlag")
    Long findUserPermissionByAllDocumentTypeFlagAndDisableDate(@Param("userPermissionScopeId") Long userPermissionScopeId,
                                                               @Param("financialUserIdCreator") Long financialUserIdCreator,
                                                               @Param("financialActivityTypeId") Long financialActivityTypeId,
                                                               @Param("financialDocumentTypeId") Long financialDocumentTypeId,
                                                               @Param("financialPeriodId") Long financialPeriodId,
                                                               @Param("disableDate") LocalDateTime disableDate,
                                                               @Param("allDocumentTypeFlag") Boolean allDocumentTypeFlag,
                                                               @Param("allFinancialPeriodFlag") Boolean allFinancialPeriodFlag);
}
