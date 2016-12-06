package com.example.fx50j.zhdailynews.Beans;

import android.graphics.Bitmap;

/**
 * Created by FX50J on 2016/11/20.
 */

public class Pic_Bitmap {
    private String url;
    private Bitmap mbitmap;
    public Pic_Bitmap(String url,Bitmap bitmap){
        this.mbitmap = bitmap;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getMbitmap() {
        return mbitmap;
    }

    public void setMbitmap(Bitmap mbitmap) {
        this.mbitmap = mbitmap;
    }
}
