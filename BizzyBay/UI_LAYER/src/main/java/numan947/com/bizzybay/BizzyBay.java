package numan947.com.bizzybay;

import android.app.Application;
import android.content.Context;

/**
 * Created by numan947 on 5/9/17.
 */

public class BizzyBay extends Application {
    private static Context applicationContext;
    private static BizzyBay application;

    public static Context getBizzyBayApplicationContext()
    {
        return applicationContext;
    }

    public static BizzyBay getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        application = this;
    }
}
