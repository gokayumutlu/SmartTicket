package com.gky.smartticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyTicketsAdapter extends RecyclerView.Adapter<MyTicketsAdapter.TicketsViewHolder>{

    private List<MyTickets> tickets;
    private Context context;
    private int rowlayout;

    public MyTicketsAdapter(List<MyTickets> tickets, int rowlayout, Context context){
        this.tickets=tickets;
        this.rowlayout=rowlayout;
        this.context=context;

    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(rowlayout,parent,false);
        return new TicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder holder, int position) {
        String fromto=tickets.get(position).getRouteFromName()+"/"+tickets.get(position).getRouteToName();
        holder.fromtotv.setText(fromto);
        holder.date.setText(tickets.get(position).getDepartureDate());
        holder.time.setText(tickets.get(position).getDepartureTime());
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class TicketsViewHolder extends RecyclerView.ViewHolder{

        LinearLayout ticketsLayout;
        TextView fromtotv;
        TextView date;
        TextView time;

        public TicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketsLayout=itemView.findViewById(R.id.tickets_layout);
            fromtotv=itemView.findViewById(R.id.recycler_fromto_tv);
            date=itemView.findViewById(R.id.recycler_date_tv);
            time=itemView.findViewById(R.id.recycler_time_tv);
        }
    }
}
