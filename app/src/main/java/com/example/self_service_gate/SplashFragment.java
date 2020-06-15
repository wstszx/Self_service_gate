package com.example.self_service_gate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.self_service_gate.databinding.FragmentSplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends Fragment {

    private FragmentSplashBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSplashBinding.inflate(inflater, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
//        Timer timer = new Timer();
//        MyTask myTask = new MyTask();
//        timer.schedule(myTask, 1000, 3 * 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NavHostFragment.findNavController(SplashFragment.this).navigate(R.id.loginFragment);
            }
        }, 3000);
    }

//    static class MyTask extends TimerTask {
//        @Override
//        public void run() {
//
//        }
//    }
}