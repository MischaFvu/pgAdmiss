package com.webapp.pgadmiss.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.webapp.pgadmiss.dto.MessageDto;
import com.webapp.pgadmiss.entity.Message;
import com.webapp.pgadmiss.entity.User;
import com.webapp.pgadmiss.entity.Message.MessageType;
import com.webapp.pgadmiss.service.serviceImpl.UserServiceImpl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class FormSpecification {


    public static <T> Specification<T> buildSpecification(final T filter, Class<T> clazz) {
        return new Specification <T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // Initialize an empty AND condition
                Predicate predicate = criteriaBuilder.conjunction();

                for (Field field : clazz.getDeclaredFields()) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(filter);
                        if (value != null) {
                            String fieldName = field.getName();
                            predicate = criteriaBuilder.and(predicate,
                                    criteriaBuilder.equal(root.get(fieldName), value));
                        }
                    } catch (IllegalAccessException e) {
                        // Handle reflection access exception
                        e.printStackTrace();
                    }
                }

                return predicate;
            }
        };
    }

    
    

}
