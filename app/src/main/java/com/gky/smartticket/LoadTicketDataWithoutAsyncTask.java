package com.gky.smartticket;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoadTicketDataWithoutAsyncTask {
    private String gfromId,gtoId,gdate;
    private final ListAdapt mAdapt;

    public LoadTicketDataWithoutAsyncTask(ListAdapt mAdapt) {
        this.mAdapt = mAdapt;
    }

    public void loadData(String fromId,String toId,String date, final LoadDataInterface callback){
        Log.d("LDWOAT1","LoadTicketDataWithoutAsyncTask:LoadData func");
        gfromId=fromId;
        gtoId=toId;
        gdate=date;
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url.getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        Api api=retrofit.create(Api.class);
        Call<TicketData> call=api.getBusTickets(gfromId,gtoId,gdate);
        call.enqueue(new Callback<TicketData>() {
            @Override
            public void onResponse(Call<TicketData> call, Response<TicketData> response) {
                if(!response.isSuccessful()){
                    Log.e("LDWOAT2","Error loading data in LoadTicketDataWithoutAsyncTask:LoadData");
                    Log.e("LDWOAT3","Error: "+response.message());
                }
                else {
                    Log.d("LDWOAT4","Success loading data in LoadTicketDataWithoutAsyncTask:LoadData");
                    try {
                        ArrayList<Bilet> ticketArray=response.body().ticketData;
                        for (int i=0;i<ticketArray.size();i++){
                            Log.d("LDWOAT5","dep_time: "+ticketArray.get(i).getTicket_time()+"\n " +
                                    "ticket fare: "+ticketArray.get(i).getTicket_fare()+"\n ticket duration: "+ticketArray.get(i).getTicket_duration());
                            callback.callList(ticketArray);
                        }
                    }catch (Exception e){
                        Log.e("LDWOAT6","Error in try catch: "+e.getMessage());
                    }


                }
            }

            @Override
            public void onFailure(Call<TicketData> call, Throwable t) {
                Log.e("LDWOAT7","Error onFailure: "+t.getMessage());
            }
        });
    }
}
