package com.webapp.pgadmiss.mapper;

import com.webapp.pgadmiss.dto.ApprovalDto;
import com.webapp.pgadmiss.entity.Approval;

public class ApprovalMapper {

    public static ApprovalDto mapToApprovalDto(Approval approval, ApprovalDto approvalDto) {
        return approvalDto;
    }

    public static Approval mapToApproval(ApprovalDto approvalDto, Approval approval) {
        return approval;
    }

}
