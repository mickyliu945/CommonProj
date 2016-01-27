package com.micky.commonproj.domain.service;

import com.micky.commonlib.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * @Project CommonProject
 * @Packate com.micky.commonproj.domain.service
 *
 * @Description Retrofit 服务管理
 *
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 14:43
 * @Version 1.0
 */
public class ServiceManager {

    private static List mServiceList = new ArrayList();

    /**
     *  创建Retrofit Service
     * @param t Service类型
     * @param <T>
     * @return
     */
    public static <T> T createService(Class<T> t) {
        T service = null;
        for (Object serviceTemp : mServiceList) {
            if (t.equals(serviceTemp.getClass())) {
                service = (T) serviceTemp;
            }
        }

        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getEndPoint(t))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            service = retrofit.create(t);
            mServiceList.add(service);
        }
        return service;
    }

    /**
     *  获取EndPoint URL
     * @param t Service类型
     * @param <T>
     * @return
     */
    public static <T> String getEndPoint(T t) {
        String endPoint = "";
        if (t.equals(WeatherService.class)) {
            endPoint = Constants.ENDPOINT_WEATHER;
        }
        if ("".equals(endPoint)) {
            throw new IllegalArgumentException("Error: Can't get end point url. Please configure at the method " + ServiceManager.class.getSimpleName() + ".getEndPoint(T t)");
        }
        return endPoint;
    }
}