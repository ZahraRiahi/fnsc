package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialSystemSubjectResponse;
import ir.demisco.cfs.model.entity.FinancialSystemSubject;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Selection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FinancialSystemSubjectGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialSystemSubject.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        return GridDataProvider.super.getCustomRestriction(filterContext);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();

        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("description")
        );

    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return FinancialSystemSubjectResponse.builder()
                    .financialSystemSubjectId((Long) array[0])
                    .code((String) array[1])
                    .description((String) array[2])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getFullPaths() {
        Map<String, String> fullPaths = new HashMap<>();
        fullPaths.put("financialSystemId", "financialSystem.id");
        return fullPaths;
    }

}
