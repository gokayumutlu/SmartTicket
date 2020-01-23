package com.gky.smartticket;

public class Bilet {

    private String ticket_time;
    private String ticket_fare;
    private String ticket_duration;

    public Bilet(String ticket_time, String ticket_duration, String ticket_fare) {
        this.ticket_time = ticket_time;
        this.ticket_duration = ticket_duration;
        this.ticket_fare = ticket_fare;
    }

    public String getTicket_time() {
        return ticket_time;
    }

    public void setTicket_time(String ticket_time) {
        this.ticket_time = ticket_time;
    }

    public String getTicket_duration() {
        return ticket_duration;
    }

    public void setTicket_duration(String ticket_duration) {
        this.ticket_duration = ticket_duration;
    }

    public String getTicket_fare() {
        return ticket_fare;
    }

    public void setTicket_fare(String ticket_fare) {
        this.ticket_fare = ticket_fare;
    }
}
