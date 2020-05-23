package com.example.self_service_gate.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance;
    private ApiService service;

    public ApiService getService() {
        return service;
    }


    private RetrofitClient() {
        OkHttpClient okHttpClient = OkHttpProvider.createOkHttpClient();
        String BASE_URL = "http://47.105.94.62:8088/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();
        this.service = retrofit.create(ApiService.class);
    }

    public synchronized static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }
}
