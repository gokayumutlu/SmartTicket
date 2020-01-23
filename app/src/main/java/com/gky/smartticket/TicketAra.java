package com.gky.smartticket;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class TicketAra implements LoadDataInterface{

    private String fromId,toId,date;
    private ListAdapt mAdapt;

    public TicketAra(String fromId, String toId, String date, ListAdapt mAdapt) {
        this.fromId = fromId;
        this.toId = toId;
        this.date = date;
        this.mAdapt = mAdapt;
    }

    public void func(Context context){
        Log.d("TicketAra1","func in TicketAra");
        LoadTicketDataWithoutAsyncTask loadWa=new LoadTicketDataWithoutAsyncTask(mAdapt);
        loadWa.loadData(fromId,toId,date,this);
    }

    @Override
    public void callList(ArrayList<Bilet> biletList) {
        Log.d("TicketAra2","callList in TicketAra/ 1.Ticket time "+biletList.get(1).getTicket_time());
        mAdapt.update(biletList);
    }
}
