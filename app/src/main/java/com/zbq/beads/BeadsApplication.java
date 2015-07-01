package com.zbq.beads;

import android.content.Context;
import android.os.Handler;


/**
 * Created by a123 on 15-7-1.
 */
public class BeadsApplication extends android.app.Application{

    public static volatile Handler applicationHandler = null;
    private static final String TAG="BeadsApplication";
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        applicationHandler = new Handler(applicationContext.getMainLooper());
    }
}
