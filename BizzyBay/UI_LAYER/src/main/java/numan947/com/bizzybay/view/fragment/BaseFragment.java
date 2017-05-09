package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 *
 * @author numan947
 * @since 5/1/17.
 * <br>
 * Base for all fragments.
 * Extend this fragment to implement more fragments.
 * By default {@code setRetainInstance(true)} is called.
 *
 **/

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        initializePresenter();
    }

    /**
     * Method to initialize presenter. MUST be implemented by all fragments in MVP
     * pattern.
     * */
    protected abstract void initializePresenter();


    /**
     * Method for showing simple {@link Toast} message if and when needed.
     * */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


}
