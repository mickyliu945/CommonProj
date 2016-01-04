package com.micky.commonproj.domain.model;

import java.util.List;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.model
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-04 18:13
 * @Version 1.0
 */
public class WeatherResult {
    public String currentCity;
    public String pm25;
    public List<WeatherExtra> index;
    public List<WeatherData> weather_data;
}
