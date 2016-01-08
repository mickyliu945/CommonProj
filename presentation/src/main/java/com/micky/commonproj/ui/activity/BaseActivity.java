package com.micky.commonproj.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.micky.commonproj.BaseApplication;
import com.micky.commonproj.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Package com.micky.commonproj.ui.activity
 * @Project CommonProj
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Team KTEAM
 * @Date 2016-01-04 21:27
 */
public class BaseActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar mToolBar;
    @Bind(R.id.tv_title) TextView mTvTitle;
    private boolean mAutoBindView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(layoutResID, false);
    }

    public void setContentView(int layoutResID, boolean hideBackButton) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initToolBar(hideBackButton);
        mAutoBindView = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAutoBindView) {
            ButterKnife.unbind(this);
        }
        BaseApplication.getRefWatcher().watch(this);
    }

    private void initToolBar(boolean hideBackButton) {
        if (mToolBar == null) {
            return;
        }
        setTitle("");

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        if (!hideBackButton) {
            mToolBar.setNavigationIcon(R.mipmap.back);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected void setTitleText(String title) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(title);
    }

    protected void setTitleRes(int resId) {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(resId);
    }

    protected void hideTitle() {
        mTvTitle.setVisibility(View.GONE);
    }
}
