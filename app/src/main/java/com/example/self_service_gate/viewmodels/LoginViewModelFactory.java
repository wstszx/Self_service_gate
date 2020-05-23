package com.example.self_service_gate.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.self_service_gate.data.LoginRepository;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private LoginRepository mRepository;

    public LoginViewModelFactory(LoginRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(mRepository);

    }


}
