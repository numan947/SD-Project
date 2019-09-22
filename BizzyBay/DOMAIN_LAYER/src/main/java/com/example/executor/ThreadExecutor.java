package com.example.executor;

/**
 *
 * @author numan947
 * @since 4/29/17.<br>
 *
 * Base for a thread executor, that the usecases use to execute their operations
 * in the freaking background thread.
 **/


public interface ThreadExecutor {

    /**
     * method for executing a freaking runnable
     * */
    void execute(Runnable runnable);
}
