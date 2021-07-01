package com.schedguap.schedguap.Services.DataImport.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProffesorEntity {

    @JsonProperty("ItemId")
    private Integer id;
    @JsonProperty("Name")
    private String name;


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
}
