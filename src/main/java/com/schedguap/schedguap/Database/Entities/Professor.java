package com.schedguap.schedguap.Database.Entities;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.Instant;


@Entity
public class Professor {


    @Id
    @Column(name="id", nullable = false, unique = true)
    @JsonProperty("_id")
    private String id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="professor_university_id")
    private Integer professorUniversityId;


    public Professor() {
    }

    public Professor(String id, String fullName, Integer professorUniversityId) {
        this.id = id;
        this.fullName = fullName;
        this.professorUniversityId = professorUniversityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getProfessorUniversityId() {
        return professorUniversityId;
    }

    public void setProfessorUniversityId(Integer professorUniversityId) {
        this.professorUniversityId = professorUniversityId;
    }
}
