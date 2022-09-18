package ir.demisco.cfs.service.repository;

import ir.demisco.cfs.model.entity.FinancialUserAlternative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    List<Object[]> getFinancialUserAlternativeByUserIdAndFlgAndOrgan(Object mainFinancialUser,Long mainFinancialUserId,Long flgAllOrganizations,Long organizationId);
}
