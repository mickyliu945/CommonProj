package com.micky.commonproj.domain.service.response;

import com.micky.commonproj.domain.model.WeatherResult;

import java.util.List;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.service.response
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-04 18:14
 * @Version 1.0
 */
public class WeatherResponse extends BaseResponse {
    public int error;
    public String status;
    public String date;
    public List<WeatherResult> results;
}
