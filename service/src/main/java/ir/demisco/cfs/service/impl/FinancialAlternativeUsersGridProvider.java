package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialAlternativeUsersResponse;
import ir.demisco.cfs.model.entity.FinancialUserAlternative;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import ir.demisco.core.utils.CommonUtils;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class FinancialAlternativeUsersGridProvider implements GridDataProvider {
    public Class<?> getRootEntityClass() {
        return FinancialUserAlternative.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> alternative = root.join("alternative");
        Join<Object, Object> applicationUser = alternative.join("applicationUser");
        Join<Object, Object> financialUser = root.join("financialUser");
        Join<Object, Object> applicationUserId = financialUser.join("applicationUser");
        Join<Object, Object> organization = root.join("organization");

        filterContext.getJoins().putAll(CommonUtils.keyValueMap("alternative", alternative,
                "applicationUser", applicationUser, "financialUser", financialUser, "applicationUserId",
                applicationUserId, "organization", organization));

        return GridDataProvider.super.getCustomRestriction(filterContext);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Map<String, Object> joins = filterContext.getJoins();
        Join<Object, Object> alternative = (Join) joins.get("alternative");
        Join<Object, Object> applicationUser = (Join) joins.get("applicationUser");
        Join<Object, Object> financialUser = (Join) joins.get("financialUser");
        Join<Object, Object> applicationUserId = (Join) joins.get("applicationUserId");
        Join<Object, Object> organization = (Join) joins.get("organization");

        return criteriaBuilder.array(
                filterContext.getPath("id"),
                financialUser.get("id"),
                applicationUserId.get("username"),
                applicationUserId.get("nickName"),
                alternative.get("id"),
                applicationUser.get("username"),
                applicationUser.get("nickName"),
                filterContext.getPath("effectiveDate"),
                filterContext.getPath("disableDate"),
                organization.get("id"),
                organization.get("name"));
    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return FinancialAlternativeUsersResponse.builder()
                    .financialAlternativeId((Long) array[0])
                    .mainUserId((Long) array[1])
                    .mainUserName((String) array[2])
                    .mainNickName((String) array[3])
                    .financialUserIdAlternative((Long) array[4])
                    .alternativeUserName((String) array[5])
                    .alternativeNickName((String) array[6])
                    .effectiveDate((LocalDateTime) array[7])
                    .disableDate((LocalDateTime) array[8])
                    .organizationId((Long) array[9])
                    .organizationName((String) array[10])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getFullPaths() {
        Map<String, String> fullPaths = new HashMap<>();
        fullPaths.put("mainFinancialUserId", "financialUser.id");
        fullPaths.put("organizationId", "organization.id");
        return fullPaths;
    }
}
