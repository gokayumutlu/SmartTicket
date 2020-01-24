package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class BusActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView busDate;
    private Button busTodayButton,busTomorrowButton,busSearchButton;
    Spinner cityFromSpinner,cityToSpinner;
    HashMap<String,Integer> busCities=new HashMap<>();
    List<String> keys;
    public String queryDate="0";
    public String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        Log.d("BusActivity", "onCreate: ");

        cityFromSpinner=findViewById(R.id.bus_from_spinner);
        cityToSpinner=findViewById(R.id.bus_to_spinner);
        Toolbar toolbar=findViewById(R.id.bus_toolbar);
        busDate=findViewById(R.id.bus_date_tv);
        busTodayButton=findViewById(R.id.bus_date_today_button);
        busTomorrowButton=findViewById(R.id.bus_date_tomorrow_button);
        busSearchButton=findViewById(R.id.bus_search_button);

        Calendar calendar=Calendar.getInstance();
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int month=calendar.get(Calendar.MONTH);
        final int year=calendar.get(Calendar.YEAR);

        putCity();

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.my_spinner_textview,keys);
        adapter.setDropDownViewResource(R.layout.my_spinner_textview);
        cityFromSpinner.setAdapter(adapter);
        cityToSpinner.setAdapter(adapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BusActivity.this,MidActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.bus_datepicker_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        busTodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(year,month,day);
            }
        });

        busTomorrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(year,month,day+1);
            }
        });

        busSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
            }
        });
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String sYear= String.valueOf(year);
        String sMonth=String.valueOf(month+1);
        String sDayOfMonth=String.valueOf(dayOfMonth);
        queryDate=sYear+"-"+sMonth+"-"+sDayOfMonth;
        Log.d("BusActivity113","onDateSet year: "+sYear+" month: "+sMonth+" day: "+sDayOfMonth+" ??->"+month);
        setDate(year,month,dayOfMonth);
    }

    public void setDate(int year,int month,int dayOfMonth){
        String monthtext="";
        switch (month){
            case 0: monthtext="Ocak"; break;
            case 1: monthtext="Şubat"; break;
            case 2: monthtext="Mart"; break;
            case 3: monthtext="Nisan"; break;
            case 4: monthtext="Mayıs"; break;
            case 5: monthtext="Haziran"; break;
            case 6: monthtext="Temmuz"; break;
            case 7: monthtext="Ağustos"; break;
            case 8: monthtext="Eylül"; break;
            case 9: monthtext="Ekim"; break;
            case 10: monthtext="Kasım"; break;
            case 11: monthtext="Aralık"; break;
            default: monthtext="Hata"; break;
        }
        if(queryDate.equals("0")){
            queryDate=String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth);
        }
        date=dayOfMonth+"/"+monthtext+"/"+year;
        busDate.setText(date);
        busDate.setVisibility(View.VISIBLE);
    }


    public void putCity(){
        busCities.put("İstanbul-Esenler",2);
        busCities.put("İstanbul-Harem",20);
        busCities.put("Kocaeli-İzmit Otogarı",21);
        busCities.put("Kocaeli-Gebze Otogarı",22);
        busCities.put("Ankara-Aşti",23);
        busCities.put("Ankara-Çubuk Otogarı",24);
        busCities.put("İzmir-Şehirlerarası Otogarı",25);
        busCities.put("Antalya-Şehir Otogarı",26);
        busCities.put("Trabzon-Şehirlerarası Otogarı",27);
        busCities.put("Erzurum-Şehirlerarası Otogarı",28);
        busCities.put("Gaziantep-Otogar",29);
        busCities.put("Eskişehir-Şehirlerarası Otobüs Terminali",30);

        keys=new ArrayList<>(busCities.keySet());



    }

    public void next(){
        String fromCity=cityFromSpinner.getSelectedItem().toString();
        String toCity=cityToSpinner.getSelectedItem().toString();
        Log.d("BusSelectedCities","From: "+fromCity+"/To: "+toCity);

        int ifromId=busCities.get(fromCity);
        int itoId=busCities.get(toCity);
        Log.d("BusCityIDs","Id1= "+ifromId+" / Id2= "+itoId);

        String fromId=Integer.toString(ifromId);
        String toId=Integer.toString(itoId);

        Intent intent=new Intent(BusActivity.this,BusTicketsActivity.class);
        intent.putExtra("borttv","Otobüs Bileti");
        intent.putExtra("date",date);
        intent.putExtra("fromcity",fromCity);
        intent.putExtra("tocity",toCity);

        intent.putExtra("querydate",queryDate);
        intent.putExtra("qfromid",fromId);
        intent.putExtra("qtoid",toId);
        startActivity(intent);


    }

}
