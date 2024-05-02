package com.webapp.pgadmiss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.webapp.pgadmiss.entity.Application;

import jakarta.transaction.Transactional;

@Repository
public interface ApplicationRepository extends JpaSpecificationExecutor<Application>, JpaRepository<Application, Integer> {
    
    Optional<Application> findByAppId(int appId);

    @Transactional
    @Modifying
    boolean deleteAppByAppId(int appId);



}
