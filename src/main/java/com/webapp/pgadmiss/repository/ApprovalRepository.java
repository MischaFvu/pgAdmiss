package com.webapp.pgadmiss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.webapp.pgadmiss.entity.Approval;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer>, JpaSpecificationExecutor<Approval> {

    Optional<Approval> findById(int approvalId);

    boolean deleteApprovalByApprovalId(int approvalId);
}

