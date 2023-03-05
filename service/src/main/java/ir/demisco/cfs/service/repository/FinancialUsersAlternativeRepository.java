package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialUserAlternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FinancialUsersAlternativeRepository extends JpaRepository<FinancialUserAlternative, Long> {
    @Query(value = " SELECT 1 " +
            "  FROM FNSC.FINANCIAL_USER_ALTERNATIVE UA " +
            " WHERE UA.ID IN (:userAlternativeIdList) " +
            "   AND EXISTS " +
            " (SELECT 1 " +
            "          FROM FNSC.FINANCIAL_USER_ALTERNATIVE UA_IN " +
            "         WHERE UA_IN.ID != UA.ID " +
            "           AND UA_IN.FINANCIAL_USER_ID = UA.FINANCIAL_USER_ID " +
            "           AND UA_IN.FINANCIAL_USER_ID_ALTERNATE = " +
            "               UA.FINANCIAL_USER_ID_ALTERNATE " +
            "           AND UA_IN.ORGANIZATION_ID = UA.ORGANIZATION_ID " +
            "           AND ((:disableDate BETWEEN UA_IN.EFFECTIVE_DATE AND " +
            "               NVL(UA_IN.DISABLE_DATE, SYSDATE + 10000))))  ", nativeQuery = true)
    List<Long> getFinancialUserAlternativeByIdList(List<Long> userAlternativeIdList, LocalDateTime disableDate);

    @Query(value = " SELECT 1 " +
            "  FROM FNSC.FINANCIAL_USER_ALTERNATIVE UA" +
            " WHERE UA.ORGANIZATION_ID = :organizationId " +
            "   AND UA.FINANCIAL_USER_ID = :financialUserId " +
            "   AND UA.FINANCIAL_USER_ID_ALTERNATE IN :userAlternativeIdList" +
            "   AND ((:effectiveDate BETWEEN UA.EFFECTIVE_DATE AND case" +
            "         when UA.DISABLE_DATE is null then" +
            "          SYSDATE + 10000" +
            "         else" +
            "          UA.DISABLE_DATE" +
            "       end) OR (:disableDate IS NOT NULL AND" +
            "       :disableDate BETWEEN UA.EFFECTIVE_DATE AND case" +
            "         when UA.DISABLE_DATE is null then" +
            "          SYSDATE + 10000" +
            "         else" +
            "          UA.DISABLE_DATE" +
            "       end" +
            "       ) OR (UA.EFFECTIVE_DATE BETWEEN :effectiveDate AND case" +
            "         when :disableDate is null then" +
            "          SYSDATE + 10000" +
            "         else" +
            "          :disableDate " +
            "       end)) ", nativeQuery = true)
    List<Long> getFinancialUserAlternativeByOrganizationAndEffectiveDateAndDisableDate(Long organizationId, Long financialUserId, List<Long> userAlternativeIdList, LocalDateTime effectiveDate, LocalDateTime disableDate);

    @Query(value = " select count(UA.id)" +
            "  from FNSC.FINANCIAL_USER_ALTERNATIVE UA " +
            " where UA.FINANCIAL_USER_ID = :financialUserId" +
            " and  UA.ORGANIZATION_ID = :organizationId " +
            " and UA.EFFECTIVE_DATE= :effectiveDate " +
            " and UA.FINANCIAL_USER_ID_ALTERNATE= :userAlternativeId"
            , nativeQuery = true)
    Long getFinancialUserAlternativeByEffectiveDate(Long financialUserId, Long organizationId, LocalDateTime effectiveDate, Long userAlternativeId);

    @Query(value = " select count(UA.id)" +
            "  from FNSC.FINANCIAL_USER_ALTERNATIVE UA " +
            " where UA.FINANCIAL_USER_ID = :financialUserId" +
            " and  UA.ORGANIZATION_ID = :organizationId " +
            " and UA.DISABLE_DATE = :disableDate " +
            " and UA.FINANCIAL_USER_ID_ALTERNATE= :userAlternativeId"
            , nativeQuery = true)
    Long getFinancialUserAlternativeByDisableDate(Long financialUserId, Long organizationId, LocalDateTime disableDate, Long userAlternativeId);

    @Query("select count(fua.id) " +
            "    from FinancialUserAlternative fua     " +
            "   inner join fua.financialUser fu     " +
            "   inner join fua.organization o     " +
            "   where fua.financialUser.id = :financialUserId     " +
            "     and fua.organization.id = :organizationId     " +
            "     and trunc(fua.effectiveDate) = :effectiveDate " +
            "     and fua.alternative.id = :financialUserAlternativeId ")
    Long findFinancialUserAlternativeByFinancialUserAndOrganizationAndEffectiveDate(@Param("financialUserId") Long financialUserId,
                                                                                    @Param("organizationId") Long organizationId,
                                                                                    @Param("effectiveDate") LocalDateTime effectiveDate,
                                                                                    @Param("financialUserAlternativeId") Long financialUserAlternativeId);

    @Query("select count(fua.id) " +
            "                 from FinancialUserAlternative fua    " +
            "                inner join fua.financialUser fu     " +
            "                inner join fua.organization o    " +
            "                where fua.financialUser.id = :financialUserId    " +
            "                  and fua.organization.id = :organizationId   " +
            "                  and (:disableDate is null or trunc(fua.disableDate) = :disableDate)   " +
            "                  and fua.alternative.id = :financialUserAlternativeId ")
    Long findFinancialUserAlternativeByFinancialUserAndOrganizationAndDisableDate(@Param("financialUserId") Long financialUserId,
                                                                                  @Param("organizationId") Long organizationId,
                                                                                  @Param("disableDate") LocalDateTime disableDate,
                                                                                  @Param("financialUserAlternativeId") Long financialUserAlternativeId);

    @Query("SELECT 1 " +
            "  FROM FinancialUserAlternative IN_AL " +
            " WHERE IN_AL.financialUser.id =  :mainFinancialUserId " +
            "   AND IN_AL.organization.id = :organizationId " +
            "   AND (IN_AL.disableDate is null or " +
            "       (trunc(IN_AL.effectiveDate) =  :effectiveDate OR " +
            "       trunc(IN_AL.disableDate) =  :effectiveDate) OR " +
            "       ( :effectiveDate BETWEEN trunc(IN_AL.effectiveDate)" +
            " AND trunc(IN_AL.disableDate))) ")
    Optional<FinancialUserAlternative> findFinancialUserAlternativeByOrganizationId(@Param("mainFinancialUserId") Long mainFinancialUserId,
                                                                                    @Param("effectiveDate") LocalDateTime effectiveDate,
                                                                                    @Param("organizationId") Long organizationId);

}
