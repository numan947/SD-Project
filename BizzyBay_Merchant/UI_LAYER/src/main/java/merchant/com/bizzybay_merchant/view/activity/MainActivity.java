package merchant.com.bizzybay_merchant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import merchant.com.bizzybay_merchant.R;
import merchant.com.bizzybay_merchant.model.CartListModel;
import merchant.com.bizzybay_merchant.navigation.ActivityNavigator;
import merchant.com.bizzybay_merchant.navigation.DrawerNavigator;
import merchant.com.bizzybay_merchant.view.fragment.CartListFragment;
import merchant.com.bizzybay_merchant.view.fragment.HistoryListFragment;
import merchant.com.bizzybay_merchant.view.fragment.ProductListFragment;
import merchant.com.bizzybay_merchant.view.fragment.ShopListFragment;
import merchant.com.bizzybay_merchant.view.fragment.WishListFragment;


/**
 * Main Activity for the application.
 * This activity holds the {@link DrawerLayout} and {@link NavigationView}
 * which are used to navigate through the main parts of the application.
 *
 * This activity implements the related Listeners of the Fragments it can hold. So that
 * it can respond to activity changing requests.
 *
 * */
@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends BaseActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener,
        ProductListFragment.ProductListListener,
        HistoryListFragment.HistoryListListener,
        ShopListFragment.ShopListListener,
        CartListFragment.CartListListener,
        WishListFragment.WishListListener{


    private final int navigationDrawerGravity = GravityCompat.START;

    //fields passed as parameters to this activity
    private static final String USER_ID="numan947.com.bizzybay.view.activity.MainActivity.USER_ID";
    private static final String CURRENT_FRAGMENT="numan947.com.bizzybay.view.activity.MainActivity.CURRENT_FRAGMENT";
    private static final String activityId = "numan947.com.bizzybay.view.activity.MainActivity.MAIN_ACTIVITY";

    private static final String cartListFragmentId = CartListFragment.getFragmentId();
    private static final String historyListFragmentId = HistoryListFragment.getFragmentID();
    private static final String productListFragmentId = ProductListFragment.getFragmentID();
    private static final String shopListFragmentId = ShopListFragment.getFragmentId();
    private static final String wishListFragmentId = WishListFragment.getFragmentId();

    //todo add other fragments here


    private int userId;//todo or should we use the whole user class

    //fields to bind the view
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ImageView settingsImageNavDrawer;
    private CircleImageView profileImageNavDrawer;
    private TextView userNameNavDrawer;

    //fields to setup the view properly
    private DrawerNavigator drawerNavigator;
    private ActivityNavigator activityNavigator;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //to keep track of the current fragment
    private int currentFragment=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getParameters();
        this.bindViews();
        this.setupToolbar();
        this.setupNavigationDrawer();
        this.setupNavigationView();

        //setup the activity navigator
        activityNavigator = new ActivityNavigator(this);



        //setting up the initial fragment
        drawerNavigator = DrawerNavigator.getInstance();

        //show the list by default
        if(currentFragment==-1)
            this.showProductListFragment();
        else
            this.showSuitableFragment(currentFragment);



        loadDetailsShopActivity();


    }

    private void loadDetailsShopActivity() {
        activityNavigator.navigateToShopDetailsActivity(1234);
    }

    private void showSuitableFragment(int switchVariable) {
        switch (switchVariable){
           case R.id.history:
               showHistoryListFragment();
               break;
            case R.id.productlist:
                showProductListFragment();
                break;
            case R.id.shoplist:
                showShopListFragment();
                break;
            case R.id.shoppingbag:
                showShoppingBagFragment();
                break;
            case R.id.wishlist:
                showWishListFragment();
                break;

            //todo handle rest

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(outState!=null)
            outState.putInt(CURRENT_FRAGMENT,currentFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState !=null) {
            currentFragment = savedInstanceState.getInt(CURRENT_FRAGMENT, -1);
            this.showSuitableFragment(currentFragment);
        }
    }

    /**
     * Method for getting parameters from the {@link Intent}
     * */
    private void getParameters() {
        userId = getIntent().getIntExtra(USER_ID,-1);

        //todo we need to load the userDetails from cache here
        userId = 12345;
    }


    /**
     * Binds the views related to this activity.
     * */
    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation_drawer_view);
    }


    /**
     * Sets up the navigation view for this activity.
     * */
    private void setupNavigationView() {
        //Setting up the NavigationView
        navigationView.setNavigationItemSelectedListener(this);

        // 0 is the first item, aka the header layout
        View headerView = navigationView.getHeaderView(0);

        this.bindNavigationViewHeader(headerView);

        //adding listener for the header views
        userNameNavDrawer.setOnClickListener(this);
        profileImageNavDrawer.setOnClickListener(this);
    }


    /**
     * Binds the navigation view's header with the resources.
     * */
    private void bindNavigationViewHeader(View headerView) {
        //binding header layout views
        //settingsImageNavDrawer = (ImageView) headerView.findViewById(R.id.settings_image_nav_drawer);
        profileImageNavDrawer = (CircleImageView) headerView.findViewById(R.id.profile_image_nav_drawer);
        userNameNavDrawer = (TextView) headerView.findViewById(R.id.user_name_nav_drawer);
    }

    /**
     * Sets up the navigation drawer with {@link ActionBarDrawerToggle}
     * */
    private void setupNavigationDrawer() {
        //setting up the Navigation Drawer
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                //todo do something here
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //todo do something here
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    /**
     * Sets up the toolbar as well as the components related to the
     * {@link Toolbar}.
     * */
    private void setupToolbar() {
        //setting up the toolbar
        setSupportActionBar(toolbar);
        //todo add listeners for toolbar items

        //setting the toolbar's Button
        assert getSupportActionBar()!=null;
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    /**
     * This method is called as a part of setting up the {@link ActionBarDrawerToggle} with
     * the navigation drawer.
     * */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    /**
     * This method is called as a part of setting up the {@link ActionBarDrawerToggle} with
     * the navigation drawer.
     * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * See the parent.
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);
    }



    //Navigation View Item selected listener
    /**
     * See the parent.
     * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        this.showSuitableFragment(item.getItemId());
        drawerLayout.closeDrawer(navigationDrawerGravity);
        return true;
    }

    private void showShopListFragment() {
        drawerNavigator.navigateToShopListFragment(getSupportFragmentManager(),R.id.main_activity_frame);
        navigationView.setCheckedItem(R.id.shoplist);
        currentFragment =R.id.shoplist;
    }

    /**
     * Method for showing the first fragment.
     * */
    private void showProductListFragment() {
        drawerNavigator.navigateToProductListFragment(R.id.main_activity_frame,getSupportFragmentManager());
        navigationView.setCheckedItem(R.id.productlist);
        currentFragment = R.id.productlist;
    }


    /**
     *
     * Method for showing the history list fragment
     * */
    private void showHistoryListFragment() {
        drawerNavigator.navigateToHistoryListFragment(R.id.main_activity_frame,getSupportFragmentManager());
        navigationView.setCheckedItem(R.id.history);
        currentFragment = R.id.history;
    }


    private void showShoppingBagFragment() {
        drawerNavigator.navigateToShoppingBagFragment(R.id.main_activity_frame,getSupportFragmentManager());
        navigationView.setCheckedItem(R.id.shoppingbag);
        currentFragment = R.id.shoppingbag;
    }


    private void showWishListFragment() {
        drawerNavigator.navigateToWishListFragment(R.id.main_activity_frame,getSupportFragmentManager());
        navigationView.setCheckedItem(R.id.wishlist);
        currentFragment = R.id.wishlist;
    }

    /**
     * See the parent.
     * */
    @Override
    public void onBackPressed() {

        //todo gracefully handle all the cases
        if(drawerLayout.isDrawerOpen(navigationDrawerGravity))
            drawerLayout.closeDrawer(navigationDrawerGravity);
        else if(currentFragment!=R.id.productlist){
            drawerNavigator.navigateToProductListFragment(R.id.main_activity_frame,getSupportFragmentManager());
            navigationView.setCheckedItem(R.id.productlist);
            currentFragment = R.id.productlist;
        }
        else
            super.onBackPressed();
    }
    /**
     * See the parent.
     * */
    @Override
    public void onClick(View v) {
        //todo show profile details, for both profile image and profile name
//        Toast.makeText(this,"Showing profile of owner",Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(navigationDrawerGravity);

        switch (v.getId()){
            case R.id.user_name_nav_drawer :
                activityNavigator.navigateToUserDetailsActivity(userId);
                break;
            case R.id.profile_image_nav_drawer:
                activityNavigator.navigateToUserDetailsActivity(userId);
                break;
        }
    }


    @Override
    public void onHomeButtonPressed() {
        //do nothing
    }

    /**
     * Static method for getting an Intent to launch this activity.
     * It's usually called from {@link ActivityNavigator} and provided
     * with necessary parameters so that those parameters are put to the
     * {@link Intent}
     * */
    public static Intent getCallingIntent(Context context,int userid)
    {
        Intent callingIntent = new Intent(context,MainActivity.class);
        callingIntent.putExtra(USER_ID,userid);
        return callingIntent;
    }


    @Override
    public void onHistoryItemClicked(int orderId, int shopId, int productId) {
        activityNavigator.navigateToDetailsHistoryActivity(orderId,shopId,productId);

    }

    @Override
    public void onShopClicked(int shopId,String fragmentId) {
        //todo do something with the fragmentID
        activityNavigator.navigateToShopDetailsActivity(shopId);
    }

    @Override
    public void onProductClicked(int productId,int shopId,String fragmentId) {
        //go to product details
        //todo do something with the fragmentId, or not may be....?
        activityNavigator.navigateToDetailsProductActivity(productId,shopId);
    }

    @Override
    public void onCheckoutButtonClicked(CartListModel cartListModel) {
        //todo navigate to checkout activity

        Snackbar.make(drawerLayout,"This will open up checkout activity",Snackbar.LENGTH_SHORT).setActionTextColor(Color.CYAN)
                .show();
    }
}