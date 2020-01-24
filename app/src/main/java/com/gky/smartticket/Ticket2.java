package com.gky.smartticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket2 {

    @SerializedName("departure_time")
    @Expose
    private String departureTime;
    @SerializedName("travel_fare")
    @Expose
    private Integer travelFare;
    @SerializedName("travel_time")
    @Expose
    private Integer travelTime;

    /**
     * No args constructor for use in serialization
     *
     */
    public Ticket2() {
    }

    /**
     *
     * @param departureTime
     * @param travelTime
     * @param travelFare
     */
    public Ticket2(String departureTime, Integer travelFare, Integer travelTime) {
        super();
        this.departureTime = departureTime;
        this.travelFare = travelFare;
        this.travelTime = travelTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getTravelFare() {
        return travelFare;
    }

    public void setTravelFare(Integer travelFare) {
        this.travelFare = travelFare;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

}