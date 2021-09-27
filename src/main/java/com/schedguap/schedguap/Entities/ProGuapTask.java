package com.schedguap.schedguap.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.text.ParseException;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "user_id",
        "datecreate",
        "dateupdate",
        "name",
        "description",
        "type",
        "tt_name",
        "public",
        "semester",
        "markpoint",
        "reportRequired",
        "url",
        "ordernum",
        "expulsionLine",
        "plenty",
        "harddeadline",
        "grid",
        "subject",
        "subject_name",
        "hash",
        "filename",
        "semester_name",
        "type_name",
        "status",
        "curPoints",
        "status_name"
})

public class ProGuapTask implements Serializable {

    @JsonProperty("id")
    private String id;
    @JsonProperty("datecreate")
    private String datecreate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("markpoint")
    private String markpoint;
    @JsonProperty("reportRequired")
    private String reportRequired;
    @JsonProperty("url")
    private String url;
    @JsonProperty("harddeadline")
    private String hardDeadline;
    @JsonProperty("subject_name")
    private String subjectName;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("semester_name")
    private String semesterName;
    @JsonProperty("type_name")
    private String typeName;
    @JsonProperty("curPoints")
    private String curPoints;
    @JsonProperty("status_name")
    private String statusName;


    public String getId() {
        return id;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMarkpoint() {
        try {
            return Integer.parseInt(markpoint);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public Boolean getReportRequired() {
        try {
            return Integer.parseInt(markpoint) != 0;
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public String getUrl() {
        return url;
    }

    public String getHardDeadline() {
        return hardDeadline;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getFilename() {
        return filename;
    }

    public Integer getCurPoints() {
        try {
            return Integer.parseInt(curPoints);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    public String getStatusName() {
        return statusName;
    }

    @JsonIgnore
    public DeadlineSource getSource() {
        return new DeadlineSource(subjectName);
    }

    public String getTypeName() {
        return typeName;
    }
}