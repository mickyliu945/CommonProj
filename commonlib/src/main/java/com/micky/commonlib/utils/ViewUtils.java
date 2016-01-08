package com.micky.commonlib.utils;

/**
 * @Project CommonProj
 * @Packate com.micky.commonlib.utils
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-08 14:51
 * @Version 1.0
 */
public class ViewUtils {
    /**防止连续点击*/
    private static long lastClickTime;
    public static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( timeD > 0 && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
