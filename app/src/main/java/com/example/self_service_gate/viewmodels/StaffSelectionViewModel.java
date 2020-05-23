package com.example.self_service_gate.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.self_service_gate.data.StaffSelectionRepository;
import com.example.self_service_gate.model.BaseResponse;
import com.example.self_service_gate.model.StaffMember;

import okhttp3.RequestBody;

public class StaffSelectionViewModel extends ViewModel {
    private MutableLiveData<StaffMember> mStaffMemberMutableLiveData;
    private MutableLiveData<BaseResponse> confirmWorkLiveData;
    private MutableLiveData<BaseResponse> confirmLaidOffLiveData;
    private StaffSelectionRepository mRepository;

    public StaffSelectionViewModel(StaffSelectionRepository repository) {
        mStaffMemberMutableLiveData = new MutableLiveData<>();
        confirmWorkLiveData = new MutableLiveData<>();
        confirmLaidOffLiveData = new MutableLiveData<>();
        mRepository = repository;
    }

    public MutableLiveData<BaseResponse> getConfirmWorkLiveData() {
        return confirmWorkLiveData;
    }

    public MutableLiveData<BaseResponse> getConfirmLaidOffLiveData() {
        return confirmLaidOffLiveData;
    }

    public MutableLiveData<StaffMember> getStaffMemberMutableLiveData() {
        return mStaffMemberMutableLiveData;
    }

    public void getStaffMember(String areaNumber) {
        mRepository.getStaffMember(areaNumber, mStaffMemberMutableLiveData);
    }

    public void confirmToWork(RequestBody body) {
        mRepository.confirmToWork(confirmWorkLiveData, body);
    }

    public void confirmLaidOff(String areaNumber) {
        mRepository.confirmLaidOff(areaNumber, confirmLaidOffLiveData);
    }
}
