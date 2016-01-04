package com.micky.commonproj.ui.view;

import com.micky.commonproj.domain.service.response.WeatherResponse;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.presentation
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 13:55
 * @Version 1.0
 */
public interface MainView {
    void showProgress();
    void hideProgress();
    void setupData(WeatherResponse response);
}