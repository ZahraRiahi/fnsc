package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialGroupResponse;
import ir.demisco.cfs.model.entity.FinancialGroup;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import ir.demisco.cloud.core.model.security.JwtSecurityPayloadKeys;
import ir.demisco.cloud.core.security.util.SecurityHelper;
import ir.demisco.core.utils.CommonUtils;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FinancialGroupGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialGroup.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> organization = root.join("organization");
        filterContext.getJoins().putAll(CommonUtils.keyValueMap("organization", organization));
        Predicate organizationId = criteriaBuilder.equal(organization.get("id"),
                (Long) SecurityHelper.getCurrentUser().getAdditionalInformation().get(JwtSecurityPayloadKeys.ORGANIZATION_USER_ID.getValue()));
        return criteriaBuilder.and(organizationId);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Map<String, Object> joins = filterContext.getJoins();
        Join<Object, Object> organization = (Join) joins.get("organization");

        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("description"),
                organization.get("id"),
                organization.get("name")
        );

    }
    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return FinancialGroupResponse.builder()
                    .financialGroupId((Long) array[0])
                    .financialGroupCode((String) array[1])
                    .financialGroupDescription((String) array[2])
                    .organizationId((Long) array[3])
                    .organizationName((String) array[4])
                    .build();
        }).collect(Collectors.toList());
    }
}
