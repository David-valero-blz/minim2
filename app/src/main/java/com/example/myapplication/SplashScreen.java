package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    protected int _splashTime = 3000;

    private Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized(this){
                        wait(_splashTime);
                    }

                } catch(InterruptedException e) {}
                finally {
                    finish();
                    SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                    Intent i = new Intent();
                    if (sharedPref.getString("User","")!=null) {
                        i.setClass(SplashScreen.this, MenuActivity.class);
                    }else{
                        i.setClass(SplashScreen.this, LoginActivity.class);
                    }
                    startActivity(i);

                    //stop();
                }
            }
        };

        splashTread.start();
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/



}