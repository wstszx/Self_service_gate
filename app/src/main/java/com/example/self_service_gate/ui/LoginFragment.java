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

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.self_service_gate.R;
import com.example.self_service_gate.databinding.FragmentLoginBinding;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.utils.InjectorUtils;
import com.example.self_service_gate.viewmodels.LoginViewModel;
import com.example.self_service_gate.viewmodels.LoginViewModelFactory;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding mFragmentLoginBinding;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private LoginViewModel mLoginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        mEtUsername = mFragmentLoginBinding.username;
        mEtPassword = mFragmentLoginBinding.password;
        LoginViewModelFactory loginViewModelFactory = InjectorUtils.provideLoginViewModelFactory(requireContext());
        mLoginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);
        mLoginViewModel.loginResult.observe(getViewLifecycleOwner(), new Observer<Login>() {
            @Override
            public void onChanged(Login login) {
                if (login != null) {
                    String code = login.getCode();
                    if ("200".equals(code)) {
                        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.faceRecognitionFragment);
                    } else {
                        ToastUtils.showShort("登录失败");
                    }
                }
            }
        });
        return mFragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentLoginBinding.tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFragmentLoginBinding.tvLogin) {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.containerFragment);

//            login();
        }
    }

    private void login() {
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            ToastUtils.showShort("用户名或密码不能为空");
            return;
        }
        mLoginViewModel.login(username, password);
    }
}
