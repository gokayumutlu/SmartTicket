package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivitySignup extends AppCompatActivity {

    public boolean status;
    EditText signup_email_et;
    Button signup_button;
    Intent intent;
    TextView signup_login_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_email_et=findViewById(R.id.signup_email_et);
        signup_button=findViewById(R.id.signup_button);
        signup_login_tv=findViewById(R.id.signup_login_tv);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String data=signup_email_et.getText().toString();
                //data=data+":"+"1";
                //storeCredentials(data);
                intent=new Intent(ActivitySignup.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signup_login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(ActivitySignup.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void storeCredentials2(String data){

    }

    private void storeCredentials(String data){
        status=true; // ??
        try{
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            Log.e("Success","Data write successfully completed");
            outputStreamWriter.close();
        }catch (IOException e){
            Log.e("Exception", "File write failed :"+e.toString());
        }

    }

    private String getCredentials(){
        String ret="";
        try{
            InputStream inputStream=getApplicationContext().openFileInput("config.txt");
            if(inputStream!=null){
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String receivedString="";
                StringBuilder stringBuilder=new StringBuilder();
                while((receivedString=bufferedReader.readLine())!=null){
                    stringBuilder.append(receivedString);
                }
                inputStream.close();
                ret=stringBuilder.toString();
            }
        }catch (FileNotFoundException e){
            Log.e("login activity", "File not found: " + e.toString());
        }catch (IOException ex){
            Log.e("login activity", "Can not read file: " + ex.toString());
        }
        return ret;
    }
}
