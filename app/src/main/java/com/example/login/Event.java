package com.example.login;

public class Event {
    private String date;
    private String location;
    private String sportType;
    private String time;
    private String title;

    public Event(String date, String location, String sportType, String time, String title) {
        this.date = date;
        this.location = location;
        this.sportType = sportType;
        this.time = time;
        this.title = title;
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
