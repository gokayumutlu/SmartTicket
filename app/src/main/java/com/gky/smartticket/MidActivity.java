package com.gky.smartticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class MidActivity extends AppCompatActivity {

    private LinearLayout midLinearBus,midLinearTrain;
    TextView mytickets_tv;
    public String email;
    public static final String MY_PREFS_NAME="MyPrefsFile";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid);
        Log.d("MidActivity", "onCreate: ");

        SharedPreferences sharedPreferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        final String email=sharedPreferences.getString("email","default");
        Log.d("MidActivityEmail","Email: "+email);
        midLinearBus=findViewById(R.id.mid_linear_bus);
        midLinearTrain=findViewById(R.id.mid_linear_train);
        mytickets_tv=findViewById(R.id.mytickets_tv);
        Toolbar toolbar=findViewById(R.id.mid_toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }
        try{
            //email=getIntent().getStringExtra("email");
            Log.d("MidActivity33","email: "+email);
        }catch (Exception e){
            Log.e("mainspemailerror","MainActivity Shared Preference Email Error!"+e.getMessage());
        }

        mytickets_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MidActivity.this,MyTicketsActivity.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_logout:
                Log.d("LogoutItem","Item logout selected");
                SharedPreferences.Editor editor=getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putString("email","0");
                editor.apply();

                intent=new Intent(MidActivity.this,LoginActivity.class);
                startActivity(intent);
                finish(); break;
            case R.id.item_signup:
                Log.d("SignupItem","Item signup selected");
                intent=new Intent(MidActivity.this,ActivitySignup.class);
                startActivity(intent);
                finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }
}
