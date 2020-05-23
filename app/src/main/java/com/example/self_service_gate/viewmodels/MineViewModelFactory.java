package com.example.self_service_gate.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.self_service_gate.data.LoginRepository;
import com.example.self_service_gate.data.MineRepository;

public class MineViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private MineRepository mRepository;

    public MineViewModelFactory(MineRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MineViewModel(mRepository);

    }
}
