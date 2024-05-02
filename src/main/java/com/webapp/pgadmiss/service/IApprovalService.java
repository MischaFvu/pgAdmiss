package com.webapp.pgadmiss.service;

import java.util.List;

import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Filter;

import com.webapp.pgadmiss.entity.Approval;
import com.webapp.pgadmiss.entity.Approval.ApprovalStatus;

public interface IApprovalService {

    /**
     * 
     * @param approval
     * @return
     */
    boolean saveApproval(Approval approval);

    /**
     * 
     * @param approval
     * @return
     */
    boolean updateApproval(Approval approval);

    /**
     * 
     * @param approval
     * @return
     */
    boolean deleteApproval(Approval approval);

    /**
     * 
     * @param appId
     * @return
     */
    Approval findApprovalByAppId(int appId);

    /**
     * 
     * @param status
     * @return
     */
    List<Approval> findApprovalsByStatus(ApprovalStatus status);

    List<Approval> findAllApprovals(Filter filter);

}
