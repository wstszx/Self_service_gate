package com.example.self_service_gate.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
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
                        requestPermission();
                    } else {
                        ToastUtils.showShort("登录失败");
                    }
                }
            }
        });
        return mFragmentLoginBinding.getRoot();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
//                ToastUtils.showShort("禁止拍照权限会导致无法完成人脸身份验证");
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.faceRecognitionFragment);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentLoginBinding.tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFragmentLoginBinding.tvLogin) {
//            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.faceRecognitionFragment);
            checkPermission();

//            login();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                ToastUtils.showShort("禁止拍照权限会导致无法完成人脸身份验证");
            } else {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.faceRecognitionFragment);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (MY_PERMISSIONS_REQUEST_CAMERA == requestCode) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.faceRecognitionFragment);
            }
        }

    }
}
