package com.gky.smartticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTickets {

    @SerializedName("route_from_name")
    @Expose
    private String routeFromName;
    @SerializedName("route_to_name")
    @Expose
    private String routeToName;
    @SerializedName("departure_date")
    @Expose
    private String departureDate;
    @SerializedName("departure_time")
    @Expose
    private String departureTime;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyTickets() {
    }

    /**
     *
     * @param departureTime
     * @param routeToName
     * @param departureDate
     * @param routeFromName
     */
    public MyTickets(String routeFromName, String routeToName, String departureDate, String departureTime) {
        super();
        this.routeFromName = routeFromName;
        this.routeToName = routeToName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public String getRouteFromName() {
        return routeFromName;
    }

    public void setRouteFromName(String routeFromName) {
        this.routeFromName = routeFromName;
    }

    public String getRouteToName() {
        return routeToName;
    }

    public void setRouteToName(String routeToName) {
        this.routeToName = routeToName;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

}
