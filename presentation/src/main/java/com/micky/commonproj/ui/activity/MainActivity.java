package com.micky.commonproj.ui.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.micky.commonproj.domain.model.WeatherResult;
import com.micky.commonproj.domain.service.response.WeatherResponse;
import com.micky.commonproj.presenter.MainPresenter;
import com.micky.commonproj.ui.adapter.WeatherExtraAdapter;
import com.micky.commonproj.ui.view.ItemDecoration;
import com.micky.commonproj.ui.view.MainView;
import com.micky.commonproj.R;
import com.micky.commonproj.presenter.impl.MainPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    @Bind(R.id.rv_left) RecyclerView mRvWeatherData;
    @Bind(R.id.recyvler_view) RecyclerView mRvWeatherExtra;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;
    @Bind(R.id.tv_date) TextView mTvDate;
    @Bind(R.id.tv_city) TextView mTvCity;
    @Bind(R.id.tv_pm25) TextView mTvPm25;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    private MainPresenter mMainPresenter;
    private WeatherExtraAdapter mWeatherExtraAdapter;
    private WeatherDataAdapter mWeatherDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        setTitle("");
        mMainPresenter = new MainPresenterImpl(this);
        initView();
        mMainPresenter.getWeatherData("成都");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mRvWeatherData.setHasFixedSize(true);
        mRvWeatherData.addItemDecoration(new ItemDecoration(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvWeatherData.setLayoutManager(linearLayoutManager);
        mWeatherDataAdapter = new WeatherDataAdapter();
        mRvWeatherData.setAdapter(mWeatherDataAdapter);

        mRvWeatherExtra.setHasFixedSize(true);
        mRvWeatherExtra.setLayoutManager(linearLayoutManager);
        mWeatherExtraAdapter = new WeatherExtraAdapter();
        mRvWeatherExtra.setAdapter(mWeatherExtraAdapter);
    }

    public void setupData(WeatherResponse weatherResponse) {
        if (weatherResponse == null) return;
        mTvDate.setText(weatherResponse.date);
        if (weatherResponse.results != null && weatherResponse.results.size() > 0) {
            WeatherResult result = weatherResponse.results.get(0);
            mTvCity.setText(getString(R.string.city, result.currentCity));
            mTvPm25.setText(getString(R.string.pm25, result.pm25));

            mWeatherDataAdapter.setData(result.weather_data);
            mWeatherDataAdapter.notifyDataSetChanged();

            mWeatherExtraAdapter.setData(result.index);
            mWeatherExtraAdapter.notifyDataSetChanged();
        }
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
