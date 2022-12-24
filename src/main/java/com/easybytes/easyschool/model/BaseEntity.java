package com.easybytes.easyschool.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Slf4j
@Data
@MappedSuperclass //all entities in web application which extend this class, treat them as columns
                  // in dynamic code that spring jpa will generate.
public class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
