package com.micky.commonproj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.TimeUtils;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.view.RxView;
import com.micky.commonlib.utils.DateUtils;
import com.micky.commonlib.utils.ViewUtils;
import com.micky.commonproj.domain.model.Place;
import com.micky.commonproj.domain.model.WeatherResult;
import com.micky.commonproj.domain.service.response.WeatherResponse;
import com.micky.commonproj.presenter.MainPresenter;
import com.micky.commonproj.ui.adapter.PlaceAdapter;
import com.micky.commonproj.ui.adapter.WeatherDataAdapter;
import com.micky.commonproj.ui.adapter.WeatherExtraAdapter;
import com.micky.commonproj.ui.view.ItemDecoration;
import com.micky.commonproj.ui.view.MainView;
import com.micky.commonproj.R;
import com.micky.commonproj.presenter.impl.MainPresenterImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.presentation
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 12:22
 * @Version 1.0
 */
public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @Bind(R.id.rv_place) RecyclerView mRvPlace;
    @Bind(R.id.rv_weather_data) RecyclerView mRvWeatherData;
    @Bind(R.id.recyvler_view) RecyclerView mRvWeatherExtra;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;
    @Bind(R.id.tv_city) TextView mTvCity;
    @Bind(R.id.tv_pm25) TextView mTvPm25;
    @Bind(R.id.fab) FloatingActionButton mFloatingActionBar;
    @Bind(R.id.tv_empty_data) TextView mTvEmptyData;

    private MainPresenter mMainPresenter;
    private WeatherExtraAdapter mWeatherExtraAdapter;
    private WeatherDataAdapter mWeatherDataAdapter;
    private PlaceAdapter mPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main, true);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        initView();

        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.onCreate();
//        mMainPresenter.getPlaceData();
//        mMainPresenter.getWeatherData("北京");

        mMainPresenter.getPlaceAndWeatherData("北京");
        RxView.clicks(mFloatingActionBar).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mMainPresenter.onRefresh();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (ViewUtils.isFastClick()) return true;

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.weather:
                mDrawerLayout.openDrawer(GravityCompat.END);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMainPresenter != null) {
            mMainPresenter.onDestroy();
        }
    }

    private void initView() {
        mRvPlace.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRvPlace.setLayoutManager(staggeredGridLayoutManager);
        mPlaceAdapter = new PlaceAdapter();
        mRvPlace.setAdapter(mWeatherDataAdapter);
        mPlaceAdapter.setOnPlaceClickListener(new PlaceAdapter.OnPlaceClickListener() {
            @Override
            public void onClick(View view, Place place) {
                mMainPresenter.getWeatherData(place.name);
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        mRvPlace.setAdapter(mPlaceAdapter);

        mRvWeatherData.setHasFixedSize(true);
        mRvWeatherData.addItemDecoration(new ItemDecoration(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvWeatherData.setLayoutManager(linearLayoutManager);
        mWeatherDataAdapter = new WeatherDataAdapter();
        mRvWeatherData.setAdapter(mWeatherDataAdapter);

        mRvWeatherExtra.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvWeatherExtra.setLayoutManager(linearLayoutManager);
        mWeatherExtraAdapter = new WeatherExtraAdapter();
        mRvWeatherExtra.setAdapter(mWeatherExtraAdapter);
    }

    public void setupWeatherData(WeatherResponse weatherResponse) {
        int visibility = View.GONE;
        if (weatherResponse == null) return;
        setTitleText(DateUtils.getWeekDay(weatherResponse.date));
        if (weatherResponse.results != null && weatherResponse.results.size() > 0) {
            WeatherResult result = weatherResponse.results.get(0);
            mTvCity.setText(getString(R.string.city, result.currentCity));
            mTvPm25.setText(getString(R.string.pm25, result.pm25));

            mWeatherDataAdapter.setData(result.weather_data);
            mWeatherDataAdapter.notifyDataSetChanged();

            mWeatherExtraAdapter.setData(result.index);
            mWeatherExtraAdapter.notifyDataSetChanged();
            visibility = result.index.size() > 0 ? View.VISIBLE : View.GONE;
        }
        mTvEmptyData.setVisibility(visibility);
    }

    @Override
    public void setupPlaceData(List<Place> placeList) {
        if (placeList == null) {
            return;
        }
        mPlaceAdapter.setData(placeList);
        mPlaceAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }
}
