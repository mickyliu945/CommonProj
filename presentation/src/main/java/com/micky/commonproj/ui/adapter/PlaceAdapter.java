package com.micky.commonproj.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.micky.commonproj.R;
import com.micky.commonproj.domain.model.Place;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.ui.adapter
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-08 14:13
 * @Version 1.0
 */
public class PlaceAdapter extends BaseListAdapter<Place> {
    private OnPlaceClickListener mOnPlaceClickListener;

    public void setOnPlaceClickListener(OnPlaceClickListener listener) {
        mOnPlaceClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Place place = getItem(position);
        itemViewHolder.place = place;
        itemViewHolder.tvPlace.setText(place.name);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_place) TextView tvPlace;

        public Place place;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(itemView).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
                @Override
                public void call(Void aVoid) {
                    mOnPlaceClickListener.onClick(itemView, place);
                }
            });
        }
    }

    public interface OnPlaceClickListener {
        void onClick(View view, Place place);
    }
}
