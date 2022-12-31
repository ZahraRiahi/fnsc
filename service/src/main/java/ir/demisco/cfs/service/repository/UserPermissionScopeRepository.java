package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.UserPermissionScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPermissionScopeRepository extends JpaRepository<UserPermissionScope, Long> {
    @Query(value = " select ups.id                       user_permission_scope_id," +
            "       ups.financial_user_id," +
            "       ups.financial_group_id," +
            "       ups.financial_department_id," +
            "       ups.department_id," +
            "       ups.financial_ledger_type_id," +
            "       ups.all_ledger_types_flag," +
            "       ups.all_fnc_department_flag," +
            "       ups.effective_date," +
            "       ups.disable_date," +
            "       fu.application_user_id," +
            "       au.nick_name                 financial_user_name," +
            "       fg.code                      financial_group_code," +
            "       fg.description               financial_group_description," +
            "       dep.code                     department_code," +
            "       dep.name                     department_name," +
            "       fd.code                      financial_department_code," +
            "       fd.name                      financial_department_name," +
            "       flt.description              ledger_type_description" +
            "  from fnsc.user_permission_scope ups " +
            " inner join org.organization org " +
            "    on org.id = ups.organization_id " +
            "  left outer join fnsc.financial_user fu" +
            "    on fu.id = ups.financial_user_id" +
            "  left outer join sec.application_user au " +
            "    on au.id = fu.application_user_id " +
            "  left outer join fnsc.financial_group fg " +
            "    on fg.id = ups.financial_group_id " +
            " inner join org.department dep " +
            "    on dep.id = ups.department_id " +
            "  left outer join fndc.financial_department fd " +
            "    on fd.id = ups.financial_department_id " +
            "  left outer join fndc.financial_ledger_type flt " +
            "    on flt.id = ups.financial_ledger_type_id " +
            " where (nvl(:filterMode, 1) = 1 or" +
            "       (:filterMode = 2 and ups.effective_date <= TRUNC(sysdate)) or " +
            "       (:filterMode = 3 and ups.effective_date > TRUNC(sysdate)) or " +
            "       (:filterMode = 4 and " +
            "       nvl(ups.disable_date, TRUNC(sysdate)) < TRUNC(sysdate)) or " +
            "       (:filterMode = 5 and" +
            "       nvl(ups.disable_date, TRUNC(sysdate)) >= TRUNC(sysdate)) or " +
            "       (:filterMode = 6 and ups.disable_date is null) or " +
            "       (:filterMode = 7 and ups.disable_date is not null))     " +
            "   and (ups.organization_id = :organizationId) " +
            "  and  (:financialUser is null or ups.financial_user_id =:financialUserId ) " +
            "  and  (:financialGroup is null or ups.financial_group_id =:financialGroupId ) " +
            "   and (nvl(:allFinancialDepartmentFlg, 0) = " +
            "       nvl(ups.all_fnc_department_flag, 0))" +
            "   and (nvl(:allFinancialLedgersFlg, 0) = " +
            "       nvl(ups.all_ledger_types_flag, 0)) "
            , nativeQuery = true)
    List<Object[]> findByUserPermissionScopeAndOrgAndAllFinancialLedgersFlg(Long filterMode, Long organizationId,
                                                                         Object financialUser, Long financialUserId, Object financialGroup, Long financialGroupId, Boolean allFinancialDepartmentFlg,
                                                                         Boolean allFinancialLedgersFlg);
}