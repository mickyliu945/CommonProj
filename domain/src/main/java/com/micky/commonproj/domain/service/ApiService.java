package com.micky.commonproj.domain.service;


import com.micky.commonproj.domain.service.response.GetIpInfoResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.commonproj.data.api
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-21 17:22
 * @Version 1.0
 */
public interface ApiService {

    /*@GET("service/getIpInfo.php")
    Call<GetIpInfoResponse> getIpInfo(@Query("ip") String ip);*/

    @GET("service/getIpInfo.php")
    Observable<GetIpInfoResponse> getIpInfo(@Query("ip") String ip);
}
