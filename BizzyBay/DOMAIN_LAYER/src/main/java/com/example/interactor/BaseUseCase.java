package com.example.interactor;

/**
 *
 * @author numan947
 * @since 5/1/17.<br>
 *
 * Base for all of the use case.
 **/

interface BaseUseCase extends Runnable {
    /**
     * Runs the Usecase in the background thread.
     * */
    void run();
}
