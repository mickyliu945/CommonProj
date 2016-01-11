package com.micky.commonproj.presenter.impl;

import com.micky.commonlib.utils.RxUtils;
import com.micky.commonproj.presenter.BasePresenter;

import rx.subscriptions.CompositeSubscription;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.commonproj.presenter
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 14:34
 * @Version 0.1
 */
public class BasePresenterImpl implements BasePresenter {
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    @Override
    public void onCreate() {
        mSubscriptions = RxUtils.getNewCompositeSubIfUnsubscribed(mSubscriptions);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mSubscriptions.unsubscribe();
    }
}
