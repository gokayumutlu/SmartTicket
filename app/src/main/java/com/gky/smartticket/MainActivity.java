package com.gky.smartticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileInputStream;


public class MainActivity extends AppCompatActivity {

    Intent intent;
    TextView main_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.register_toolbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
        }

        main_tv=findViewById(R.id.main_tv);

        Bundle b=getIntent().getExtras();
        String data=b.getString("data");
        main_tv.setText(data);
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
                try{
                    FileInputStream fileInputStream=new FileInputStream("config.txt");
                    fileInputStream.close();
                }catch (Exception e){
                    Log.e("ItemLogoutError","Logout item selected but error occured"+e.toString());
                }

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
