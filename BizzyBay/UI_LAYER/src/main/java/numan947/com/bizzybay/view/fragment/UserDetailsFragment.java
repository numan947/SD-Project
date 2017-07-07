package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.executor.PostExecutionThread;
import com.example.executor.ThreadExecutor;

import de.hdodenhof.circleimageview.CircleImageView;
import numan947.com.bizzybay.MainThread;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.UserDetailsModel;
import numan947.com.bizzybay.presenter.UserDetailsPresenter;
import numan947.com.bizzybay.view.UserDetailsView;
import numan947.com.bizzybay.view.component.MyPasswordTransformationMethod;
import numan947.com.data_layer.cache.TestUserCacheImpl;
import numan947.com.data_layer.cache.UserCache;
import numan947.com.data_layer.executor.BackgroundExecutor;

/**
 * @author numan947
 * @since 7/6/17.<br>
 **/

public class UserDetailsFragment extends BaseFragment implements UserDetailsView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.UserDetailsFragment.USER_DETAILS_FRAGMENT";
    private static final String USER_ID= "numan947.com.bizzybay.view.fragment.UserDetailsFragment.USER_ID";


    public static String getFragmentId(){return fragmentId;}


    public interface UserDetailsListener{
        void finishActivity();
        //todo add the things that can be edited and setup requests accordingly
    }

    private int userId;
    private UserDetailsModel userDetailsModel;

    private UserDetailsListener actvityListener;
    private UserDetailsPresenter userDetailsPresenter;
    private ImageView userImageBackground;

    private NestedScrollView nestedScrollView;

    private Toolbar toolbar;
    private TextView userAccountName;
    private CircleImageView userImage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout shoppingBagLL;


    private LinearLayout likedItemsLL;
    private LinearLayout paymentHistoryLL;
    private LinearLayout phoneLL;


    private TextView phoneTV;
    private LinearLayout userNameLL;
    private TextView userNameTV;
    private LinearLayout emailLL;

    private TextView emailTV;
    private LinearLayout passwordLL;
    private TextView passwordTV;
    private LinearLayout whatsappLL;
    private TextView whatsappTV;
    private LinearLayout facebookLL;
    private TextView facebookTV;


    private RelativeLayout retryView;
    private RelativeLayout loadingView;
    private Button retryButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof UserDetailsListener)
            this.actvityListener = (UserDetailsListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_info_and_settings_page_fragment,container,false);

        this.bindAll(view);
        this.setupToolbar();
        this.addListeners();


        return view;
    }

    private void bindAll(View view) {

        this.nestedScrollView = (NestedScrollView) view.findViewById(R.id.user_info_and_settings_frag_NSV);

        this.toolbar = (Toolbar) view.findViewById(R.id.user_info_and_settings_frag_toolbar);
        this.userImageBackground = (ImageView) view.findViewById(R.id.user_info_and_settings_frag_CTL_bg);
        this.userImage = (CircleImageView) view.findViewById(R.id.user_info_and_settings_frag_CircleImageView);
        this.userAccountName =(TextView)view.findViewById(R.id.user_info_and_settings_frag_accountname);
        this.swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.user_info_and_settings_frag_SRL);


        this.shoppingBagLL = (LinearLayout)view.findViewById(R.id.user_settings_shopping_bag_LL);
        this.likedItemsLL = (LinearLayout)view.findViewById(R.id.user_settings_liked_items_LL);
        this.paymentHistoryLL = (LinearLayout)view.findViewById(R.id.user_settings_payment_history_LL);


        this.phoneLL = (LinearLayout)view.findViewById(R.id.user_settings_phone_LL);
        this.phoneTV = (TextView)view.findViewById(R.id.user_settings_phone);
        this.userNameLL = (LinearLayout)view.findViewById(R.id.user_settings_username_LL);
        this.userNameTV = (TextView)view.findViewById(R.id.user_settings_username);


        this.emailLL = (LinearLayout)view.findViewById(R.id.user_settings_email_LL);
        this.emailTV = (TextView)view.findViewById(R.id.user_settings_email);
        this.passwordLL = (LinearLayout)view.findViewById(R.id.user_settings_password_LL);
        this.passwordTV = (TextView)view.findViewById(R.id.user_settings_password);
        this.whatsappLL = (LinearLayout)view.findViewById(R.id.user_settings_whatsapp_LL);
        this.whatsappTV = (TextView)view.findViewById(R.id.user_settings_whatsapp);
        this.facebookLL = (LinearLayout)view.findViewById(R.id.user_settings_facebook_LL);
        this.facebookTV = (TextView)view.findViewById(R.id.user_settings_facebook);

        this.retryButton = (Button)view.findViewById(R.id.bt_retry);
        this.retryView = (RelativeLayout)view.findViewById(R.id.rl_retry);
        this.loadingView = (RelativeLayout)view.findViewById(R.id.rl_progress);
    }

    private void addListeners() {
        //todo add listeners here for different type of user interactions
    }

    private void setupToolbar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //todo add items to toolbar here
        //todo add handling for toolbar item selections

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState==null){
            this.getParameters();
            this.userDetailsPresenter.initialize(userId,-1);
        }
        //otherwise the whole states should be automatically restored
    }


    private void getParameters() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            this.userId = bundle.getInt(USER_ID,-1);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userDetailsPresenter.onResume();
    }

    @Override
    public void onPause() {
        userDetailsPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void initializePresenter() {
        ThreadExecutor threadExecutor = BackgroundExecutor.getInstance();
        PostExecutionThread postExecutionThread = MainThread.getInstance();

        //todo initialize serializer

        //todo add real cache instead of test

        UserCache userCache = TestUserCacheImpl.getInstance();



    }

    public static UserDetailsFragment newInstance(int userId)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(USER_ID,userId);

        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
        userDetailsFragment.setArguments(bundle);
        return userDetailsFragment;
    }

    @Override
    public void renderUserDetails(UserDetailsModel userDetailsModel) {
        this.userDetailsModel = userDetailsModel;

        this.loadImage(userDetailsModel.getUserImageUrl());

        this.userAccountName.setText(userDetailsModel.getAccountName());
        this.emailTV.setText(userDetailsModel.getEmail());
        this.phoneTV.setText(userDetailsModel.getPhone());
        this.userNameTV.setText(userDetailsModel.getUserName());

        this.passwordTV.setTransformationMethod(new MyPasswordTransformationMethod());
        this.passwordTV.setText(userDetailsModel.getPassword());

        this.whatsappTV.setText(userDetailsModel.getWhatsapp());
        this.facebookTV.setText(userDetailsModel.getFacebook());
    }

    private void loadImage(String userImageUrl) {
        Glide.with(this).load(userImageUrl)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade().fitCenter()
                .into(this.userImage);
    }

    @Override
    public void showLoading() {
        this.swipeRefreshLayout.setRefreshing(true);
    }

    public void hideUserDetails() {
        this.nestedScrollView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        this.swipeRefreshLayout.setRefreshing(false);
    }

    public void showUserDetails() {
        this.nestedScrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRetry(){
        this.retryView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.retryView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        //todo what to do when there's error?
    }


}
