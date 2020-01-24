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

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME="MyPrefsFile";
    private EditText login_email_et,login_password_et;
    private Button login_button;
    private Intent intent;
    private TextView login_signup_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email_et=findViewById(R.id.login_email_et);
        login_password_et=findViewById(R.id.login_password_et);
        login_button=findViewById(R.id.login_button);
        login_signup_tv=findViewById(R.id.login_signup_tv);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=login_email_et.getText().toString();
                String password=login_password_et.getText().toString();
                //storeCredentials(email); buradan kaldırılıp başarılı giriş yapıldıktan sonra kaydedilmesi için girisYap() ın içine konuldu
                //girisYap(email,password);
                storeCredentials(email);
                intent=new Intent(LoginActivity.this,MidActivity.class);
                startActivity(intent);
                finish();
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

    private void storeCredentials(String email){
        SharedPreferences.Editor editor=getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE).edit();
        editor.putString("email",email);
        editor.apply();
    }

    public void girisYap(final String email, String password){

        OkHttpClient client=new OkHttpClient();
        try {

            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            client = new OkHttpClient.Builder()
                    .sslSocketFactory(new TLSSocketFactory(),(X509TrustManager) trustAllCerts[0])
                    .build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        URL url=new URL();
        Retrofit retrofit=new Retrofit.Builder().client(client).baseUrl(url.getBaseUrl()).addConverterFactory(GsonConverterFactory.create()).build();
        Api api=retrofit.create(Api.class);
        Call<User> call=api.girisYap(email,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Başarısız",Toast.LENGTH_LONG).show();
                    Log.d("loginonresfail","Başarısız!!");
                    Log.d("loginonresfail2","Response Message: "+response.message());
                    Log.d("loginonresfail3","Response Code: "+response.code());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Başarılı",Toast.LENGTH_LONG).show();
                    Log.d("loginonressucc","Başarılı!!");
                    Log.d("loginonressucc2","Response Message: "+response.message());
                    Log.d("loginonressucc3","Response Code: "+response.code());
                    if(response.code()==200){
                        storeCredentials(email);
                        intent=new Intent(LoginActivity.this,MainActivity.class);
                        //intent.putExtra("email",email); email i sharedpreference tan al
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Bir şeyler yanlış gitti",Toast.LENGTH_LONG).show();
                        Log.d("loginonressuccfail","Bir şeyler yanlış gitti: "+response.message()+" Code: "+response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_LONG).show();
                Log.d("loginonfail","Error: "+t.getMessage());
            }
        });
    }



    /*
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
    */
    /*
    private void storeCredentials(String data){
        try{
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            Log.e("Login95","Data write successfully completed");
            outputStreamWriter.close();
        }catch (IOException e){
            Log.e("Login98", "File write failed :"+e.toString());
        }

    }
    */
}
