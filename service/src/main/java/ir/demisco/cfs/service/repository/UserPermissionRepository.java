package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

    @Query(value = " select count(up.id)" +
            "  from fnsc.user_permission up" +
            " where up.user_permission_scope_id = :userPermissionScopeId" +
            "   and up.financial_activity_type_id = :financialActivityTypeId" +
            "   and up.effective_date = trunc(:effectiveDate)" +
            "   and up.financial_user_id_creator = :financialUserIdCreator" +
            "   and up.financial_document_type_id = :financialDocumentTypeId" +
            "   and up.financial_period_id = :financialPeriodId" +
            "   and up.all_document_type_flag = :allDocumentTypeFlag" +
            "   and up.all_financial_priod_flag = :allFinancialPeriodFlag"
            , nativeQuery = true)
    Long getPermissionByScopeIdAndFlgAndEffectiveDate(Long userPermissionScopeId, Long financialActivityTypeId, LocalDateTime effectiveDate, Long financialUserIdCreator,
                                      Long financialDocumentTypeId, Long financialPeriodId, Long allDocumentTypeFlag, Long allFinancialPeriodFlag);

    @Query(value = " select count(up.id)" +
            "  from fnsc.user_permission up" +
            " where up.user_permission_scope_id = :userPermissionScopeId" +
            "   and up.financial_activity_type_id = :financialActivityTypeId" +
            "   and up.disable_date = trunc(:disableDate)" +
            "   and up.financial_user_id_creator = :financialUserIdCreator" +
            "   and up.financial_document_type_id = :financialDocumentTypeId" +
            "   and up.financial_period_id = :financialPeriodId" +
            "   and up.all_document_type_flag = :allDocumentTypeFlag" +
            "   and up.all_financial_priod_flag = :allFinancialPeriodFlag"
            , nativeQuery = true)
    Long getPermissionByScopeIdAndFlgAndDisableDate(Long userPermissionScopeId, Long financialActivityTypeId, LocalDateTime disableDate, Long financialUserIdCreator,
                                      Long financialDocumentTypeId, Long financialPeriodId, Long allDocumentTypeFlag, Long allFinancialPeriodFlag);

}
