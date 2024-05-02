package com.webapp.pgadmiss.repository;

import org.springframework.data.jpa.domain.Specification;

import com.webapp.pgadmiss.entity.Application;

public class FormSpecification {

    public static Specification<Application> filterBySpec(String name, String value) {
        return (root, query, criteriaBuilder) -> {
            if (value == null || name == null) {
                return null;
            }
            return criteriaBuilder.like(root.get(name), "%" + value + "%");
        };
    }

    
    

}
