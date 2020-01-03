package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainEmptyActivity extends Activity {

    Intent intent;
    private String email,status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_empty);

        loadStatus2();

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
                stringBuilder.append(text);
            }

            Log.e("ConfigContent","Content of the config file: "+text);

            intent=new Intent(MainEmptyActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }catch (FileNotFoundException e){
            Log.e("FileNotFound","File Not Found Exception: "+e.toString());
            intent=new Intent(MainEmptyActivity.this,ActivitySignup.class);
            startActivity(intent);
            finish();
        }catch (IOException e){
            Log.e("IOException","IO Exception: "+e.toString());
            intent=new Intent(MainEmptyActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            Log.e("Error","Error: "+e.toString());
            intent=new Intent(MainEmptyActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
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

    public void loadStatus2(){
        FileInputStream fileInputStream=null;
        try{
            fileInputStream=openFileInput("config.txt");
            int c;
            String temp="";
            while((c=fileInputStream.read())!=-1){
                temp=temp+Character.toString((char)c);
            }

            Log.e("ConfigContent2","Content of the config file 2: "+temp);
            String[] seperatedtemp=temp.split(":");
            email=seperatedtemp[0];
            status=seperatedtemp[1];
            Log.d("EmailStatus","Email: "+email+"\n Status: "+status);
            if(status.equals("1")){
                intent=new Intent(MainEmptyActivity.this,MainActivity.class);
                intent.putExtra("data",temp);
                startActivity(intent);
                finish();
            }else {
                Log.d("StatusError","Status doesnt equal to 1");
            }



        }catch (FileNotFoundException e){
            Log.e("FileNotFound","File Not Found Exception: "+e.toString());
            intent=new Intent(MainEmptyActivity.this,ActivitySignup.class);
            startActivity(intent);
            finish();
        }catch (IOException e){
            Log.e("IOException","IO Exception: "+e.toString());
            intent=new Intent(MainEmptyActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
            Log.e("Error","Error: "+e.toString());
            intent=new Intent(MainEmptyActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
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
}
