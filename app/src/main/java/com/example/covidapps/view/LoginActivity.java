package com.example.covidapps.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.covidapps.R;
import com.example.covidapps.model.preferencesData.UserModel;
import com.example.covidapps.model.preferencesData.UserPreferences;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {


    private EditText edUsername, edPassword;
    private View parentView;


    private UserPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {


        preferences = new UserPreferences(this);

        parentView = findViewById(android.R.id.content);
        edUsername = findViewById(R.id.edt_username);
        edPassword = findViewById(R.id.edt_password);


        CardView btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    private void showSnack(String data) {
        Snackbar mSnackbar = Snackbar.make(parentView, data, Snackbar.LENGTH_LONG);
        View view = mSnackbar.getView();
        TextView sbTextView = view.findViewById(com.google.android.material.R.id.snackbar_text);
        sbTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        sbTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mSnackbar.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();

            String admin ="admin";
            if(username.equals(admin) && password.equals(admin)){
                UserModel dataForPref = new UserModel();
                dataForPref.setUsername(admin);
                preferences.setDataPref(dataForPref);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else if (!password.equals(admin)){
                showSnack(getString(R.string.username_password_salah));
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}