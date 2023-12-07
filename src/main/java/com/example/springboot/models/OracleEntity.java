package com.example.springboot.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class OracleEntity implements Serializable {
    @Id
    private Long id;

    public String name;
    public String age;
    // Other fields and methods
    public OracleEntity() {

    }
    public OracleEntity(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

