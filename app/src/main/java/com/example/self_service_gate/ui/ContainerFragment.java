package com.example.self_service_gate.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.self_service_gate.R;
import com.example.self_service_gate.databinding.FragmentContainerBinding;


public class ContainerFragment extends Fragment implements View.OnClickListener {


    private static final int JOB_SCHEDULING = 1;
    private static final int SECOND_VERIFICATION = 2;
    private static final int ME = 3;
    private FragmentContainerBinding mFragmentContainerBinding;
    private TextView tvPersonnelSelection;
    private TextView tvPassengerSecondVerification;
    private TextView mTvMe;
    private TextView mTvTitle;
    private Fragment currentFragment = new Fragment();
    private StaffSelectionFragment mStaffSelectionFragment;
    private PassengerSecondVerificationFragment mSecondVerificationFragment;
    private MineFragment mMineFragment;

    public ContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentContainerBinding = FragmentContainerBinding.inflate(inflater, container, false);
        tvPersonnelSelection = mFragmentContainerBinding.tvPersonnelSelection;
        tvPassengerSecondVerification = mFragmentContainerBinding.tvPassengerSecondVerification;
        mTvMe = mFragmentContainerBinding.tvMe;
        mTvTitle = mFragmentContainerBinding.tvTitle;
        mStaffSelectionFragment = StaffSelectionFragment.newInstance();
        mSecondVerificationFragment = PassengerSecondVerificationFragment.newInstance();
        mMineFragment = MineFragment.newInstance();
        switchFragment(mStaffSelectionFragment).commit();
        return mFragmentContainerBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvPersonnelSelection.setOnClickListener(this);
        tvPassengerSecondVerification.setOnClickListener(this);
        mTvMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvPassengerSecondVerification) {
            switchFragment(mSecondVerificationFragment).commit();
        } else if (v == tvPersonnelSelection) {
            switchFragment(mStaffSelectionFragment).commit();
        } else if (v == mTvMe) {
            switchFragment(mMineFragment).commit();
        }
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.framelayout, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }


}
