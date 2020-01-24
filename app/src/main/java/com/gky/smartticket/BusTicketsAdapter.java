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

public class BusTicketsAdapter extends RecyclerView.Adapter<BusTicketsAdapter.BusTicketsViewHolder>{

    public List<Ticket2> myTickets;
    private Context context;
    private int rowlayout;
    private OnTicketClickListener onTicketClickListener;

    public BusTicketsAdapter(List<Ticket2> myTickets, int rowlayout, Context context,OnTicketClickListener onTicketClickListener) {
        this.myTickets = myTickets;
        this.context = context;
        this.rowlayout = rowlayout;
        this.onTicketClickListener=onTicketClickListener;
    }

    @NonNull
    @Override
    public BusTicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(rowlayout,parent,false);
        return new BusTicketsViewHolder(view,onTicketClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BusTicketsViewHolder holder, int position) {
        String fare=Integer.toString(myTickets.get(position).getTravelFare())+"₺";
        String time=Integer.toString(myTickets.get(position).getTravelTime())+" saat";
        holder.departure_time.setText(myTickets.get(position).getDepartureTime());
        holder.travel_fare.setText(fare);
        holder.travel_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return myTickets.size();
    }

    public static class BusTicketsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout ticketsLayout;
        TextView departure_time;
        TextView travel_fare;
        TextView travel_time;
        OnTicketClickListener onTicketClickListener;

        public BusTicketsViewHolder(@NonNull View itemView, OnTicketClickListener onTicketClickListener) {
            super(itemView);
            ticketsLayout=itemView.findViewById(R.id.bus_tickets_layout);
            departure_time=itemView.findViewById(R.id.bus_departure_time);
            travel_fare=itemView.findViewById(R.id.bus_ticket_fare);
            travel_time=itemView.findViewById(R.id.bus_ticket_time);
            this.onTicketClickListener=onTicketClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTicketClickListener.onTicketClick(getAdapterPosition());
        }
    }

    public interface OnTicketClickListener{
        void onTicketClick(int position);
    }
}
