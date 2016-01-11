package com.micky.commonproj.domain.repository;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.micky.commonproj.domain.model.Place;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.repository
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-08 13:36
 * @Version 1.0
 */
public class PlaceRepository {

    public Observable<List<Place>> getPlaceList(final Context context) {
        return Observable.create(new Observable.OnSubscribe<List<Place>>() {
            @Override
            public void call(Subscriber<? super List<Place>> subscriber) {
                try {
                    AssetManager assertManager = context.getAssets();
                    InputStream inputStream = assertManager.open("place");
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] data = new byte[1024];
                    int count = -1;
                    while((count = inputStream.read(data,0, 1024)) != -1) {
                        outStream.write(data, 0, count);
                    }
                    String json =  new String(outStream.toByteArray(),"UTF-8");
                    Gson gson = new GsonBuilder().create();
                    List<Place> placeList = gson.fromJson(json, new TypeToken<List<Place>>() {}.getType());
                    subscriber.onNext(placeList);
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }
}
