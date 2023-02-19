package ir.demisco.cfs.service.component;

import ir.demisco.cfs.model.dto.response.FinancialSecUserPermissionScopeResponse;
import ir.demisco.cfs.model.entity.UserPermission;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import ir.demisco.core.utils.CommonUtils;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserPermissionGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return UserPermission.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> financialActivityType = root.join("financialTypeActivityId");
        Join<Object, Object> financialSystemSubject = financialActivityType.join("financialSystemSubject");
        Join<Object, Object> financialUserIdCreator = root.join("financialUserIdCreator", JoinType.LEFT);

        filterContext.getJoins().putAll(CommonUtils.keyValueMap("financialActivityType", financialActivityType,
                "SystemSubject", financialSystemSubject, "financialUserCreator", financialUserIdCreator));

        return GridDataProvider.super.getCustomRestriction(filterContext);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Map<String, Object> joins = filterContext.getJoins();
        Join<Object, Object> financialActivityType = (Join) joins.get("financialActivityType");
        Join<Object, Object> financialSystemSubject = (Join) joins.get("SystemSubject");
        Join<Object, Object> financialUserIdCreator = (Join) joins.get("financialUserCreator");

        return criteriaBuilder.array(
                filterContext.getPath("userPermissionScopeId.id"),
                financialUserIdCreator.get("id"),
                financialActivityType.get("id"),
                filterContext.getPath("financialDocumentTypeId.id"),
                filterContext.getPath("financialPeriodId.id"),
                filterContext.getPath("effectiveDate"),
                filterContext.getPath("disableDate"),
                filterContext.getPath("allDocumentTypeFlag"),
                filterContext.getPath("allFinancialPeriodFlag"),
                financialActivityType.get("code"),
                financialActivityType.get("description"),
                financialSystemSubject.get("code"),
                financialSystemSubject.get("description"),
                filterContext.getPath("id")
        );

    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map(object -> {
            Object[] array = (Object[]) object;
            return FinancialSecUserPermissionScopeResponse.builder()
                    .userPermissionScopeId((Long) array[0])
                    .financialUserCreatorId((Long) array[1])
                    .financialActivityTypeId((Long) array[2])
                    .financialDocumentTypeId((Long) array[3])
                    .financialPeriodId((Long) array[4])
                    .effectiveDate((LocalDateTime) array[5])
                    .disableDate((LocalDateTime) array[6])
                    .allDocumentTypeFlag((Long) array[7])
                    .allFinancialPeriodFlag((Long) array[8])
                    .activityTypeCode((String) array[9])
                    .activityTypeDescription((String) array[10])
                    .subSystemCode((String) array[11])
                    .subSystemDescription((String) array[12])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getFullPaths() {
        Map<String, String> fullPaths = new HashMap<>();
        fullPaths.put("userPermissionScopeId", "userPermissionScopeId.id");
        return fullPaths;
    }

}
