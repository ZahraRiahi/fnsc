package ir.demisco.cfs.service.impl;

import ir.demisco.cfs.model.dto.response.FinancialActivityTypeResponse;
import ir.demisco.cfs.model.entity.FinancialActivityType;
import ir.demisco.cloud.core.middle.service.business.api.core.GridDataProvider;
import ir.demisco.core.utils.CommonUtils;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class FinancialActivityTypeGridProvider implements GridDataProvider {
    @Override
    public Class<?> getRootEntityClass() {
        return FinancialActivityType.class;
    }

    @Override
    public Predicate getCustomRestriction(FilterContext filterContext) {
        Root<Object> root = filterContext.getRoot();
        Join<Object, Object> financialSystemSubject = root.join("financialSystemSubject");
        Join<Object, Object> financialSystem = root.join("financialSystem");

        filterContext.getJoins().putAll(CommonUtils.keyValueMap("financialSystemSubject", financialSystemSubject,
                "financialSystem", financialSystem));

        return GridDataProvider.super.getCustomRestriction(filterContext);
    }

    @Override
    public Selection<?> getCustomSelection(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        Map<String, Object> joins = filterContext.getJoins();
        Join<Object, Object> financialSystemSubject = (Join) joins.get("financialSystemSubject");
        Join<Object, Object> financialSystem = (Join) joins.get("financialSystem");

        return criteriaBuilder.array(
                filterContext.getPath("id"),
                filterContext.getPath("code"),
                filterContext.getPath("description"),
                filterContext.getPath("insertFlag"),
                filterContext.getPath("updateFlag"),
                filterContext.getPath("deleteFlag"),
                filterContext.getPath("queryFlag"),
                financialSystem.get("id"),
                financialSystem.get("description"),
                financialSystemSubject.get("id"),
                financialSystemSubject.get("description")
        );

    }


    @Override
    public List<Object> mapToDto(List<Object> resultList) {
        return resultList.stream().map((Object object) -> {
            Object[] array = (Object[]) object;
            return FinancialActivityTypeResponse.builder()
                    .financialActivityTypeId((Long) array[0])
                    .code((String) array[1])
                    .description((String) array[2])
                    .insertFlag((Boolean) array[3])
                    .updateFlag((Boolean) array[4])
                    .deleteFlag((Boolean) array[5])
                    .queryFlag((Boolean) array[6])
                    .financialSystemId((Long) array[7])
                    .financialSystemDes((String) array[8])
                    .financialSystemSubjectId((Long) array[9])
                    .financialSystemSubjectDes((String) array[10])
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, String> getFullPaths() {
        Map<String, String> fullPaths = new HashMap<>();
        fullPaths.put("financialSystemId", "financialSystem.id");
        fullPaths.put("financialSystemSubjectId", "financialSystemSubject.id");
        return fullPaths;
    }
}
