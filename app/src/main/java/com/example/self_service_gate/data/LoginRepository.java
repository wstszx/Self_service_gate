package com.example.self_service_gate.data;

import androidx.lifecycle.MutableLiveData;

import com.example.self_service_gate.model.BaseResponse;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.net.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static LoginRepository instance;

    public static LoginRepository getInstance() {
        if (instance == null) {
            synchronized (LoginRepository.class) {
                if (instance == null) {
                    instance = new LoginRepository();
                }
            }
        }
        return instance;
    }

    public void getLoginResult(String username, String password, final MutableLiveData<Login> loginResult) {
        RetrofitClient.getInstance().getService().login(username, password).enqueue(new Callback<BaseResponse<Login>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<Login>> call, @NotNull Response<BaseResponse<Login>> response) {
                BaseResponse<Login> body = response.body();
                Login data = null;
                if (body != null) {
                    data = body.getData();
                }
                loginResult.postValue(data);
            }

            @Override
            public void onFailure(Call<BaseResponse<Login>> call, Throwable t) {

            }
        });
    }

}
