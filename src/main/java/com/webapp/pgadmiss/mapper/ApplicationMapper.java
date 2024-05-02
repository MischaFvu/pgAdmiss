package com.webapp.pgadmiss.mapper;

import com.webapp.pgadmiss.dto.ApplicationDto;
import com.webapp.pgadmiss.entity.Application;

public class ApplicationMapper {

    public static ApplicationDto mapToApplicationDto(Application application, ApplicationDto applicationDto) {
        return applicationDto;
    }

    public static Application mapToApplication(ApplicationDto applicationDto, Application application) {
        return application;
    }
}
