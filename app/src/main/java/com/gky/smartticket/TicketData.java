package com.gky.smartticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TicketData {
    @SerializedName("ticketData")
    @Expose
    public ArrayList<Bilet> ticketData=null;
}
