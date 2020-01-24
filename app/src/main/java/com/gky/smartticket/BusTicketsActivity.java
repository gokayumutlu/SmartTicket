package com.gky.smartticket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusTicketsActivity extends AppCompatActivity implements BusTicketsAdapter.OnTicketClickListener {

    TextView tickets_bort_tv,tickets_date_tv,tickets_from_tv,tickets_to_tv;
    List<Ticket2> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_tickets);

        Toolbar toolbar=findViewById(R.id.bus_tickets_toolbar);
        Intent intent=getIntent();
        String bort_tv=intent.getStringExtra("borttv");
        String date_tv=intent.getStringExtra("date");
        String fromCity=intent.getStringExtra("fromcity");
        String toCity=intent.getStringExtra("tocity");

        String queryDate=intent.getStringExtra("querydate");
        String fromId=intent.getStringExtra("qfromid");
        String toId=intent.getStringExtra("qtoid");

        tickets_bort_tv=findViewById(R.id.bus_tickets_bort_tv);
        tickets_bort_tv.setText(bort_tv);
        tickets_date_tv=findViewById(R.id.bus_tickets_date_tv);
        tickets_date_tv.setText(date_tv);
        tickets_from_tv=findViewById(R.id.bus_tickets_from_tv);
        tickets_from_tv.setText(fromCity);
        tickets_to_tv=findViewById(R.id.bus_tickets_to_tv);
        tickets_to_tv.setText(toCity);

        final RecyclerView recyclerView=findViewById(R.id.recycler_bustickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BusTicketsActivity.this,MidActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getData(queryDate,fromId,toId,recyclerView);
    }

    public void getData(final String queryDate, final String fromId, final String toId, final RecyclerView recyclerView){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url.getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        Api api=retrofit.create(Api.class);
        Call<List<Ticket2>> call=api.getBusTickets3(fromId,toId,queryDate);
        call.enqueue(new Callback<List<Ticket2>>() {
            @Override
            public void onResponse(Call<List<Ticket2>> call, Response<List<Ticket2>> response) {
                List<Ticket2> myTickets=response.body();
                list=response.body();
                recyclerView.setAdapter(new BusTicketsAdapter(myTickets,R.layout.list_item,getApplicationContext(),BusTicketsActivity.this));
                if(myTickets.size()==0){
                    Log.d("MyTicketsActivity","Response list empty"+"fromId: "+fromId+" toId: "+toId+" queryDate: "+queryDate);
                }
                else{
                    for(int i=0;i<myTickets.size();i++){
                        Log.d("BusTickets-getData","Dep.Time: "+myTickets.get(i).getDepartureTime()+"\n Travel Fare: "+myTickets.get(i).getTravelFare()+"\n Travel Time: "+myTickets.get(i).getTravelTime());

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Ticket2>> call, Throwable t) {
                Log.e("BusTicketsActivity","Error onFailure: "+t.getMessage());
            }
        });
    }

    @Override
    public void onTicketClick(final int position) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Onayla");
        builder.setMessage("Onaylıyor musun?");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AlertDialog.Builder builder2=new AlertDialog.Builder(BusTicketsActivity.this);
                builder2.setTitle("Onaylanan bilet bilgileri");
                builder2.setMessage("Saat: "+list.get(position).getDepartureTime()+"\n Ücret: "+list.get(position).getTravelFare()+"\n Süre: "+list.get(position).getTravelTime());
                builder2.setNeutralButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
}
