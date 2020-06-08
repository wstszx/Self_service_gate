package com.example.self_service_gate.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.self_service_gate.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        for (Fragment fragment : fragments) {
//            if (fragment != null) {
//                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            }
//        }
//    }
}
