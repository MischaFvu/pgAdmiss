package com.webapp.pgadmiss.service;

import java.util.List;

import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Application.AppStatus;

public interface IApplicationService {


    boolean saveApplication(Application application);

    boolean updateApplication(Application application);
    boolean deleteApplication(int applicationId);
    Application findAppById(int applicationId);

    List<Application> findAppsByStatus(AppStatus status);
}
