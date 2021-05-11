package com.example.login;

public class Post {
    String address;
    String eventTitle;
    String sport;
    String username;
    String date;
    String time;
    String uid;


    public Post(String address, String eventTitle, String description, String username, String eventDate, String eventTime, String uid) {
        this.address = address;
        this.eventTitle = eventTitle;
        this.sport = description;
        this.username = username;
        this.date = eventDate;
        this.time = eventTime;
        this.uid = uid;
    }

    public Post() {
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String description) {
        this.sport = description;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getEventDate() {
        return date;
    }

    public void setEventDate(String eventDate) {
        this.date = eventDate;
    }

    public String gettime() {
        return time;
    }

    public void setEventTime(String eventTime) {
        this.time = eventTime;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
