package com.webapp.pgadmiss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.webapp.pgadmiss.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

}
