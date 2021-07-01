package com.schedguap.schedguap.Services.DataImport.Entities;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ItemId",
        "Week",
        "Day",
        "Less",
        "Build",
        "Rooms",
        "Disc",
        "Type",
        "Groups",
        "GroupsText",
        "Preps",
        "PrepsText",
        "Dept"
})
public class ScheduleGUAPEntity {

    @JsonProperty("ItemId")
    private Integer itemId;
    @JsonProperty("Week")
    private Integer week;
    @JsonProperty("Day")
    private Integer day;
    @JsonProperty("Less")
    private Integer less;
    @JsonProperty("Build")
    private String build;
    @JsonProperty("Rooms")
    private String rooms;
    @JsonProperty("Disc")
    private String disc;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Groups")
    private String groups;
    @JsonProperty("GroupsText")
    private String groupsText;
    @JsonProperty("Preps")
    private String preps;
    @JsonProperty("PrepsText")
    private String prepsText;
    @JsonProperty("Dept")
    private Object dept;

    @JsonProperty("ItemId")
    public Integer getItemId() {
        return itemId;
    }

    @JsonProperty("ItemId")
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("Week")
    public Integer getWeek() {
        return week;
    }

    @JsonProperty("Week")
    public void setWeek(Integer week) {
        this.week = week;
    }

    @JsonProperty("Day")
    public Integer getDay() {
        return day;
    }

    @JsonProperty("Day")
    public void setDay(Integer day) {
        this.day = day;
    }

    @JsonProperty("Less")
    public Integer getLess() {
        return less;
    }

    @JsonProperty("Less")
    public void setLess(Integer less) {
        this.less = less;
    }

    @JsonProperty("Build")
    public String getBuild() {
        return build;
    }

    @JsonProperty("Build")
    public void setBuild(String build) {
        this.build = build;
    }

    @JsonProperty("Rooms")
    public String getRooms() {
        return rooms;
    }

    @JsonProperty("Rooms")
    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    @JsonProperty("Disc")
    public String getDisc() {
        return disc;
    }

    @JsonProperty("Disc")
    public void setDisc(String disc) {
        this.disc = disc;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Groups")
    public String getGroups() {
        return groups;
    }

    @JsonProperty("Groups")
    public void setGroups(String groups) {
        this.groups = groups;
    }

    @JsonProperty("GroupsText")
    public String getGroupsText() {
        return groupsText;
    }

    @JsonProperty("GroupsText")
    public void setGroupsText(String groupsText) {
        this.groupsText = groupsText;
    }

    @JsonProperty("Preps")
    public String getPreps() {
        return preps;
    }

    @JsonProperty("Preps")
    public void setPreps(String preps) {
        this.preps = preps;
    }

    @JsonProperty("PrepsText")
    public String getPrepsText() {
        return prepsText;
    }

    @JsonProperty("PrepsText")
    public void setPrepsText(String prepsText) {
        this.prepsText = prepsText;
    }

    @JsonProperty("Dept")
    public Object getDept() {
        return dept;
    }

    @JsonProperty("Dept")
    public void setDept(Object dept) {
        this.dept = dept;
    }

}
