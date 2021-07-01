package com.schedguap.schedguap.Database.Entities;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Lesson {

    @Id
    @Column(name="lesson_id")
    private String id;

    @Column(name="start_name")
    private String startTime;

    @Column(name="end_time")
    private String endTime;

    @Column(name="number_lesson")
    private int numLesson;

    @Column(name="day")
    private String day;

    @Column(name="room")
    private String room;

    @Column(name="type")
    private String type;

    @Column(name = "week")
    private String week;


    @ManyToMany
    private Set<Professor> professors;

    @ManyToOne
    private Subject subject;

    @ManyToMany
    private Set<PupilGroup> groups;


    public Lesson() {
    }


    public Lesson(String id, String startTime, String endTime, int numLesson, String day, String room, String type, Subject subject, Set<Professor> professors, Set<PupilGroup> pupilGroups,  String week) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numLesson = numLesson;
        this.day = day;
        this.room = room;
        this.type = type;
        this.professors = professors;
        this.subject = subject;
        this.week = week;
        this.groups = pupilGroups;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getNumLesson() {
        return numLesson;
    }

    public void setNumLesson(int numLesson) {
        this.numLesson = numLesson;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Set<PupilGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<PupilGroup> groups) {
        this.groups = groups;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
