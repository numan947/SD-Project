package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by numan947 on 5/1/17.
 */

public class BrowseProductBaseFragment extends RecyclerViewBaseFragment {


    public static BrowseProductBaseFragment newInstance()
    {
        BrowseProductBaseFragment browseProductBaseFragment = new BrowseProductBaseFragment();
        return browseProductBaseFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.initialze();
    }



    private void initialze() {
        this.setRetainInstance(true);

    }
}
