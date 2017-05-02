
package numan947.com.bizzybay.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ProductModelSingle;
import numan947.com.bizzybay.view.activity.DemoDetails;
import numan947.com.bizzybay.view.adapter.DemoListAdapter;

public class DemoFragment extends Fragment implements DemoListAdapter.Callback {

    private ArrayList<ProductModelSingle> productEntities = new ArrayList<>();
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.demo,container,false);
        listView = (ListView) view.findViewById(R.id.demo_list);

        productEntities.clear();
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.kitty);
        for(int i=0;i<20;i++)
            productEntities.add(new ProductModelSingle(""+i,""+i,"ProductTitle: "+i,"Product Description: "+i,"Product Shop: "+i,"Product Price: "+(i*100),b));


        listView.setAdapter(new DemoListAdapter(getContext(),productEntities,R.layout.product_in_list,this));


        return view;
    }


    @Override
    public void showNextActivity() {
        Intent intent = new Intent(getActivity(), DemoDetails.class);
        startActivity(intent);
    }
}
