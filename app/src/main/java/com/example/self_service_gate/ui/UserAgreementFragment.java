package com.example.self_service_gate.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.self_service_gate.databinding.FragmentUserAgreementBinding;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.utils.InjectorUtils;
import com.example.self_service_gate.viewmodels.CheckUpdateViewModel;
import com.example.self_service_gate.viewmodels.CheckUpdateViewModelFactory;
import com.example.self_service_gate.viewmodels.UserAgreementViewModel;
import com.example.self_service_gate.viewmodels.UserAgreementViewModelFactory;

public class UserAgreementFragment extends Fragment {

    private static UserAgreementFragment sFragment;
    private FragmentUserAgreementBinding mBinding;
    private UserAgreementViewModel mViewModel;

    public static UserAgreementFragment newInstance() {
        if (sFragment == null) {
            sFragment = new UserAgreementFragment();
        }
        return sFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentUserAgreementBinding.inflate(inflater, container, false);
        initView();
        initData();
        return mBinding.getRoot();
    }

    private void initData() {
        UserAgreementViewModelFactory factory = InjectorUtils.provideUserAgreementViewModelFactory(requireContext());
        mViewModel = new ViewModelProvider(this, factory).get(UserAgreementViewModel.class);
        mViewModel.mLiveData.observe(getViewLifecycleOwner(), new Observer<Login>() {
            @Override
            public void onChanged(Login login) {

            }
        });
        mViewModel.agreeGuidelines();
    }

    private void initView() {


    }
}
