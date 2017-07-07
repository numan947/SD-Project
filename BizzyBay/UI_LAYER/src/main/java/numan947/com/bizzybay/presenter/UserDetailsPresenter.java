package numan947.com.bizzybay.presenter;

/**
 * @author numan947
 * @since 7/7/17.<br>
 **/

public class UserDetailsPresenter implements Presenter {
    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    public void initialize(int userId,int flag) { //flag indicates whether to reload from internet or just use the cached data
        //todo load user details from NET/Saved States
        if(flag==-1){//load from cache
        }
        //load from net
    }
}
