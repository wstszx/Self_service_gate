package com.example.self_service_gate.net;


import com.example.self_service_gate.model.BaseResponse;
import com.example.self_service_gate.model.Login;
import com.example.self_service_gate.model.StaffMember;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *
 */
public interface ApiService {

    //APP登录
    @FormUrlEncoded
    @POST("dev-api/padLogin/login")
    Call<BaseResponse<Login>> login(@Field("username") String username,
                                    @Field("password") String password);

    //APP登出
    @FormUrlEncoded
    @POST("dev-api/padLogin/loginOut")
    Call<BaseResponse> logout();

    //pad工作人员选择列表
    @GET("dev-api/padMemberChoose/list")
    Call<BaseResponse<StaffMember>> getStaffSelectionsList(@Query("areaNumber") String areaNumber);

    //pad获取工作时长列表
    @GET("dev-api/padMemberChoose/getWorkTimeList")
    Call<BaseResponse> getWorkingHoursList(@Query("areaNumber") String areaNumber);

    //pad确认上岗
    @FormUrlEncoded
    @POST("dev-api/padMemberChoose/confirmWork")
    Call<BaseResponse> confirmInduction(@Body RequestBody body);

    //pad确认下岗
    @FormUrlEncoded
    @POST("dev-api/padMemberChoose/confirmUnWork")
    Call<BaseResponse> confirmUnInduction(@Field("areaNumber") String areaNumber);


}
