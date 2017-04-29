package com.example.executor;

/**
 * Created by numan947 on 4/29/17.
 */

//Thread available to Domain layer, so that it can access to UI thread
public interface PostExecutionThread {

    void post(Runnable runnable);
}
