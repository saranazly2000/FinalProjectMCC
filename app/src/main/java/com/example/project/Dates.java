package com.example.project;

public class Dates {
    private String date;
    private String event;



    public Dates() {}
    public Dates(String date, String event) {
        this.date=date;
        this.event=event;


    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }


}
