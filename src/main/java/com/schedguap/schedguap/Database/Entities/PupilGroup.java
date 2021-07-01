package com.schedguap.schedguap.Database.Entities;


import javax.persistence.*;
import java.util.Set;

@Entity
public class PupilGroup {


    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="group_university_id")
    private Integer universityGroupId;

    @ManyToMany
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
