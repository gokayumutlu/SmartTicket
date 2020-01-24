package com.gky.smartticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

import java.io.FileInputStream;
import java.security.spec.ECField;


public class MainActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME="MyPrefsFile";
    Intent intent;
    TextView main_tv;
    String email;
    private TableLayout tableLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate: ");

        Toolbar toolbar=findViewById(R.id.register_toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }
        try{
            email=getIntent().getStringExtra("email");
            Log.d("MainActivity35","email: "+email);
        }catch (Exception e){
            Log.e("mainspemailerror","MainActivity Shared Preference Email Error!"+e.getMessage());
        }

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
                SharedPreferences.Editor editor=getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE).edit();
                editor.putString("email","0");
                editor.apply();

                intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish(); break;
            case R.id.item_signup:
                Log.d("SignupItem","Item signup selected");
                intent=new Intent(MainActivity.this,ActivitySignup.class);
                startActivity(intent);
                finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }
}
