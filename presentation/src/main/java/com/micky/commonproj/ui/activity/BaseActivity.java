package com.micky.commonproj.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.micky.commonproj.BaseApplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseApplication.getRefWatcher().watch(this);
    }
}
