package com.example.executor;

/**
 *
 * @author numan947
 * @since 4/29/17.<br>
 *
 * Interface for a thread implementation so that runnables can be posted in that
 * thread.
 **/

//Thread available to Domain layer, so that it can access to UI thread
public interface PostExecutionThread {

    /**
     * Posts runnable in the thread.
     * */
    void post(Runnable runnable);
}
