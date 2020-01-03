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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity {

    EditText login_email_et;
    Button login_button;
    Intent intent;
    TextView login_signup_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_et=findViewById(R.id.login_email_et);
        login_button=findViewById(R.id.login_button);
        login_signup_tv=findViewById(R.id.login_signup_tv);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=login_email_et.getText().toString();
                data=data+":"+"1";
                storeCredentials(data);
                intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        login_signup_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(LoginActivity.this,ActivitySignup.class);
                startActivity(intent);
            }
        });

    }

    public void loadStatus(){
        FileInputStream fileInputStream=null;
        try {
            fileInputStream=openFileInput("config.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();
            String text;
            while((text=bufferedReader.readLine())!=null){
                stringBuilder.append(text).append("\n");
            }

            Log.e("ConfigContent","Content of the config file: "+text);
            login_email_et.setText(text);

        }catch (FileNotFoundException e){
            Log.e("FileNotFound","File Not Found Exception: "+e.toString());
        }catch (IOException e){
            Log.e("IOException","IO Exception: "+e.toString());
        }catch (Exception e){
            Log.e("Error","Error: "+e.toString());
        }finally {
            if(fileInputStream!=null){
                try {
                    Log.e("Close Stream","Checking if this part is reachable");
                    fileInputStream.close();
                }catch (IOException e){
                    Log.e("IOException2","IO Exception when closing stream"+e.toString());
                }
            }
        }
    }

    private void storeCredentials(String data){
        try{
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            Log.e("Success","Data write successfully completed");
            outputStreamWriter.close();
        }catch (IOException e){
            Log.e("Exception", "File write failed :"+e.toString());
        }

    }
}
