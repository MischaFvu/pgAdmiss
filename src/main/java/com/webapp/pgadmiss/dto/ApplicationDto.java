package com.webapp.pgadmiss.dto;

import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Student;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationDto extends Application{

    public ApplicationDto(Application application, Student student) {
        
    }


}
