package com.webapp.pgadmiss.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.webapp.pgadmiss.entity.Application;

import jakarta.transaction.Transactional;

@Repository
public interface ApplicationRepository extends JpaSpecificationExecutor<Application>, JpaRepository<Application, UUID> {
    
    Optional<Application> findByAppId(UUID appId);

    @Transactional
    @Modifying
    boolean deleteAppByAppId(UUID appId);



}
