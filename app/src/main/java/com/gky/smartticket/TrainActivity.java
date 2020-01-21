package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class TrainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView trainDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        Toolbar toolbar=findViewById(R.id.train_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrainActivity.this,MidActivity.class);
                startActivity(intent);
                finish();
            }
        });

        trainDate=findViewById(R.id.train_date_tv);

        findViewById(R.id.train_datepicker_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
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
}
