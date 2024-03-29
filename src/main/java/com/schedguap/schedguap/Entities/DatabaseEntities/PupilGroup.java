package com.schedguap.schedguap.Entities.DatabaseEntities;


import GetGraphQL.SearchableField;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PupilGroup {


    @Id
    @JsonProperty("_id")
    @Column(name="id")
    private String id;

    @Column(name="name")
    @SearchableField
    private String name;

    @Column(name="group_university_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer universityGroupId;

    @ManyToMany
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Lesson> lessons;

    public PupilGroup() {
    }


    public PupilGroup(String id, String name, Integer universityGroupId) {
        this.id = id;
        this.name = name;
        this.universityGroupId = universityGroupId;
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

    public void setName(String name) {
        this.name = name;
    }


    public Integer getUniversityGroupId() {
        return universityGroupId;
    }

    public void setUniversityGroupId(Integer universityGroupId) {
        this.universityGroupId = universityGroupId;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }


}
