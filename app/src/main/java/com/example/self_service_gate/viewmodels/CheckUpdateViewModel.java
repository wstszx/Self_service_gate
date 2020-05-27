package com.example.self_service_gate.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.self_service_gate.data.CheckUpdateRepository;
import com.example.self_service_gate.model.Login;

public class CheckUpdateViewModel extends ViewModel {
    private CheckUpdateRepository mRepository;
    public MutableLiveData<Login> mLiveData;

    public CheckUpdateViewModel(CheckUpdateRepository repository) {
        mLiveData = new MutableLiveData<>();
        mRepository = repository;
    }

    public void agreeGuidelines() {
        mRepository.agreeGuidelines(mLiveData);
    }
}
