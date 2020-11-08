package com.example.covidapps.model.preferencesData;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    private static final String PREFS_NAME = "usr_covid_apps";

    //    String
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private final SharedPreferences preferences;

    public UserPreferences(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setDataPref(UserModel value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME, value.username);
        editor.putString(PASSWORD, value.password);
        editor.apply();
    }


    public UserModel getDataPref() {
        UserModel model = new UserModel();
        model.setUsername(preferences.getString(USERNAME, ""));
        model.setPassword(preferences.getString(PASSWORD, ""));
        return model;
    }

    public void clearDataPref() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
