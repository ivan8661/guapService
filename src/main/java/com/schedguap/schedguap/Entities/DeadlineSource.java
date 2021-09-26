package com.schedguap.schedguap.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.apache.commons.codec.digest.DigestUtils;

public class DeadlineSource {


    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    private String id;

    @JsonProperty("name")
    private String name;

    public DeadlineSource() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof DeadlineSource)) return false;
        DeadlineSource that = (DeadlineSource) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public DeadlineSource(String name) {
        this.id = DigestUtils.sha256Hex(name);
        this.name = name;
    }
}
