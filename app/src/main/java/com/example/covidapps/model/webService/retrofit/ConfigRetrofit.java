package com.example.covidapps.model.webService.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    private static final String BASE_URL = "https://services5.arcgis.com";
    private static Retrofit retRofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S serVice(Class<S> serviceClass) {
        return retRofit.create(serviceClass);
    }
}
