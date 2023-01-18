package com.easybytes.easyschool.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Component("easyschoolProps")
@Data
@ConfigurationProperties(prefix = "easyschool")
@Validated
public class EasySchoolProps {

    @Min(value=5, message="must be between 5 and 25")
    @Max(value=25, message = "must be between 5 and 25")
    private int pageSize;
    private Map<String, String> contact;
    private List<String> branches;
}
