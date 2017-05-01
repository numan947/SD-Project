package numan947.com.bizzybay.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import numan947.com.bizzybay.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    @BindView(R.id.navigation_drawer) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.navigation_drawer_view)NavigationView navigationView;


    ImageView settingsImageNavDrawer;
    CircleImageView profileImageNavDrawer;
    TextView userNameNavDrawer;
    int navigationDrawerGravity = GravityCompat.START;


    ActionBarDrawerToggle actionBarDrawerToggle;

    int cnt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        //setting up the Navigation Drawer
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
        drawerLayout.closeDrawer(navigationDrawerGravity);
        return true;
    }


    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(navigationDrawerGravity))drawerLayout.closeDrawer(navigationDrawerGravity);

        else
            super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        //for both profile image and profile name
        Toast.makeText(this,"Showing profile of owner",Toast.LENGTH_SHORT).show();
        drawerLayout.closeDrawer(navigationDrawerGravity);
    }
}