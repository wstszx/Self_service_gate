package com.example.self_service_gate.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.self_service_gate.R;
import com.example.self_service_gate.databinding.FragmentLoginBinding;
import com.example.self_service_gate.databinding.FragmentMineBinding;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.model.Mine;
import com.example.self_service_gate.utils.InjectorUtils;
import com.example.self_service_gate.viewmodels.LoginViewModel;
import com.example.self_service_gate.viewmodels.LoginViewModelFactory;
import com.example.self_service_gate.viewmodels.MineViewModel;
import com.example.self_service_gate.viewmodels.MineViewModelFactory;


public class MineFragment extends Fragment implements View.OnClickListener {


    private static MineFragment sFragment;
    private FragmentMineBinding mBinding;
    private TextView mTvLogOff;
    private TextView mTvCheckUpdate;
    private TextView mTvUserAgreement;
    private MineViewModel mMineViewModel;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        if (sFragment == null) {
            sFragment = new MineFragment();
        }
        return sFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMineBinding.inflate(inflater, container, false);
        mTvLogOff = mBinding.tvLogOff;
        mTvCheckUpdate = mBinding.tvCheckUpdate;
        mTvUserAgreement = mBinding.tvUserAgreement;
        MineViewModelFactory mineViewModelFactory = InjectorUtils.provideMineViewModelFactory(requireContext());
        mMineViewModel = new ViewModelProvider(this, mineViewModelFactory).get(MineViewModel.class);
        mMineViewModel.mMineMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Mine>() {
            @Override
            public void onChanged(Mine mine) {
                if (mine != null) {
                    String code = mine.getCode();
                    if ("200".equals(code)) {
                        NavHostFragment.findNavController(MineFragment.this).navigate(R.id.loginFragment);
                    } else {
                        ToastUtils.showShort("登出失败");
                    }
                }
            }
        });
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvLogOff.setOnClickListener(this);
        mTvCheckUpdate.setOnClickListener(this);
        mTvUserAgreement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mTvLogOff) {
            getLogout();
        } else if (v == mTvCheckUpdate) {

        } else if (v == mTvUserAgreement) {

        }
    }

    private void getLogout() {
        mMineViewModel.getLogoutResult();
    }
}
