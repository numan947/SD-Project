package com.example.interactor;

import com.example.UserDetails;
import com.example.exception.ErrorBundle;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;
import com.example.repository.UserDetailsRepository;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class GetUserDetailsUseCaseImpl implements GetUserDetailsUseCase {

    private PostExecutionThread postExecutionThread;
    private ThreadExecutor threadExecutor;
    private UserDetailsRepository userDetailsRepository;


    private Callback providedCallback;
    private int userId;

    public GetUserDetailsUseCaseImpl(PostExecutionThread postExecutionThread, ThreadExecutor threadExecutor, UserDetailsRepository userDetailsRepository) {
        this.postExecutionThread = postExecutionThread;
        this.threadExecutor = threadExecutor;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public void getUserDetails(int userId, Callback providedCallback) {
        this.providedCallback = providedCallback;
        this.userId = userId;


        this.threadExecutor.execute(this);
    }

    @Override
    public void run() {
        userDetailsRepository.loadUserDetails(userId,createdCallback);
    }


    private UserDetailsRepository.UserDetailsCallback createdCallback = new UserDetailsRepository.UserDetailsCallback() {
        @Override
        public void onUserDetailsLoaded(final UserDetails userDetails) {
            postExecutionThread.post(new Runnable() {
                @Override
                public void run() {
                    providedCallback.onUserDetailsLoaded(userDetails);
                }
            });
        }

        @Override
        public void onError(final ErrorBundle errorBundle) {
            postExecutionThread.post(new Runnable() {
                @Override
                public void run() {
                    providedCallback.onError(errorBundle);
                }
            });
        }
    };
}
