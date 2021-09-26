package com.schedguap.schedguap.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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

public class ProGuapTask  {

    @JsonProperty("id")
    private String id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("datecreate")
    private String datecreate;
    @JsonProperty("dateupdate")
    private String dateupdate;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private String type;
    @JsonProperty("tt_name")
    private String ttName;
    @JsonProperty("public")
    private String _public;
    @JsonProperty("semester")
    private String semester;
    @JsonProperty("markpoint")
    private String markpoint;
    @JsonProperty("reportRequired")
    private String reportRequired;
    @JsonProperty("url")
    private String url;
    @JsonProperty("ordernum")
    private String ordernum;
    @JsonProperty("expulsionLine")
    private String expulsionLine;
    @JsonProperty("plenty")
    private String plenty;
    @JsonProperty("harddeadline")
    private String harddeadline;
    @JsonProperty("grid")
    private String grid;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("subject_name")
    private String subjectName;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("filename")
    private String filename;
    @JsonProperty("semester_name")
    private String semesterName;
    @JsonProperty("type_name")
    private String typeName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("curPoints")
    private String curPoints;
    @JsonProperty("status_name")
    private String statusName;


    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public String getDateupdate() {
        return dateupdate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getTtName() {
        return ttName;
    }

    public String get_public() {
        return _public;
    }

    public String getSemester() {
        return semester;
    }

    public String getMarkpoint() {
        return markpoint;
    }

    public String getReportRequired() {
        return reportRequired;
    }

    public String getUrl() {
        return url;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public String getExpulsionLine() {
        return expulsionLine;
    }

    public String getPlenty() {
        return plenty;
    }

    public String getHarddeadline() {
        return harddeadline;
    }

    public String getGrid() {
        return grid;
    }

    public String getSubject() {
        return subject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getHash() {
        return hash;
    }

    public String getFilename() {
        return filename;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getStatus() {
        return status;
    }

    public String getCurPoints() {
        return curPoints;
    }

    public String getStatusName() {
        return statusName;
    }
}