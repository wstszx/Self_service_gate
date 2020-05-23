package com.example.self_service_gate.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.self_service_gate.data.StaffSelectionRepository;

public class StaffSelectionViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private StaffSelectionRepository mRepository;

    public StaffSelectionViewModelFactory(StaffSelectionRepository repository) {

        mRepository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StaffSelectionViewModel(mRepository);

    }
}
