package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialUserGroupResponse;
import ir.demisco.cfs.model.entity.FinancialUserGroup;
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
public class FinancialUserGroupGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialUserGroup.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> financialUser = root.join("financialUserId");
        Join<Object, Object> applicationUser = financialUser.join("applicationUser");

        filterContext.getJoins().putAll(CommonUtils.keyValueMap("financialUser", financialUser,
                "applicationUser", applicationUser));

        return GridDataProvider.super.getCustomRestriction(filterContext);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Map<String, Object> joins = filterContext.getJoins();
        Join<Object, Object> financialUser = (Join) joins.get("financialUser");
        Join<Object, Object> applicationUser = (Join) joins.get("applicationUser");

        return criteriaBuilder.array(
                filterContext.getPath("id"),
                applicationUser.get("id"),
                financialUser.get("id"),
                applicationUser.get("username"),
                applicationUser.get("nickName"),
                filterContext.getPath("effectiveDate"),
                filterContext.getPath("disableDate")
        );

    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return FinancialUserGroupResponse.builder()
                    .financialUserGroupId((Long) array[0])
                    .applicationUserId((Long) array[1])
                    .financialUserId((Long) array[2])
                    .userName((String) array[3])
                    .nickName((String) array[4])
                    .effectiveDate((LocalDateTime) array[5])
                    .disableDate((LocalDateTime) array[6])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getFullPaths() {
        Map<String, String> fullPaths = new HashMap<>();
        fullPaths.put("organizationId", "organizationId");
        fullPaths.put("financialGroupId", "financialGroupId");
        return fullPaths;
    }

}
