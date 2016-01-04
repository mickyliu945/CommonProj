package com.micky.commonproj.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.micky.commonproj.presenter.MainPresenter;
import com.micky.commonproj.ui.view.MainView;
import com.micky.commonproj.R;
import com.micky.commonproj.presenter.impl.MainPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.presentation
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 12:22
 * @Version 1.0
 */
public class MainActivity extends AppCompatActivity implements MainView {

    @Bind(R.id.et_ip) EditText mEtIp;
    @Bind(R.id.tv_content) TextView mTvContent;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mMainPresenter = new MainPresenterImpl(this);
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

    @OnClick(R.id.fab) void onFabCLick(View view) {
        mMainPresenter.getIpInfo(mEtIp.getText().toString());
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAreaText(String text) {
        mTvContent.setText(text);
    }
}
