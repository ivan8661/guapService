package com.schedguap.schedguap.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.schedguap.schedguap.Entities.ProGuapTask;

import java.io.Serializable;
import java.util.List;

public class DeadlinesResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<ProGuapTask> tasks;

    public List<ProGuapTask> getTasks() {
        return tasks;
    }
}

