package com.easybytes.easyschool.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity {

    @Id //Primary Key
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING) //convert enum into varchar at runtime.
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL;
    }

}
