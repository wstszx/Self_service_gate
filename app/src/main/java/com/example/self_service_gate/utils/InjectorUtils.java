package com.example.self_service_gate.utils;

import android.content.Context;

import com.example.self_service_gate.data.Converters;
import com.example.self_service_gate.data.LoginRepository;
import com.example.self_service_gate.data.MineRepository;
import com.example.self_service_gate.data.StaffSelectionRepository;
import com.example.self_service_gate.viewmodels.LoginViewModelFactory;
import com.example.self_service_gate.viewmodels.MineViewModelFactory;
import com.example.self_service_gate.viewmodels.StaffSelectionViewModelFactory;

public class InjectorUtils {
    public static LoginViewModelFactory provideLoginViewModelFactory(Context context) {
        LoginRepository repository = getLoginRepository(context);
        return new LoginViewModelFactory(repository);
    }

    private static LoginRepository getLoginRepository(Context context) {
        return LoginRepository.getInstance();
    }

    private static MineRepository getLogoutRepository(Context context) {
        return MineRepository.getInstance();
    }

    public static MineViewModelFactory provideMineViewModelFactory(Context context) {
        MineRepository repository = getLogoutRepository(context);
        return new MineViewModelFactory(repository);
    }

    public static StaffSelectionViewModelFactory provideStaffSelectionViewModelFactory(Context requireContext) {
        StaffSelectionRepository repository = getStaffSelectionRepository(requireContext);
        return new StaffSelectionViewModelFactory(repository);
    }

    private static StaffSelectionRepository getStaffSelectionRepository(Context requireContext) {
        return StaffSelectionRepository.getInstance();
    }
}
