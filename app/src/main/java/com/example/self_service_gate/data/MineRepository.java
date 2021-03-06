package com.example.self_service_gate.data;

import androidx.lifecycle.MutableLiveData;

import com.example.self_service_gate.model.BaseResponse;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.model.Mine;
import com.example.self_service_gate.net.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineRepository {
    private static MineRepository instance;

    public static MineRepository getInstance() {
        if (instance == null) {
            synchronized (MineRepository.class) {
                if (instance == null) {
                    instance = new MineRepository();
                }
            }
        }
        return instance;
    }


    public void getLogoutResult(final MutableLiveData<Login> mineMutableLiveData) {
        RetrofitClient.getInstance().getService().logout().enqueue(new Callback<BaseResponse<Login>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<Login>> call, @NotNull Response<BaseResponse<Login>> response) {
                BaseResponse<Login> body = response.body();
                Login data = null;
                if (body != null) {
                    data = body.getData();
                }
                mineMutableLiveData.postValue(data);
            }

            @Override
            public void onFailure(Call<BaseResponse<Login>> call, Throwable t) {

            }
        });

    }
}
