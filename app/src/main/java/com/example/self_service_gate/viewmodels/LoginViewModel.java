package com.example.self_service_gate.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.self_service_gate.data.LoginRepository;
import com.example.self_service_gate.model.Login;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<Login> loginResult;
    private LoginRepository mRepository;

    public LoginViewModel(LoginRepository repository) {
        loginResult = new MutableLiveData<>();
        mRepository = repository;
    }

    public void login(String username, String password) {
        mRepository.getLoginResult(username, password, loginResult);
    }
}
