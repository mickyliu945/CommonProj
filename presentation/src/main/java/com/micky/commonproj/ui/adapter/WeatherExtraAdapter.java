package com.micky.commonproj.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.micky.commonproj.R;
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
public class WeatherExtraAdapter extends BaseListAdapter<WeatherExtra> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_extra, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        WeatherExtra weatherExtra = getItem(position);
        itemViewHolder.tvTitle.setText(weatherExtra.title);
        itemViewHolder.tvZs.setText(weatherExtra.zs);
        itemViewHolder.tvTipt.setText(holder.itemView.getContext().getString(R.string.weather_content, weatherExtra.tipt, weatherExtra.des));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_title) TextView tvTitle;
        @Bind(R.id.tv_zs) TextView tvZs;
        @Bind(R.id.tv_tipt) TextView tvTipt;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
