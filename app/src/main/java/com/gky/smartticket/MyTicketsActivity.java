package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyTicketsActivity extends AppCompatActivity {
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);
        Log.d("MyTicketsActivity", "onCreate: ");

        final RecyclerView recyclerView=findViewById(R.id.tickets_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar=findViewById(R.id.mytickets_toolbar);
        Intent intent=getIntent();
        email=intent.getStringExtra("email");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyTicketsActivity.this,MidActivity.class);
                startActivity(intent);
                finish();
            }
        });

        getData(email,recyclerView);

    }

    public void getData(String email, final RecyclerView recyclerView){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url.getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        Api api=retrofit.create(Api.class);
        Call<List<MyTickets>> call=api.getMyTickets(email);
        call.enqueue(new Callback<List<MyTickets>>() {
            @Override
            public void onResponse(Call<List<MyTickets>> call, Response<List<MyTickets>> response) {
                List<MyTickets> myTickets=response.body();
                recyclerView.setAdapter(new MyTicketsAdapter(myTickets,R.layout.my_recyclerview_item,getApplicationContext()));
                if(myTickets.size()==0){
                    Log.d("MyTicketsActivity","Response list empty");
                }
                else{
                    for(int i=0;i<myTickets.size();i++){
                        Log.d("MyTicketsAct-getData","From: "+myTickets.get(i).getRouteFromName()+"\n To: "+myTickets.get(i).getRouteToName()+"\n Date: "+myTickets.get(i).getDepartureDate()+"\n Time: "+myTickets.get(i).getDepartureTime());

                    }
                }

            }

            @Override
            public void onFailure(Call<List<MyTickets>> call, Throwable t) {
                Log.e("MyTicketsActivity","Error onFailure: "+t.getMessage());
            }
        });
    }
}
