package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialSecUserPermissionScopeResponse;
import ir.demisco.cfs.model.entity.UserPermission;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import ir.demisco.core.utils.CommonUtils;
import org.springframework.stereotype.Component;


import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
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
        Join<Object, Object> financialPeriod = root.join("financialPeriodId", JoinType.LEFT);
        Join<Object, Object> financialDocumentType = root.join("financialDocumentTypeId", JoinType.LEFT);

        filterContext.getJoins().putAll(CommonUtils.keyValueMap("financialActivityType", financialActivityType,
                "SystemSubject", financialSystemSubject, "financialUserCreator", financialUserIdCreator, "financialPeriod",
                financialPeriod, "financialDocumentType", financialDocumentType));

        return GridDataProvider.super.getCustomRestriction(filterContext);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Map<String, Object> joins = filterContext.getJoins();
        Join<Object, Object> financialActivityType = (Join) joins.get("financialActivityType");
        Join<Object, Object> financialSystemSubject = (Join) joins.get("SystemSubject");
        Join<Object, Object> financialUserIdCreator = (Join) joins.get("financialUserCreator");
        Join<Object, Object> financialPeriod = (Join) joins.get("financialPeriod");
        Join<Object, Object> financialDocumentType = (Join) joins.get("financialDocumentType");

        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("userPermissionScopeId.id"),
                financialUserIdCreator.get("id"),
                financialActivityType.get("id"),
                financialDocumentType.get("id"),
                financialPeriod.get("id"),
                filterContext.getPath("effectiveDate"),
                filterContext.getPath("disableDate"),
                filterContext.getPath("allDocumentTypeFlag"),
                filterContext.getPath("allFinancialPeriodFlag"),
                financialActivityType.get("code"),
                financialActivityType.get("description"),
                financialSystemSubject.get("code"),
                financialSystemSubject.get("description"),
                financialDocumentType.get("description"),
                financialPeriod.get("description")
        );

    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return FinancialSecUserPermissionScopeResponse.builder()
                    .userPermissionId((Long) array[0])
                    .userPermissionScopeId((Long) array[1])
                    .financialUserCreatorId((Long) array[2])
                    .financialActivityTypeId((Long) array[3])
                    .financialDocumentTypeId((Long) array[4])
                    .financialPeriodId((Long) array[5])
                    .effectiveDate((LocalDateTime) array[6])
                    .disableDate((LocalDateTime) array[7])
                    .allDocumentTypeFlag((Long) array[8])
                    .allFinancialPeriodFlag((Long) array[9])
                    .activityTypeCode((String) array[10])
                    .activityTypeDescription((String) array[11])
                    .subSystemCode((String) array[12])
                    .subSystemDescription((String) array[13])
                    .documentTypeDescription((String) array[14])
                    .financialPeriodDescription((String) array[15])
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
