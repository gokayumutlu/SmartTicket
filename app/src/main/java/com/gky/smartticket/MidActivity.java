package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MidActivity extends AppCompatActivity {

    private LinearLayout midLinearBus,midLinearTrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid);

        midLinearBus=findViewById(R.id.mid_linear_bus);
        midLinearTrain=findViewById(R.id.mid_linear_train);

        midLinearBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MidActivity.this,BusActivity.class);
                startActivity(intent);
                finish();
            }
        });

        midLinearTrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MidActivity.this,TrainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
