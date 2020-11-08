package com.example.covidapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.covidapps.model.preferencesData.UserModel;
import com.example.covidapps.model.preferencesData.UserPreferences;
import com.example.covidapps.view.LoginActivity;
import com.example.covidapps.view.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {
    UserModel dataUser;
    UserPreferences uPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    private void init() {
        uPre = new UserPreferences(this);
        dataUser = uPre.getDataPref();

        int waktu_loading = 1000;
        checkSession(waktu_loading);
    }

    private void checkSession(int time) {
        new Handler().postDelayed(() -> {
            Intent intent;
            if (dataUser.getUsername().equals("admin")) {
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }, time);
    }
}