package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.request.FinancialSecurityFilterRequest;
import ir.demisco.cfs.model.dto.response.FinancialSecurityOutputResponse;
import ir.demisco.cfs.service.api.FinancialSecurityService;
import ir.demisco.cfs.service.repository.FinancialActivityTypeRepository;
import ir.demisco.cloud.core.middle.exception.RuleException;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import org.hibernate.jpa.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class DefaultFinancialSecurity implements FinancialSecurityService {
    private final FinancialActivityTypeRepository financialActivityTypeRepository;
    private final EntityManager entityManager;
//    private final FinancialSecurityService financialSecurityService;

    public DefaultFinancialSecurity(FinancialActivityTypeRepository financialActivityTypeRepository, EntityManager entityManager) {
        this.financialActivityTypeRepository = financialActivityTypeRepository;
        this.entityManager = entityManager;
//        this.financialSecurityService = financialSecurityService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public FinancialSecurityOutputResponse hasPermission(FinancialSecurityFilterRequest financialSecurityFilterRequest, Long organizationId) {
        if (financialSecurityFilterRequest.getUserId() == null) {
            throw new RuleException("لطفا شناسه ی کاربر را وارد نمایید.");
        }
        if (financialSecurityFilterRequest.getDepartmentId() == null) {
            throw new RuleException("لطفا شناسه ی شعبه را وارد نمایید.");
        }
        if (financialSecurityFilterRequest.getActivityCode() == null) {
            throw new RuleException("لطفا کد نوع فعالیت را وارد نمایید.");
        }
        if (financialSecurityFilterRequest.getInputFromConfigFlag() == null) {
            throw new RuleException("لطفا فلگ تنظیمات را وارد نمایید.");
        }
        List<Object[]> financialDocumentItemList = financialActivityTypeRepository.getFinancialActivityTypeByActivityCode(financialSecurityFilterRequest.getActivityCode());
        FinancialSecurityOutputResponse financialSecurityOutputResponse = new FinancialSecurityOutputResponse();
        if (financialDocumentItemList.size() != 0 && financialDocumentItemList.get(0)[0] == null) {
            financialSecurityOutputResponse.setPermissionMessage("اشکال در واکشی اطلاعات امنیت سیستم");
            financialSecurityOutputResponse.setHasPermissionStatus(2L);
        } else {
            int resultSet = resultSet(financialSecurityFilterRequest);
            if (resultSet == 0) {
                if (financialSecurityOutputResponse.getPermissionMessage() == null) {
                    financialSecurityOutputResponse.setHasPermissionStatus(0L);
                    return financialSecurityOutputResponse;
                } else {
                    throw new RuleException(financialSecurityOutputResponse.getPermissionMessage());
                }
            } else if (resultSet > 1) {
                financialSecurityOutputResponse.setHasPermissionStatus(2L);
                throw new RuleException(financialSecurityOutputResponse.getPermissionMessage());
            } else {
                financialSecurityOutputResponse.setHasPermissionStatus(1L);
                return financialSecurityOutputResponse;
            }
        }
        return financialSecurityOutputResponse;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int resultSet(FinancialSecurityFilterRequest financialSecurityFilterRequest) {
        List<Object> resultList = entityManager.createNativeQuery(
                " select * from table(fnsc.pkg_financial_security.GET_PERMISSION(p_organization_id => ?1," +
                        "                                                       p_activity_code => ?2," +
                        "                                                       p_financial_period_id => ?3," +
                        "                                                       p_financial_document_type_id => ?4," +
                        "                                                       p_creator_user => ?5," +
                        "                                                       p_financial_department_id => ?6," +
                        "                                                       p_financial_ledger_type_id => ?7," +
                        "                                                       p_department_id => ?8," +
                        "                                                       p_user_id => ?9))")
                .setParameter(1, SecurityHelper.getCurrentUser().getOrganizationId())
                .setParameter(2, financialSecurityFilterRequest.getActivityCode())
                .setParameter(3, new TypedParameterValue(StandardBasicTypes.LONG, financialSecurityFilterRequest.getFinancialPeriodId()))
                .setParameter(4, new TypedParameterValue(StandardBasicTypes.LONG, financialSecurityFilterRequest.getDocumentTypeId()))
                .setParameter(5, new TypedParameterValue(StandardBasicTypes.LONG, financialSecurityFilterRequest.getCreatorUserId()))
                .setParameter(6, new TypedParameterValue(StandardBasicTypes.LONG, financialSecurityFilterRequest.getFinancialDepartmentId()))
                .setParameter(7, new TypedParameterValue(StandardBasicTypes.LONG, financialSecurityFilterRequest.getFinancialLedgerId()))
                .setParameter(8, new TypedParameterValue(StandardBasicTypes.LONG, financialSecurityFilterRequest.getDepartmentId()))
                .setParameter(9, new TypedParameterValue(StandardBasicTypes.LONG, financialSecurityFilterRequest.getUserId()))
                .getResultList();

        return Integer.parseInt(resultList.get(0).toString());
    }
}
