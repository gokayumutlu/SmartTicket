package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class TrainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView trainDate;
    private Button trainTodayButton,trainTomorrowButton,trainSearchButton;
    Spinner cityFromSpinner,cityToSpinner;
    HashMap<String,Integer> trainCities=new HashMap<>();
    List<String> keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        Calendar calendar=Calendar.getInstance();
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int month=calendar.get(Calendar.MONTH);
        final int year=calendar.get(Calendar.YEAR);

        Toolbar toolbar=findViewById(R.id.train_toolbar);
        cityFromSpinner=findViewById(R.id.train_from_spinner);
        cityToSpinner=findViewById(R.id.train_to_spinner);
        trainDate=findViewById(R.id.train_date_tv);
        trainTodayButton=findViewById(R.id.train_date_today_button);
        trainTomorrowButton=findViewById(R.id.train_date_tomorrow_button);
        trainSearchButton=findViewById(R.id.train_search_button);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrainActivity.this,MidActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.train_datepicker_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        trainTodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(year,month,day);
            }
        });

        trainTomorrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(year,month,day+1);
            }
        });

        trainSearchButton.setOnClickListener(new View.OnClickListener() {
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
        setDate(year,month,dayOfMonth);
    }

    public void setDate(int year, int month, int dayOfMonth){
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

        String date=dayOfMonth+"/"+monthtext+"/"+year;
        trainDate.setText(date);
        trainDate.setVisibility(View.VISIBLE);
    }

    public void next(){
        String text=cityFromSpinner.getSelectedItem().toString();
        String text2=cityToSpinner.getSelectedItem().toString();
        Log.d("TrainSelectedCities","From: "+text+"/To: "+text2);

        try {
            int id=trainCities.get(text);
            int id2=trainCities.get(text2);
            Log.d("TrainCityIDs","Id1= "+id+" / Id2= "+id2);
        }catch (Exception e){
            Log.e("TGetIdFromSpinnerError","Cant get id from spinner Train");
        }
    }

    public void putCity(){
        trainCities.put("İstanbul-Halkalı",1);
        trainCities.put("İstanbul-Pendik",2);
        trainCities.put("Kocaeli-İzmit",3);
        trainCities.put("Sakarya-Arifiye",4);
        trainCities.put("Bilecik-Bozüyük",5);
        trainCities.put("Eskişehir-Porsuk",6);
        trainCities.put("Konya-Pınarbaşı",7);
        trainCities.put("Isparta-Eğirdir",8);

        keys=new ArrayList<>(trainCities.keySet());



    }
}
