package com.tugba.cevizci.spacex;

import android.app.Application;

public class SpaceXApp extends Application {
    private static SpaceXApp application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static SpaceXApp Instance() {
        return application;
    }
}
