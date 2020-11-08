package com.example.covidapps.model.webService.retrofit;

import com.example.covidapps.model.webService.pojo.response.CovidResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    String API = "/VS6HdKS0VfIhv8Ct/arcgis/rest/services/COVID19_Indonesia_per_Provinsi/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json";

    @GET(API)
    Call<CovidResponse> getAllData();

}
