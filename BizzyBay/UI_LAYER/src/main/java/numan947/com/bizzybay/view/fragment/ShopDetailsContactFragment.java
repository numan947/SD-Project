package numan947.com.bizzybay.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import numan947.com.bizzybay.R;
import numan947.com.bizzybay.model.ShopDetailsModel;
import numan947.com.bizzybay.model.ShopDetailsModelForMap;

/**
 * @author numan947
 * @since 5/16/17.<br>
 **/

public class ShopDetailsContactFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    private static final String MODEL = "numan947.com.bizzybay.view.fragment.ShopDetailsContactFragment.MODEL";
    private static final String fragmentId = "numan947.com.bizzybay.view.fragment.ShopDetailsContactFragment.SHOP_DETAILS_CONTACT";


    public interface ShopDetailsContactFragmentListener{
        void onWhatsAppButtonClicked(); //todo add parameters
        void onFacebookButtonClicked();
        void onMapClicked(ShopDetailsModelForMap shopDetailsModelForMap);
    }


    public static ShopDetailsContactFragment newInstance(ShopDetailsModel shopDetailsModel)
    {
        Bundle bundle  =  new Bundle();
        bundle.putParcelable(MODEL,shopDetailsModel);

        ShopDetailsContactFragment fragment = new ShopDetailsContactFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public static String getFragmentId()
    {
        return fragmentId;
    }

    private MapView mapView;
    private GoogleMap googleMap;
    private MarkerOptions markerOptions;
    private LinearLayout mapViewLL;

    private Button whatsappButton;
    private Button facebookButton;


    private TextView shopDetailsShopName;

    private TextView shopDetailsPhone;
    private TextView shopDetailsAuxiliaryPhone;


    private TextView shopDetailsAddressLine1;
    private TextView shopDetailsAddressLine2;
    private TextView shopDetailsAddressLine3;

    private TextView shopDetailsCity;
    private TextView shopDetailsZipCode;




    private ShopDetailsModel shopDetailsModel;
    private ShopDetailsContactFragmentListener shopDetailsContactFragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ShopDetailsContactFragmentListener)
            shopDetailsContactFragmentListener = (ShopDetailsContactFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_details_contact,container,false);

        this.bindAll(view);
        this.getParameters();
        this.setupMapView(savedInstanceState);
        this.renderAll();
        this.addListeners();

        return view;
    }

    private void addListeners() {
        this.facebookButton.setOnClickListener(this);
        this.whatsappButton.setOnClickListener(this);
        this.mapViewLL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.shop_details_whatsapp_button:
                this.shopDetailsContactFragmentListener.onWhatsAppButtonClicked();
                break;
            case R.id.shop_details_facebook_button:
                this.shopDetailsContactFragmentListener.onFacebookButtonClicked();
                break;
        }
    }

    private void setupMapView(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        this.markerOptions = new MarkerOptions().position(new LatLng(this.shopDetailsModel.getLat(),this.shopDetailsModel.getLng()))
                .title(this.shopDetailsModel.getShopName())
                .snippet(this.shopDetailsModel.getShopDetailsCity())
                .draggable(false);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.addMarker(this.markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(),15.0f));


        //the click listener chaining the event to the activity
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                shopDetailsContactFragmentListener.onMapClicked(new ShopDetailsModelForMap(
                        ShopDetailsContactFragment.this.markerOptions.getSnippet(), ShopDetailsContactFragment.this.markerOptions.getTitle(),
                        ShopDetailsContactFragment.this.markerOptions.getPosition()));
            }
        });

        //deactivating all gestures, cause we don't want the user to trash the whole app
        googleMap.getUiSettings().setAllGesturesEnabled(false);
    }


    private void renderAll() {
        this.shopDetailsShopName.setText(shopDetailsModel.getShopName());

        this.shopDetailsAddressLine1.setText(shopDetailsModel.getShopAddressLine1());
        this.shopDetailsAddressLine2.setText(this.shopDetailsModel.getShopAddressLine2());
        this.shopDetailsAddressLine3.setText(this.shopDetailsModel.getShopAddressLine3());

        this.shopDetailsPhone.setText(this.shopDetailsModel.getShopDetailsPhone());
        this.shopDetailsAuxiliaryPhone.setText(this.shopDetailsModel.getShopDetailsAuxPhone());

        this.shopDetailsCity.setText(this.shopDetailsModel.getShopDetailsCity());
        this.shopDetailsZipCode.setText(this.shopDetailsModel.getShopDetailsZip());

    }


    private void getParameters() {
        Bundle bundle = getArguments();
        this.shopDetailsModel = bundle.getParcelable(MODEL);
    }

    private void bindAll(View view) {
        this.facebookButton = (Button) view.findViewById(R.id.shop_details_facebook_button);
        this.whatsappButton = (Button) view.findViewById(R.id.shop_details_whatsapp_button);
        this.mapView = (MapView)view.findViewById(R.id.shop_details_map_view);
        this.mapViewLL = (LinearLayout)view.findViewById(R.id.mapViewLL) ;

        this.shopDetailsShopName = (TextView) view.findViewById(R.id.shop_details_shop_name);

        this.shopDetailsPhone = (TextView) view.findViewById(R.id.shop_details_phone);
        this.shopDetailsAuxiliaryPhone = (TextView) view.findViewById(R.id.shop_details_auxiliary_phone);

        //this.shopDetailsAddress = (TextView) view.findViewById(R.id.shop_details_address);
        this.shopDetailsAddressLine1 = (TextView) view.findViewById(R.id.shop_details_address_line_1);
        this.shopDetailsAddressLine2 = (TextView) view.findViewById(R.id.shop_details_address_line_2);
        this.shopDetailsAddressLine3 = (TextView) view.findViewById(R.id.shop_details_address_line_3);

        this.shopDetailsCity = (TextView) view.findViewById(R.id.shop_details_city);
        this.shopDetailsZipCode = (TextView) view.findViewById(R.id.shop_details_zip);
    }



    //setting up mapview

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        mapView.onStop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }
}
