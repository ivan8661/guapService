package com.schedguap.schedguap.Entities.DatabaseEntities;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
public class Professor {


    @Id
    @Column(name="id", nullable = false, unique = true)
    @JsonProperty("_id")
    private String id;

    @Column(name="full_name")
    @JsonProperty("name")
    private String fullName;

    @Column(name="professor_university_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
