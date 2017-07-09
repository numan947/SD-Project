package merchant.com.bizzybay_merchant;

import android.os.Handler;
import android.os.Looper;

import com.merchant_example.executor.PostExecutionThread;


/**
 * Created by numan947 on 4/29/17.
 */


/**
 * It's an implementation of the {@link PostExecutionThread}
 * It provides a way to post Runnables in the main application thread, i.e. UI thread.
 * */
public class MainThread implements PostExecutionThread {


    //lazy holder singleton pattern
    public static class LazyHolderClass{
        private static final MainThread MAIN_THREAD = new MainThread();
    }

    /**
     * Returns the lazily held {@link MainThread} instance
     * It's a singleton.
     * */
    public static MainThread getInstance()
    {
        return LazyHolderClass.MAIN_THREAD;
    }


    //handler helps posting runnable on the ui thread
    /**
     * This is the {@link Handler} that's used to post Runnables to
     * Main Application Thread.
     * */
    private final Handler handler;

    /**
     * Implements the whole class as singleton by registering the {@link Handler}
     * to the main application thread.
     * */
    private MainThread()
    {
        this.handler = new Handler(Looper.getMainLooper());
    }


    /**
     * This is the method used to post {@link Runnable}s to the main application thread.
     * It takes a runnable and the {@link Handler} inside the class posts it to main application
     * thread.
     * */
    @Override
    public void post(Runnable runnable) {
        if(runnable == null)
            throw new IllegalArgumentException("Runnable to execute can't be null");

        //run the runnable inside MainApplicationThread
        handler.post(runnable);
    }
}
