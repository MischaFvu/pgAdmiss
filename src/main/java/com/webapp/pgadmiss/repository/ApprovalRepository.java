package com.webapp.pgadmiss.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.webapp.pgadmiss.entity.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, UUID>, JpaSpecificationExecutor<Approval> {

    Approval findByApprovalId(UUID approvalId);

    boolean deleteApprovalByApprovalId(UUID approvalId);
}

