package com.easybytes.easyschool.model;

import lombok.Data;

import java.lang.reflect.Type;
@Data
public class Holiday extends BaseEntity {

    private String day;
    private String reason;
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL;
    }

}
