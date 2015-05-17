package com.elkriefy.apps.memoryleakerdemo;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by MaTriXy on 5/16/15.
 */
public class MemoryleakDemo  extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        MemoryleakDemo application;
        if (context!=null) {
            if (context instanceof MemoryleakDemo)
            {
                 application = (MemoryleakDemo) context;
            }
            else {
                 application = (MemoryleakDemo) ((AppCompatActivity) context).getApplication();
            }
            return application.refWatcher;
        }
        return null;
    }

    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

}
