package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.ApplicationUsersResponse;
import ir.demisco.cfs.model.entity.FinancialUser;
import ir.demisco.cloud.basic.model.entity.sec.User;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;


import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationUsersGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return User.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Subquery<Long> financialUserSubQuery = filterContext
                .createSubQueryContext(Long.class).getSubqueryBuilder().build();
        Root<FinancialUser> financialUserRoot = financialUserSubQuery.from(FinancialUser.class);
        Subquery<Long> subQuery = financialUserSubQuery.select(financialUserRoot.get("id"));
        Predicate userIdCondition = criteriaBuilder
                .equal(financialUserRoot.get("applicationUser")
                        .get("id"), filterContext.getPath("id"));
        subQuery.where(userIdCondition);

        Predicate notExists = criteriaBuilder.not(criteriaBuilder.exists(subQuery));

        return criteriaBuilder.and(notExists);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();

        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("username"),
                filterContext.getPath("nickName")
        );

    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return ApplicationUsersResponse.builder()
                    .applicationUserId((Long) array[0])
                    .userName((String) array[1])
                    .nickName((String) array[2])
                    .build();
        }).collect(Collectors.toList());
    }

}
