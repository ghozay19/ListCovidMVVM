package com.example.covidapps.model.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.covidapps.model.webService.pojo.response.CovidResponse;
import com.example.covidapps.model.webService.retrofit.ApiService;
import com.example.covidapps.model.webService.retrofit.ConfigRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {

    private ApiService service;
    private static ApiRepository newRepo;

    private MutableLiveData<CovidResponse> covidResponseMutableLiveData;

    public static ApiRepository getApiRepo() {
        if (newRepo == null) {
            newRepo = new ApiRepository();
        }
        return newRepo;
    }

    private ApiRepository() {
        service = ConfigRetrofit.serVice(ApiService.class);
    }


    public MutableLiveData<CovidResponse> daftarCovid() {

        covidResponseMutableLiveData = new MutableLiveData<>();
        service.getAllData().enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                if (response.isSuccessful()) {
                    CovidResponse item = response.body();
                    assert item != null;
                    covidResponseMutableLiveData.postValue(item);
                }
            }

            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {
                covidResponseMutableLiveData.postValue(null);
            }
        });
        return covidResponseMutableLiveData;

    }
}
