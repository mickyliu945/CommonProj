package com.micky.commonproj.presenter.impl;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.micky.commonproj.BaseApplication;
import com.micky.commonproj.presenter.MainPresenter;
import com.micky.commonproj.ui.view.MainView;
import com.micky.commonproj.R;
import com.micky.commonproj.domain.service.ServiceManager;
import com.micky.commonproj.domain.service.response.GetIpInfoResponse;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Project RetrofitRxAndroidDragger2
 * @Packate com.micky.commonproj.presenter
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-22 14:33
 * @Version 1.0
 */
public class MainPresenterImpl extends BasePresenterImpl implements MainPresenter {
    private static final String TAG = "TAG";
    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void getIpInfo(String ip) {
        if (TextUtils.isEmpty(ip)) {
            Toast.makeText(BaseApplication.getContext(), R.string.input_tip_ip, Toast.LENGTH_SHORT).show();
            return;
        }
        mMainView.setAreaText("");
        mMainView.showProgress();
        ServiceManager.getInstance().getApiService().getIpInfo(ip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetIpInfoResponse>() {
                    @Override
                    public void onCompleted() {
                        mMainView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage(), e);
                        mMainView.hideProgress();
                        mMainView.setAreaText(BaseApplication.getContext().getString(R.string.network_error));
                    }

                    @Override
                    public void onNext(GetIpInfoResponse getIpInfoResponse) {
                        mMainView.setAreaText(getIpInfoResponse.data.country + " " + getIpInfoResponse.data.area);
                    }
                });
    }
}
