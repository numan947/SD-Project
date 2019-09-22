package merchant.com.bizzybay_merchant.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import merchant.com.bizzybay_merchant.R;

/**
 *
 * @author numan947
 * @since 5/6/17.<br>
 *
 * Base Class for all the Activities.
 * Extend this class for implementing more activities.
 * Basically this activity overrides the {@code onCreate()} method and
 * sets up translucent status bar.
 *
 **/

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();

        if(window!=null){
            if( Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
                setTranslucentStatusBarLollipop(window);
            else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
                setTranslucentStatusBarKitkat(window);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setTranslucentStatusBarLollipop(Window window){
        window.setStatusBarColor(getResources().getColor(R.color.transparent,null));
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatusBarKitkat(Window window)
    {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }




}
