package com.gky.smartticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bilet {

    @SerializedName("departure_time")
    @Expose
    private String ticket_time;
    @SerializedName("travel_fare")
    @Expose
    private int ticket_fare;
    @SerializedName("travel_time")
    @Expose
    private int ticket_duration;

    public Bilet(String ticket_time, int ticket_duration, int ticket_fare) {
        this.ticket_time = ticket_time;
        this.ticket_duration = ticket_duration;
        this.ticket_fare = ticket_fare;
    }

    public Bilet(){

    }

    public String getTicket_time() {
        return ticket_time;
    }

    public void setTicket_time(String ticket_time) {
        this.ticket_time = ticket_time;
    }

    public int getTicket_duration() {
        return ticket_duration;
    }

    public void setTicket_duration(int ticket_duration) {
        this.ticket_duration = ticket_duration;
    }

    public int getTicket_fare() {
        return ticket_fare;
    }

    public void setTicket_fare(int ticket_fare) {
        this.ticket_fare = ticket_fare;
    }
}
