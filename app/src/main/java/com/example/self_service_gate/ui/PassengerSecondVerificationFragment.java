package com.example.self_service_gate.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.self_service_gate.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PassengerSecondVerificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PassengerSecondVerificationFragment extends Fragment {

    private static PassengerSecondVerificationFragment sFragment;

    public PassengerSecondVerificationFragment() {
        // Required empty public constructor
    }

    public static PassengerSecondVerificationFragment newInstance() {
        if (sFragment == null) {
            sFragment = new PassengerSecondVerificationFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_passenger_second_verification, container, false);
    }
}
