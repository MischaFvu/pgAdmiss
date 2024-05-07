package com.webapp.pgadmiss.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.pgadmiss.service.IApprovalService;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping(path = "/approval", method=RequestMethod.GET, produces = "application/json")
public class ApprovalController implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ApprovalController.class); 

    @Autowired
    IApprovalService approvalService;

    public ApprovalController(IApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    
}
