package com.example.self_service_gate.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpProvider {
    static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //打印一次请求的全部信息
        builder.connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }
}
