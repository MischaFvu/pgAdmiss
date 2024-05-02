package com.webapp.pgadmiss.service.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Application.AppStatus;
import com.webapp.pgadmiss.entity.Student;
import com.webapp.pgadmiss.repository.ApplicationRepository;
import com.webapp.pgadmiss.repository.StudentRepository;
import com.webapp.pgadmiss.service.IApplicationService;

@Service
public class ApplicationServiceImpl implements IApplicationService{

    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private ApplicationRepository applicationRps;
    private StudentRepository studentRps;

    
    @Override
    public boolean saveApplication(Application application) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveApplication'");
    }

    /**
     * Generate new application
     * @param application
     * @param student
     * @return
     */
    private Application createNewApp(Application application, Student student) {
        return application;
    }

    @Override
    public boolean updateApplication(Application application) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateApplication'");
    }

    @Override
    public boolean deleteApplication(int applicationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteApplication'");
    }

    @Override
    public Application findAppById(int applicationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppById'");
    }

    @Override
    public List<Application> findAppsByStatus(AppStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppsByStatus'");
    }

}
