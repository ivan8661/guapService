package com.schedguap.schedguap.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.schedguap.schedguap.Entities.DatabaseEntities.Subject;
import com.schedguap.schedguap.Entities.Repositories.SubjectRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "title",
        "type",
        "description",
        "subject",
        "created",
        "date",
        "status",
        "curPoints",
        "markpoint",
        "file",
        "reportRequired"
})

public class Deadline {
    class File {
        @JsonProperty("_id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("url")
        private String url;

        public File(String name, String url) {
            this.id = DigestUtils.sha256Hex(url);
            this.name = name;
            this.url = url;
        }
    }



    public Deadline(ProGuapTask task, SubjectRepository repo) {
        this.id = task.getId();
        this.title = task.getName();
        this.description = task.getDescription();

        DateFormat createDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat endDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.startDate = createDateFormat.parse(task.getDatecreate()).getTime() / 1000;
        } catch(ParseException | NullPointerException e) {
            this.startDate = System.currentTimeMillis()/1000;
        }

        try {
            this.endDate = endDateFormat.parse(task.getHardDeadline()).getTime() / 1000;
        } catch(ParseException | NullPointerException e) {}

        this.type = task.getTypeName();
        this.isClosed = true;
        this.isExternal = true;

        try {
            this.subject = repo.findByName(task.getSubjectName()).get(0);
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            if( task.getSubjectName() != null ) {
                String id = DigestUtils.sha256Hex(task.getSubjectName());
                this.subject = new Subject(id, task.getSubjectName());
            }
        }

        this.curPoints = task.getCurPoints();
        this.markpoint = task.getMarkpoint();

        this.reportRequired = task.getReportRequired();

        this.status = task.getStatusName();


        try {
            this.file = new ArrayList();
            this.file.add(new File(task.getFilename(), task.getUrl()));
        } catch (NullPointerException e) {}
    }

    @JsonProperty("_id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("startDate")
    private Long startDate;
    @JsonProperty("endDate")
    private Long endDate;

    @JsonProperty("type")
    private String type;

    @JsonProperty("isClosed")
    private Boolean isClosed;
    @JsonProperty("isExternal")
    private Boolean isExternal = true;

    @JsonProperty("subject")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    private Subject subject;

    @JsonProperty("curPoints")
    private Integer curPoints;
    @JsonProperty("markpoint")
    private Integer markpoint;

    @JsonProperty("reportRequired")
    private Boolean reportRequired;

    @JsonProperty("status")
    private String status;

    @JsonProperty("file")
    private List<File> file;

}