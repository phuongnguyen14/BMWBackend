package com.example.mockspring.specification;

import com.example.mockspring.entity.Car;
import com.example.mockspring.form.car.CarFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CarSpecification {
    private static final String NAME = "name";

    public static Specification<Car> buildWhere(CarFilterForm form) {
        if (form == null) {
            return null;
        }
        Specification<Car> wherename = new SpecificationImpl(NAME, form.getSearch());
        return Specification.where(wherename);
    }

    @AllArgsConstructor
    public static class SpecificationImpl implements Specification<Car> {
        private String key;
        private Object value;


        @Override
        public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null){
                return null;
            }
            switch (key){
                case NAME:
                    return criteriaBuilder.like(root.get("name"),"%" + value + "%");
            }
            return null;
        }
    }

}