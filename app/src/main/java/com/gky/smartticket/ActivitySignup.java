package com.gky.smartticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivitySignup extends AppCompatActivity {

    public static final String MY_PREFS_NAME="MyPrefsFile";
    private EditText signup_name;
    private EditText signup_phone;
    private EditText signup_email;
    private EditText signup_birthofdate;
    private EditText signup_password;
    private Button signup_button;
    private Intent intent;
    private TextView signup_login_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d("SignupActivity", "onCreate: ");

        signup_email=findViewById(R.id.signup_email_et);
        signup_birthofdate=findViewById(R.id.signup_date_et);
        signup_name=findViewById(R.id.signup_name_et);
        signup_password=findViewById(R.id.signup_password_et);
        signup_phone=findViewById(R.id.signup_phone_et);
        signup_button=findViewById(R.id.signup_button);
        signup_login_tv=findViewById(R.id.signup_login_tv);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=signup_email.getText().toString();
                String name=signup_name.getText().toString();
                String phone=signup_phone.getText().toString();
                String birthdate=signup_birthofdate.getText().toString();
                String password=signup_password.getText().toString();

                storeCredentials2(email);
                if(email.isEmpty() || name.isEmpty() || phone.isEmpty() || birthdate.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Lütfen bütün alanları doldurun!",Toast.LENGTH_LONG).show();
                }
                else{
                    submit(name,email,phone,birthdate,password);
                    /*
                    if(status==0){
                        Toast.makeText(getApplicationContext(),"Kayıt işlemi başarısız!",Toast.LENGTH_LONG).show();
                    }
                    else if(status==1){
                        Toast.makeText(getApplicationContext(),"Kayıt işlemi başarılı!",Toast.LENGTH_LONG).show();
                        intent=new Intent(ActivitySignup.this,MainActivity.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Kayıt işlemi sırasında hata!",Toast.LENGTH_LONG).show();
                    }
                    */
                }


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

    private void storeCredentials2(String email){
        SharedPreferences.Editor editor=getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("email",email);
        editor.apply();
    }

    private void submit(String name, final String email, String phone, String birthdate, String password){
        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url.getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        Api api=retrofit.create(Api.class);
        Call<User> call=api.kayitOl(name,email,phone,birthdate,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Başarısız",Toast.LENGTH_LONG).show();
                    Log.d("submitonresfail","Başarısız!!");
                    Log.d("submitonresfail2","Response Message: "+response.message());
                    Log.d("submitonresfail3","Response Code: "+response.code());
                    //return 0;
                }
                else {
                    Toast.makeText(getApplicationContext(),"Başarılı",Toast.LENGTH_LONG).show();
                    Log.d("submitonressucc","Başarılı!!");
                    Log.d("submitonressucc2","Response Message: "+response.message());
                    Log.d("submitonressucc3","Response Code: "+response.code());
                    intent=new Intent(ActivitySignup.this,MidActivity.class);
                    //intent.putExtra("email",email); email i sharedpreference tan al
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_LONG).show();
                Log.d("submitoffail","Error: "+t.getMessage());
            }
        });
    }

    /*
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
    */
}
