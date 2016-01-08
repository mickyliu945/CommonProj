package com.micky.commonproj.domain.model;

/**
 * @Project CommonProj
 * @Packate com.micky.commonproj.domain.model
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2016-01-08 13:36
 * @Version 1.0
 */
public class Place implements Comparable<Place>{

    public String label = "";
    public String name = "";
    public String pinyin = "";


    @Override
    public int hashCode() {
        return pinyin.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Place) {
            return pinyin.equals(((Place)o).pinyin);
        }
        return false;
    }

    @Override
    public int compareTo(Place another) {
        return pinyin.compareTo(another.pinyin);
    }
}
