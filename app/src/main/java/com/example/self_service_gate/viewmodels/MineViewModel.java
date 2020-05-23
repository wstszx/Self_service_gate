package com.example.self_service_gate.viewmodels;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.self_service_gate.data.LoginRepository;
import com.example.self_service_gate.data.MineRepository;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.model.Mine;

public class MineViewModel extends ViewModel {

    public MutableLiveData<Mine> mMineMutableLiveData;
    private MineRepository mRepository;

    public MineViewModel(MineRepository repository) {
        mMineMutableLiveData = new MutableLiveData<>();
        mRepository = repository;
    }

    public void getLogoutResult() {
        mRepository.getLogoutResult( mMineMutableLiveData);
    }
}
