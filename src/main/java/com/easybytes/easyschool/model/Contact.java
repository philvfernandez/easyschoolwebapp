package com.easybytes.easyschool.model;

import lombok.Data;

/*
@Data annotation is provided by Lombok library which generates getter, setter, equals(), hashCode(), toString()
methods and Constructor at compile time.  This makes code short and clean.
 */

@Data
public class Contact {

    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}
