package com.example.mockspring.specification;
import com.example.mockspring.entity.Account;
import com.example.mockspring.utils.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountSpecification {

    public static Specification<Account> buildWhere(String search) {
        Specification<Account> where = null;

        if (!StringUtils.isEmpty(search)) {
            search = Utils.formatSearch(search);
            CustomSpecification username = new CustomSpecification("username", search);
            where = Specification.where(username);
        }

        return where;
    }

}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Account> {

    @NonNull
    private String field;
    @NonNull
    private Object value;

    @Override
    public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("username")) {
            return criteriaBuilder.like(root.get("username"), "%" + value.toString() + "%");
        }

        return null;
    }

}