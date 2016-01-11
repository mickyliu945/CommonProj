package com.micky.commonlib.utils;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @Project CommonProj
 * @Packate com.micky.commonlib.utils
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-11 15:03
 * @Version 1.0
 */
public class RxBus {

    private final PublishSubject<BusEvent> publishSubject =  PublishSubject.create();
    private final Subject<BusEvent, BusEvent> mBus = new SerializedSubject<BusEvent, BusEvent>(publishSubject);

    private static class RxBusHolder {
        private static final RxBus INSTANCE = new RxBus();
    }

    private RxBus() {}

    public static final RxBus getInstance() {
        return RxBusHolder.INSTANCE;
    }

    public void send(BusEvent event) {
        mBus.onNext(event);
    }

    public Observable<BusEvent> toObserverable() {
        return mBus;
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public static class BusEvent {}
}