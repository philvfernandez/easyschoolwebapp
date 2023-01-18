package com.easybytes.easyschool.audit;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EasySchoolInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> easyMap = new HashMap<String, String>();
        easyMap.put("App Name", "EasySchool");
        easyMap.put("App Description", "Easy School Web Application for Students and Admin");
        easyMap.put("App Version", "1.0.0");
        easyMap.put("Contact Email", "info@eazyschool.com");
        easyMap.put("Contact Phone", "(555) 123 4567");
        builder.withDetail("easyschool-info", easyMap);
    }
}
