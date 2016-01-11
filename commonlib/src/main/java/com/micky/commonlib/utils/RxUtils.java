package com.micky.commonlib.utils;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @Project CommonProj
 * @Packate com.micky.commonlib.utils
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-11 14:22
 * @Version 1.0
 */
public class RxUtils {

    public static void unsubscribeIfNotNull(Subscription subscription) {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    public static CompositeSubscription getNewCompositeSubIfUnsubscribed(CompositeSubscription subscription) {
        if (subscription == null || subscription.isUnsubscribed()) {
            return new CompositeSubscription();
        }

        return subscription;
    }
}