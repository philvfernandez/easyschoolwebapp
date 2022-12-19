package com.easybytes.easyschool.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime undatedAt;
    private String updatedBy;
}
