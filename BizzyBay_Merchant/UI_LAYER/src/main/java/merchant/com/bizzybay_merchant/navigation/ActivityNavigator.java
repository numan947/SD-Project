package merchant.com.bizzybay_merchant.navigation;

import android.content.Context;
import android.content.Intent;

import merchant.com.bizzybay_merchant.view.activity.DetailsProductActivity;
import merchant.com.bizzybay_merchant.view.activity.HistoryDetailsActivity;
import merchant.com.bizzybay_merchant.view.activity.MainActivity;
import merchant.com.bizzybay_merchant.view.activity.ShopDetailsActivity;
import merchant.com.bizzybay_merchant.view.activity.UserDetailsActivity;

/**
 *
 * @author numan947
 * @since 5/7/17.<br>
 *
 * This class provides navgation from one activity to another.
 **/


//todo change activity navigation and make it a singleton too
public class ActivityNavigator {
    private Context context;

    public ActivityNavigator(Context context) {
        if(context==null)throw new IllegalArgumentException("Argument Can't be Null");
        this.context = context;
    }

    /**
     * Navigation to {@link MainActivity}
     * */
    public void navigateToMainActivity(int userid)
    {
        Intent callingIntent = MainActivity.getCallingIntent(context,userid);
        context.startActivity(callingIntent);
    }

    /**
     * Navigation to {@link DetailsProductActivity}
     * */
    public void navigateToDetailsProductActivity(int productid,int shopid)
    {
        Intent callingIntent = DetailsProductActivity.getCallingIntent(context,productid,shopid);
        context.startActivity(callingIntent);
    }


    public void navigateToDetailsHistoryActivity(int orderId, int shopId, int productId) {
        Intent callingIntent = HistoryDetailsActivity.getCallingIntent(context,orderId,shopId,productId);
        context.startActivity(callingIntent);
    }

    public void navigateToShopDetailsActivity(int shopId) {
        Intent callingIntent = ShopDetailsActivity.getCallingIntent(context,shopId);
        context.startActivity(callingIntent);
    }

    public void navigateToUserDetailsActivity(int userId) {
        Intent callingIntent = UserDetailsActivity.getCallingIntent(context,userId);
        context.startActivity(callingIntent);
    }
}
