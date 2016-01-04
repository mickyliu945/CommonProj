package com.micky.commonproj.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
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

    @Bind(R.id.recyvler_view) RecyclerView mRecyclerView;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;
    @Bind(R.id.tv_date) TextView mTvDate;
    @Bind(R.id.tv_city) TextView mTvCity;
    @Bind(R.id.tv_pm25) TextView mTvPm25;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    private MainPresenter mMainPresenter;
    private WeatherExtraAdapter mWeatherExtraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mMainPresenter = new MainPresenterImpl(this);
        initView();
        mMainPresenter.getWeatherData("成都");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new ItemDecoration(this));
        mWeatherExtraAdapter = new WeatherExtraAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mWeatherExtraAdapter);
    }

    public void setupData(WeatherResponse weatherResponse) {
        if (weatherResponse == null) return;
        mTvDate.setText(weatherResponse.date);
        if (weatherResponse.results != null && weatherResponse.results.size() > 0) {
            WeatherResult result = weatherResponse.results.get(0);
            mTvCity.setText(getString(R.string.city, result.currentCity));
            mTvPm25.setText(getString(R.string.pm25, result.pm25));
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
