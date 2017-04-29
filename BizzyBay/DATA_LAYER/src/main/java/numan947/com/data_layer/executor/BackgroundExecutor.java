package numan947.com.data_layer.executor;

import com.example.executor.ThreadExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by numan947 on 4/29/17.
 */

public class BackgroundExecutor implements ThreadExecutor {

    //lazy-holder-singleton pattern
    private static class LazyHolderClass{
        private static final BackgroundExecutor BACKGROUND_EXECUTOR = new BackgroundExecutor();
    }

    public static BackgroundExecutor newInstance()
    {
        return LazyHolderClass.BACKGROUND_EXECUTOR;
    }

    private static final int INITIAL_THREAD_POOL_SIZE=3;
    private static final int MAXIMUM_THREAD_POOL_SIZE=5;


    //idle threads should wait this amount of seconds before terminating
    private static final int KEEP_ALIVE_TIME=10000;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT=TimeUnit.MILLISECONDS;


    private final BlockingQueue<Runnable>mainWorkingQueue;
    private final ThreadPoolExecutor threadPoolExecutor;


    private BackgroundExecutor()
    {
        this.mainWorkingQueue= new LinkedBlockingDeque<>();
        this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_THREAD_POOL_SIZE,MAXIMUM_THREAD_POOL_SIZE, KEEP_ALIVE_TIME,
                                    KEEP_ALIVE_TIME_UNIT,this.mainWorkingQueue);
    }


    @Override
    public void execute(Runnable runnable) {
        if(runnable == null){
            throw new IllegalArgumentException("Runnable to execute can't be null");
        }
        this.threadPoolExecutor.execute(runnable);

    }
}
