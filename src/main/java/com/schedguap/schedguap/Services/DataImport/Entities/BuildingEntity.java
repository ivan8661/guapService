package com.schedguap.schedguap.Services.DataImport.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuildingEntity {

    @JsonProperty("ItemId")
    private Integer id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("ItemOrd")
    private Integer university;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUniversity() {
        return university;
    }

    public void setUniversity(Integer university) {
        this.university = university;
    }
}
