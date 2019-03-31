package com.hua.testpro2;

import android.app.Application;

import cc.rome753.activitytask.ActivityTask;

/**
 * Created by hua on 2019/3/2.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActivityTask.init(this, true);
    }
}
