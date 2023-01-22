package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialUserAlternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FinancialUsersAlternativeRepository extends JpaRepository<FinancialUserAlternative, Long> {
    @Query(value = " SELECT AL.ID                          FINANCIAL_ALTERNATIVE_ID," +
            "       AL.FINANCIAL_USER_ID           MAIN_USER_ID," +
            "       AP.USER_NAME                   MAIN_USER_NAME," +
            "       AP.NICK_NAME                   MAIN_NICK_NAME," +
            "       AL.FINANCIAL_USER_ID_ALTERNATE," +
            "       AP_AL.USER_NAME                ALTERNATIVE_USER_NAME," +
            "       AP_AL.NICK_NAME                ALTERNATIVE_NICK_NAME," +
            "       AL.EFFECTIVE_DATE," +
            "       AL.DISABLE_DATE," +
            "       AL.ORGANIZATION_ID," +
            "       ORG.NAME                       ORGANIZATION_NAME " +
            "  FROM FNSC.FINANCIAL_USER_ALTERNATIVE AL" +
            " INNER JOIN FNSC.FINANCIAL_USER AL_USER" +
            "    ON AL_USER.ID = AL.FINANCIAL_USER_ID_ALTERNATE" +
            " INNER JOIN SEC.APPLICATION_USER AP_AL" +
            "    ON AP_AL.ID = AL_USER.APPLICATION_USER_ID" +
            " INNER JOIN FNSC.FINANCIAL_USER M_USER" +
            "    ON M_USER.ID = AL.FINANCIAL_USER_ID" +
            " INNER JOIN SEC.APPLICATION_USER AP" +
            "    ON AP.ID = M_USER.APPLICATION_USER_ID" +
            " INNER JOIN ORG.ORGANIZATION ORG" +
            "    ON ORG.ID = AL.ORGANIZATION_ID" +
            " WHERE (:mainFinancialUser IS NULL OR" +
            "       AL.FINANCIAL_USER_ID = :mainFinancialUserId)" +
            "   AND (NVL(:flgAllOrganizations, 0) = 1 OR" +
            "       AL.ORGANIZATION_ID = :organizationId)"
            , nativeQuery = true)
    List<Object[]> getFinancialUserAlternativeByUserIdAndFlgAndOrgan(Object mainFinancialUser, Long mainFinancialUserId, Long flgAllOrganizations, Long organizationId);

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
}
