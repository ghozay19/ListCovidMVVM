package com.example.covidapps.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.covidapps.model.repository.ApiRepository;
import com.example.covidapps.model.webService.pojo.response.CovidResponse;

public class CovidViewModel extends ViewModel {

    private MutableLiveData<CovidResponse> covidResponseMutableLiveData;

    public void setCovidData() {
        ApiRepository repoMovie = ApiRepository.getApiRepo();
        covidResponseMutableLiveData = new MutableLiveData<>();
        covidResponseMutableLiveData = repoMovie.daftarCovid();
    }

    public LiveData<CovidResponse> getAllCovidData(){
        return  covidResponseMutableLiveData;
    }

}
