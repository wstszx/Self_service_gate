package com.example.self_service_gate.data;

import androidx.lifecycle.MutableLiveData;

import com.example.self_service_gate.model.BaseResponse;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.model.StaffMember;
import com.example.self_service_gate.net.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffSelectionRepository {
    private static StaffSelectionRepository instance;

    public static StaffSelectionRepository getInstance() {
        if (instance == null) {
            synchronized (StaffSelectionRepository.class) {
                if (instance == null) {
                    instance = new StaffSelectionRepository();
                }
            }
        }
        return instance;
    }

    public void getStaffMember(String areaNumber, final MutableLiveData<StaffMember> liveData) {
//        底下列表
        StaffMember staffMember = new StaffMember();
        List<StaffMember.MemberHelperListBean> memberHelperListBeanList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            StaffMember.MemberHelperListBean memberHelperListBean = new StaffMember.MemberHelperListBean();
            memberHelperListBean.setUserId(i);
            memberHelperListBean.setUserName("底下用户名" + i);
            memberHelperListBean.setUserTitle("底下标题" + i);
            memberHelperListBeanList.add(memberHelperListBean);
        }
        staffMember.setMemberHelperList(memberHelperListBeanList);
//      上面列表
        List<StaffMember.PostHelperListBean> postHelperListBeans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            StaffMember.PostHelperListBean postHelperListBean = new StaffMember.PostHelperListBean();
            postHelperListBean.setUserId(i);
            postHelperListBean.setUserName("上面用户名" + i);
            postHelperListBean.setUserTitle("上面标题" + i);
            postHelperListBeans.add(postHelperListBean);
        }
        staffMember.setPostHelperList(postHelperListBeans);
        liveData.postValue(staffMember);


//        RetrofitClient.getInstance().getService().getStaffSelectionsList(areaNumber).enqueue(new Callback<BaseResponse<StaffMember>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<StaffMember>> call, Response<BaseResponse<StaffMember>> response) {
//                BaseResponse<StaffMember> body = response.body();
//                StaffMember data = null;
//                if (body != null) {
//                    data = body.getData();
//                }
//                liveData.postValue(data);
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse<StaffMember>> call, Throwable t) {
//
//            }
//        });
    }

    public void confirmToWork(final MutableLiveData<BaseResponse> liveData, RequestBody body) {
        RetrofitClient.getInstance().getService().confirmInduction(body).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse body = response.body();
                liveData.postValue(body);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }

    public void confirmLaidOff(String areaNumber, final MutableLiveData<BaseResponse> liveData) {
        RetrofitClient.getInstance().getService().confirmUnInduction(areaNumber).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse body = response.body();
                liveData.postValue(body);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}
