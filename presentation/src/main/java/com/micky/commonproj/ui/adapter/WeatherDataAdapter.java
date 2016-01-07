package com.micky.commonproj.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.micky.commonproj.R;
import com.micky.commonproj.domain.model.WeatherData;
import com.micky.commonproj.domain.model.WeatherExtra;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Package com.micky.commonproj.ui.adapter
 * @Project CommonProj
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Team KTEAM
 * @Date 2016-01-04 22:52
 */
public class WeatherDataAdapter extends BaseListAdapter<WeatherData> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_data, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        WeatherData weatherData = getItem(position);
        itemViewHolder.tvDate.setText(weatherData.date);
        itemViewHolder.sdvWeather.setImageURI(Uri.parse(weatherData.dayPictureUrl));
        itemViewHolder.tvWeather.setText(weatherData.weather);
        itemViewHolder.tvTemperature.setText(weatherData.temperature);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_date) TextView tvDate;
        @Bind(R.id.sdv_weather) SimpleDraweeView sdvWeather;
        @Bind(R.id.tv_weather) TextView tvWeather;
        @Bind(R.id.tv_temperature) TextView tvTemperature;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
