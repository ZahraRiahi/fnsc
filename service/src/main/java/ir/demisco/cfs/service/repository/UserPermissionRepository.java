package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

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
            "   where up.userPermissionScopeId.id =  :userPermissionScopeId " +
            "     and ( :financialUserIdCreator is null or up.financialUserIdCreator.id =  :financialUserIdCreator )" +
            "     and up.financialTypeActivityId.id =  :financialActivityTypeId " +
            "     and (:financialDocumentTypeId is null or up.financialDocumentTypeId.id =  :financialDocumentTypeId) " +
            "     and (:financialPeriodId is null or up.financialPeriodId.id =  :financialPeriodId) " +
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
            "   where up.userPermissionScopeId.id =  :userPermissionScopeId " +
            "     and (:financialUserIdCreator is null or up.financialUserIdCreator.id =  :financialUserIdCreator )" +
            "     and up.financialTypeActivityId.id =  :financialActivityTypeId " +
            "     and (:financialDocumentTypeId is null or up.financialDocumentTypeId.id =  :financialDocumentTypeId) " +
            "     and (:financialPeriodId is null or up.financialPeriodId.id =  :financialPeriodId) " +
            "     and (:disableDate is null or trunc(up.disableDate) =  :disableDate )" +
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

    @Query("select count(u.id)   " +
            "  from UserPermission u   " +
            " where u.financialTypeActivityId.id = :financialActivityTypeId")
    Long findUserPermissionByFinancialActivityTypeId(@Param("financialActivityTypeId") Long financialActivityTypeId);

    @Query("select count(u.id) " +
            "  from  UserPermission u " +
            " where u.financialUserIdCreator.id= :financialUserIdCreator")
    Long findUserPermissionByFinancialUserId(@Param("financialUserIdCreator") Long financialUserIdCreator);


}
