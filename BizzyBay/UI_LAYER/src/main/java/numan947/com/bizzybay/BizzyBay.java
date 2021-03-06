package numan947.com.bizzybay;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by numan947 on 5/9/17.
 */

/**
 * Class for Global Application. It provides resources that won't change in the whole application
 * and whose life time is equal to the application's
 * */
public class BizzyBay extends Application {
    private static Context applicationContext;
    private static BizzyBay application;

    /**
     * Returns the application context.
     * It's called in the initializer of Data layer repositories.
     * */
    public static Context getBizzyBayApplicationContext()
    {
        return applicationContext;
    }

    /**
     * Returns an instance of the application.**/
    public static BizzyBay getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        application = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
