package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import numan947.com.bizzybay.R;

/**
 *
 * @author numan947
 * @since 5/7/17.<br>
 *
 * Fragment for the {@link android.support.v4.view.ViewPager} element of the
 * {@link ProductDetailsFragment}
 *
 **/

public class ProductDetailsViewPagerFragment extends Fragment {
    private static final String KEY = "numan947.com.bizzybay.view.fragment.ProductDetailsViewPagerFragment.KEY";
    private String url;

    public static ProductDetailsViewPagerFragment newInstance(String url)
    {
        ProductDetailsViewPagerFragment fragment = new ProductDetailsViewPagerFragment();

        Bundle b = new Bundle();
        b.putString(KEY,url);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        if(b!=null)
            url = b.getString(KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_pager_fragment,container,false);

        ImageView imageView = (ImageView) v.findViewById(R.id.image_view);

        Glide.with(this).load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .crossFade().fitCenter()
                .into(imageView);
        return v;
    }
}
