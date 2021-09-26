package com.schedguap.schedguap.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

public class DeadlineSource {


    @JsonProperty("_id")
    private String id;

    @JsonProperty("name")
    private String name;

    public DeadlineSource() {
    }

    public DeadlineSource(String name) {
        this.id = DigestUtils.sha256Hex(name);
        this.name = name;
    }
}
