package com.gky.smartticket;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("signup")
    Call<User> kayitOl(@Field("ad")String user_name,
                       @Field("email")String user_email,
                       @Field("sifre")String user_password,
                       @Field("telefon")String user_phone,
                       @Field("dogumtarihi")String user_date_of_birth);

    @GET("login")
    Call<User> girisYap(@Query("email")String user_email,
                        @Query("sifre")String user_password);


    /*
    @GET("getbustickets")
    Call<TicketData> getBusTickets(@Query("fromId") String fromId,
                                   @Query("toId") String toId,
                                   @Query("date") String date);

    @GET("getbustickets")
    Call<TicketData2> getBusTickets2(@Query("fromId") String fromId,
                                          @Query("toId") String toId,
                                          @Query("date") String date);
    */
    @GET("getmytickets")
    Call<List<MyTickets>> getMyTickets(@Query("email") String email);


    @GET("getbustickets")
    Call<List<Ticket2>> getBusTickets3(@Query("fromId") String fromId,
                                       @Query("toId") String toId,
                                       @Query("date") String date);




}
