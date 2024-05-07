package com.webapp.pgadmiss.util;

import com.webapp.pgadmiss.entity.Application;
import com.webapp.pgadmiss.entity.Major;

public class ApplicationUtil {

    public String generateAppNo(Application app) {
        String year = String.valueOf(app.getAppPeriod());
        Major major = app.getMajor();
        return year + major.getMajorNo() + getAppSeq();

    }
    public int getAppSeq() {
        return 0;

    
    }

}
