package com.example.self_service_gate.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.self_service_gate.R;
import com.example.self_service_gate.databinding.FragmentCheckUpdatesBinding;
import com.example.self_service_gate.databinding.FragmentUserAgreementBinding;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.utils.InjectorUtils;
import com.example.self_service_gate.viewmodels.CheckUpdateViewModel;
import com.example.self_service_gate.viewmodels.CheckUpdateViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckUpdatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckUpdatesFragment extends Fragment {
    private static CheckUpdatesFragment sFragment;
    private FragmentCheckUpdatesBinding mBinding;
    private CheckUpdateViewModel mViewModel;

    public static CheckUpdatesFragment newInstance() {
        if (sFragment == null) {
            sFragment = new CheckUpdatesFragment();
        }
        return sFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentCheckUpdatesBinding.inflate(inflater, container, false);
        initView();
        initData();
        return mBinding.getRoot();
    }

    private void initData() {
        CheckUpdateViewModelFactory factory = InjectorUtils.provideCheckUpdateViewModelFactory(requireContext());
        mViewModel = new ViewModelProvider(this, factory).get(CheckUpdateViewModel.class);
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
