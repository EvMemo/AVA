package com.example.ava;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {
    public MyApplication() {

    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }


}
