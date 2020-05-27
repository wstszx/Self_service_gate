package com.example.self_service_gate.data;

import androidx.lifecycle.MutableLiveData;

import com.example.self_service_gate.model.BaseResponse;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.net.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAgreementRepository {
    private static UserAgreementRepository instance;

    public static UserAgreementRepository getInstance() {
        if (instance == null) {
            synchronized (UserAgreementRepository.class) {
                if (instance == null) {
                    instance = new UserAgreementRepository();
                }
            }
        }
        return instance;
    }

    public void agreeGuidelines(final MutableLiveData<Login> liveData) {
        RetrofitClient.getInstance().getService().agreeGuidelines().enqueue(new Callback<BaseResponse<Login>>() {
            @Override
            public void onResponse(Call<BaseResponse<Login>> call, Response<BaseResponse<Login>> response) {
                BaseResponse<Login> body = response.body();
                Login data = body.getData();
                liveData.postValue(data);
            }

            @Override
            public void onFailure(Call<BaseResponse<Login>> call, Throwable t) {

            }
        });

    }
}
