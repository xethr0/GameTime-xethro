package com.example.login;

public class Event {
    private String date;
    private String location;
    private String sportType;
    private String time;
    private String title;
    private String uid;
    private String eventKey;

    public Event(String date, String location, String sportType, String time, String title, String uid, String eventKey) {
        this.date = date;
        this.location = location;
        this.sportType = sportType;
        this.time = time;
        this.title = title;
        this.uid = uid;
        this.eventKey = eventKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getEventKey() { return eventKey; }

    public void setEventKey(String eventKey) { this.eventKey = eventKey; }

    @Override
    public String toString() {
        return "Event{" +
                "date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", sportType='" + sportType + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
