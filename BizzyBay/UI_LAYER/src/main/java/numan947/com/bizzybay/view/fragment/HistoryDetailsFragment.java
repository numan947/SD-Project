package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.view.HistoryDetailsView;

/**
 * @author numan947
 * @since 5/10/17.<br>
 **/

public class HistoryDetailsFragment extends BaseFragment implements HistoryDetailsView {
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.HISTORY_DETAILS_FRAGMENT";


    public static HistoryDetailsFragment newInstance(){
        //todo add necessary arguments here
        return new HistoryDetailsFragment();
    }
    public static String getFragmentID()
    {
        return fragmentId;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_details_fragment,container,false);
        this.bindAll(view);

        return view;


    }

    private void bindAll(View view) {

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //todo setup options menu later
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
        //todo setup options menu later
    }

    @Override
    protected void initializePresenter() {

    }

    @Override
    public void showDetails() {

    }

    @Override
    public void hideDetails() {

    }

    @Override
    public void onProductNameClicked() {

    }

    @Override
    public void onShopNameClicked() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }
}
