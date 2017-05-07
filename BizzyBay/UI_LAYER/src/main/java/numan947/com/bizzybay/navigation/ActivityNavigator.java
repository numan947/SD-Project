package numan947.com.bizzybay.navigation;

import android.content.Context;
import android.content.Intent;

import numan947.com.bizzybay.view.activity.DetailsProductActivity;
import numan947.com.bizzybay.view.activity.MainActivity;

/**
 * Created by numan947 on 5/7/17.
 */

public class ActivityNavigator {
    private Context context;

    public ActivityNavigator(Context context) {
        if(context==null)throw new IllegalArgumentException("Argument Can't be Null");
        this.context = context;
    }

    public void navigateToMainActivity(int userid)
    {
        Intent callingIntent = MainActivity.getCallingIntent(context,userid);
        context.startActivity(callingIntent);
    }

    public void navigateToDetailsProductActivity(int productid,int shopid)
    {
        Intent callingIntent = DetailsProductActivity.getCallingIntent(context,productid,shopid);
        context.startActivity(callingIntent);
    }




}
