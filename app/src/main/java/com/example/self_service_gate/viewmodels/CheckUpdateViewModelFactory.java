package com.example.self_service_gate.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.self_service_gate.data.CheckUpdateRepository;

public class CheckUpdateViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private CheckUpdateRepository mRepository;

    public CheckUpdateViewModelFactory(CheckUpdateRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CheckUpdateViewModel(mRepository);

    }
}
