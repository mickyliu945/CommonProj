package com.micky.commonproj.ui.view;

import com.micky.commonproj.domain.model.Place;
import com.micky.commonproj.domain.service.response.WeatherResponse;

import java.util.List;

/**
 * @Project CommonProject
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
    void setupPlaceData(List<Place> placeList);
    void setupWeatherData(WeatherResponse response);
}