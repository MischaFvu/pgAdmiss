package com.webapp.pgadmiss.controller;

import org.springframework.web.bind.annotation.RestController;

import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Application.AppStatus;
import com.webapp.pgadmiss.service.IApplicationService;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api", method=RequestMethod.GET, produces = "application/json")
@Validated
public class ApplicationController implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    IApplicationService applicationService;

    public ApplicationController(IApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/appList")
    public String getApplications() {
        List<Application> applications = applicationService.findAppsByStatus(AppStatus.SAVE);
        return "application/application";
    }

    @PostMapping("/appCreate")
    public ResponseEntity<Application> createApplication() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findApp")
    public String getMethodName(@RequestParam String param) {
        logger.info("");
        return new String();
    }

    @PutMapping("/update/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    
    @DeleteMapping("/delete/{id}")
    public String deleteMethodName(@PathVariable String id) {
        //TODO: process DELETE request
        
        return new String();
    }

}
