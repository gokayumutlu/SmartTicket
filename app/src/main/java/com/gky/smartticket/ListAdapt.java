package com.gky.smartticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapt extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Bilet> bList=new ArrayList<Bilet>();

    public ListAdapt(Context context){
        mContext=context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bList.size();
    }

    @Override
    public Object getItem(int position) {
        return bList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        if(convertView==null){
            itemView=(LinearLayout) mLayoutInflater.inflate(R.layout.list_item,parent,false);
        }
        else{
            itemView=(LinearLayout) convertView;
        }

        TextView ticket_timetv=itemView.findViewById(R.id.ticket_time);
        TextView ticket_faretv=itemView.findViewById(R.id.ticket_fare);
        TextView ticket_durationtv=itemView.findViewById(R.id.ticket_during);

        String ticketTime=bList.get(position).getTicket_time();
        ticket_timetv.setText(ticketTime);
        String ticketFare=bList.get(position).getTicket_fare();
        ticket_faretv.setText(ticketFare);
        String ticketDuration=bList.get(position).getTicket_duration();
        ticket_durationtv.setText(ticketDuration);
        return itemView;
    }

    public void update(ArrayList<Bilet> biletList){
        bList=biletList;
        notifyDataSetChanged();
    }
}
