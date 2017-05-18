package numan947.com.bizzybay.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ShopDetailsModelForMap;

/**
 * @author numan947
 * @since 5/18/17.<br>
 **/

public class GenericMapFragment extends Fragment implements OnMapReadyCallback {
    private static final String MODEL = "numan947.com.bizzybay.view.fragment.GenericMapFragment.MODEL";
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.GenericMapFragment.GENERIC_MAP_FRAGMENT";


    private ShopDetailsModelForMap model;


    public static String getFragmentId()
    {
        return fragmentId;
    }
    public static GenericMapFragment newInstance(ShopDetailsModelForMap model)
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable(MODEL,model);

        GenericMapFragment genericMapFragment = new GenericMapFragment();
        genericMapFragment.setArguments(bundle);
        return genericMapFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generic_map_fragment,container,false);
        this.setupAll();
        this.getParameters();
        return view;
    }

    private void getParameters() {
        Bundle b = getArguments();
        this.model = b.getParcelable(MODEL);
    }

    private void setupAll() {
        FragmentManager myFM = getChildFragmentManager();
        final SupportMapFragment SMF = (SupportMapFragment) myFM.findFragmentById(R.id.generic_map);
        SMF.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(model.getLatLng())
        .draggable(false).snippet(model.getSnippet()).title(model.getTitle()));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(model.getLatLng(),16.5f));
    }
}
