package com.example.self_service_gate.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.self_service_gate.data.UserAgreementRepository;

public class UserAgreementViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private UserAgreementRepository mRepository;

    public UserAgreementViewModelFactory(UserAgreementRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserAgreementViewModel(mRepository);

    }
}
