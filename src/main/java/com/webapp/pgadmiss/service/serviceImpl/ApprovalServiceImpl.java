package com.webapp.pgadmiss.service.serviceImpl;

import java.util.List;

import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Filter;
import org.springframework.stereotype.Service;

import com.webapp.pgadmiss.entity.Approval;
import com.webapp.pgadmiss.entity.Approval.ApprovalStatus;
import com.webapp.pgadmiss.service.IApprovalService;

@Service
public class ApprovalServiceImpl implements IApprovalService {

    @Override
    public boolean saveApproval(Approval approval) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveApproval'");
    }

    @Override
    public boolean updateApproval(Approval approval) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateApproval'");
    }

    @Override
    public boolean deleteApproval(Approval approval) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteApproval'");
    }

    @Override
    public Approval findApprovalByAppId(int appId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findApprovalByAppId'");
    }

    @Override
    public List<Approval> findApprovalsByStatus(ApprovalStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findApprovalsByStatus'");
    }

    @Override
    public List<Approval> findAllApprovals(Filter filter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllApprovals'");
    }

}
