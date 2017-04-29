package numan947.com.bizzybay;

import android.os.Handler;
import android.os.Looper;

import com.example.executor.PostExecutionThread;


/**
 * Created by numan947 on 4/29/17.
 */

public class MainThread implements PostExecutionThread {


    //lazy holder singleton pattern
    public static class LazyHolderClass{
        private static final MainThread MAIN_THREAD = new MainThread();
    }

    public static MainThread newInstance()
    {
        return LazyHolderClass.MAIN_THREAD;
    }


    //handler helps posting runnable on the ui thread
    private final Handler handler;

    // get the main thread and store inside the handler
    private MainThread()
    {
        this.handler = new Handler(Looper.getMainLooper());
    }



    @Override
    public void post(Runnable runnable) {
        if(runnable == null)
            throw new IllegalArgumentException("Runnable to execute can't be null");

        //run the runnable inside MainApplicationThread
        handler.post(runnable);
    }
}
