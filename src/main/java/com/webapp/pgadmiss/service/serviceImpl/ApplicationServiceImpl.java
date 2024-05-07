package com.webapp.pgadmiss.service.serviceImpl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Application.AppStatus;
import com.webapp.pgadmiss.entity.Student;
import com.webapp.pgadmiss.repository.ApplicationRepository;
import com.webapp.pgadmiss.repository.StudentRepository;
import com.webapp.pgadmiss.service.IApplicationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationServiceImpl implements IApplicationService{

    @Autowired
    private ApplicationRepository appRepository;
    
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
    public boolean deleteApplication(UUID applicationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteApplication'");
    }

    @Override
    public Application findAppById(UUID applicationId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppById'");
    }

    @Override
    public List<Application> findAppsByStatus(AppStatus status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppsByStatus'");
    }

}
