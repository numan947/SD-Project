package com.example.executor;

/**
 * Created by numan947 on 4/29/17.
 */

//a thread executor that can be used to execute a runnable in a separate thread
public interface ThreadExecutor {

    void execute(Runnable runnable);
}
