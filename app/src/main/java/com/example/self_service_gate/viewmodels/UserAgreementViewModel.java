package com.example.self_service_gate.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.self_service_gate.data.UserAgreementRepository;
import com.example.self_service_gate.model.Login;

public class UserAgreementViewModel extends ViewModel {
    public MutableLiveData<Login> mLiveData;
    private UserAgreementRepository mRepository;

    public UserAgreementViewModel(UserAgreementRepository repository) {
        mLiveData = new MutableLiveData<>();
        mRepository = repository;
    }

    public void agreeGuidelines() {
        mRepository.agreeGuidelines(mLiveData);

    }
}
