package numan947.com.bizzybay.mapper;

import com.example.ShopDetails;
import com.example.ShopList;

import java.util.ArrayList;

import numan947.com.bizzybay.model.ShopDetailsModel;
import numan947.com.bizzybay.model.ShopListModel;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopModelDataMapper {

    public ArrayList<ShopListModel> transform(ArrayList<ShopList> shopList) {
        ArrayList<ShopListModel>shops = new ArrayList<>();

        for(ShopList a : shopList){
            ShopListModel model = new ShopListModel(a.getShopId(),a.getShopImage(),
                    a.getShopDetails(),a.getShopType(),a.getShopLocation(),a.getShopDistance(),
                    a.getShopName());
            shops.add(model);
        }

        return shops;
    }

    public ShopDetailsModel transform(ShopDetails shopDetails) {
        ShopDetailsModel model=new ShopDetailsModel(shopDetails.getShopDetailsAboutUs(),shopDetails.getShopDetailsAboutUsImage(),
                shopDetails.getShopName(),shopDetails.getShopAddressLine1(),shopDetails.getShopAddressLine2(),shopDetails.getShopAddressLine3(),
                shopDetails.getShopDetailsPhone(),shopDetails.getShopDetailsAuxPhone(),shopDetails.getShopDetailsCity(),shopDetails.getShopDetailsZip(),
                shopDetails.getShopDetailsImageViewPagerImages(),shopDetails.getLat(),shopDetails.getLng(),shopDetails.getShopUserName(),
                shopDetails.getShopId());

        return model;
    }
}
