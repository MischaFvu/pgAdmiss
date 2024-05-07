package com.webapp.pgadmiss.service;

import java.util.List;
import java.util.UUID;

import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Application.AppStatus;

public interface IApplicationService {


    boolean saveApplication(Application application);

    boolean updateApplication(Application application);
    boolean deleteApplication(UUID applicationId);
    Application findAppById(UUID applicationId);

    List<Application> findAppsByStatus(AppStatus status);
}
