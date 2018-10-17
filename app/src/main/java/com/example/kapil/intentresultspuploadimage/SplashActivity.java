package com.example.kapil.intentresultspuploadimage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.kapil.intentresultspuploadimage.MainActivity.FILE_NAME;
import static com.example.kapil.intentresultspuploadimage.MainActivity.USERNAME;

public class SplashActivity extends AppCompatActivity {


    private SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSp = getSharedPreferences(FILE_NAME, MODE_PRIVATE);

        new Handler().postDelayed(()->{


            String username = mSp.getString(USERNAME,"");
            Intent i;
            if(username.isEmpty()){
                i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
            }else {
                i = new Intent(SplashActivity.this, HomePage.class);
                startActivity(i);
            }


        },3000);
    }
}
