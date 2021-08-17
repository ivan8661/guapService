package com.schedguap.schedguap.Entities.DatabaseEntities;

import javax.persistence.Entity;

import GetGraphQL.SearchableField;
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
    @SearchableField
    private String name;

    @Column(name="professor_university_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer professorUniversityId;


    public Professor() {
    }

    public Professor(String id, String fullName, Integer professorUniversityId) {
        this.id = id;
        this.name = fullName;
        this.professorUniversityId = professorUniversityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String fullName) {
        this.name = fullName;
    }

    public Integer getProfessorUniversityId() {
        return professorUniversityId;
    }

    public void setProfessorUniversityId(Integer professorUniversityId) {
        this.professorUniversityId = professorUniversityId;
    }
}
