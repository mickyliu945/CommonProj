package com.micky.commonproj.domain.service;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.commonproj.domain.service
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 14:43
 * @Version 1.0
 */
public class ServiceManager {
    private static final String ENDPOINT = "http://ip.taobao.com";

    private static class ServiceManagerHolder {
        private static final ServiceManager INSTANCE = new ServiceManager();
    }

    private ServiceManager() {}

    public static final ServiceManager getInstance() {
        return ServiceManagerHolder.INSTANCE;
    }

    private ApiService mApiService = null;

    public ApiService getApiService() {
        if (mApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mApiService = retrofit.create(ApiService.class);
            return mApiService;
        }
        return mApiService;
    }
}
