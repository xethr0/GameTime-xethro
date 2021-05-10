package com.example.login;

public class Events {
    public String eventTitle;
    public String address;
    public String sport;

    public Events() {

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

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public Events(String eventTitle, String sport, String address) {
        this.eventTitle = eventTitle;
        this.sport = sport;
        this.address = address;
    }

}
