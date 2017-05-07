package numan947.com.bizzybay.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;
import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ListProductModel;
import numan947.com.bizzybay.navigation.DrawerNavigator;
import numan947.com.bizzybay.view.fragment.ProductListFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener,ProductListFragment.ProductListListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerNavigator drawerNavigator;

    private ImageView settingsImageNavDrawer;
    private CircleImageView profileImageNavDrawer;
    private TextView userNameNavDrawer;
    private int navigationDrawerGravity = GravityCompat.START;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    private static final String USER_ID="numan947.com.bizzybay.view.activity.MainActivity.USER_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting up the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setting up the Navigation Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        //Setting up the NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_drawer_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);


        //binding header layout views
        settingsImageNavDrawer = (ImageView) headerView.findViewById(R.id.settings_image_nav_drawer);
        profileImageNavDrawer = (CircleImageView) headerView.findViewById(R.id.profile_image_nav_drawer);
        userNameNavDrawer = (TextView) headerView.findViewById(R.id.user_name_nav_drawer);

        //adding listener for the header views
        userNameNavDrawer.setOnClickListener(this);
        profileImageNavDrawer.setOnClickListener(this);
        settingsImageNavDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show Settings fragment
                Toast.makeText(MainActivity.this,"Show Settings Fragment",Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationDrawerGravity);
            }
        });





        //setting up the initial fragment
        drawerNavigator = new DrawerNavigator(this);
        drawerNavigator.navigateToProductListFragment(R.id.frame,getSupportFragmentManager());
        navigationView.setCheckedItem(R.id.browse_products);







    }



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))return true;
        return super.onOptionsItemSelected(item);
    }



    //Navigation View Item selected listener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.setCheckedItem(item.getItemId());

        if(item.getItemId()==R.id.browse_products){
            drawerNavigator.navigateToProductListFragment(R.id.frame,getSupportFragmentManager());
        }


        drawerLayout.closeDrawer(navigationDrawerGravity);
        return true;
    }


    @Override
    public void onBackPressed() {

        //todo gracefully handle all the cases
        if(drawerLayout.isDrawerOpen(navigationDrawerGravity))drawerLayout.closeDrawer(navigationDrawerGravity);

        else
            super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        //todo show profile details, for both profile image and profile name
        Toast.makeText(this,"Showing profile of owner",Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(navigationDrawerGravity);
    }



    @Override
    public void onProductClicked(ListProductModel model) {
        //todo show product details Activity
        Toast.makeText(this,"SHowing details activity",Toast.LENGTH_SHORT).show();
    }


    public static Intent getCallingIntent(Context context,int userid)
    {
        Intent callingIntent = new Intent(context,MainActivity.class);
        callingIntent.putExtra(USER_ID,userid);
        return callingIntent;
    }


}