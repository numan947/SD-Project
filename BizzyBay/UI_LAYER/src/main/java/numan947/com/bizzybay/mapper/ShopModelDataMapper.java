package numan947.com.bizzybay.mapper;

import com.example.ShopList;

import java.util.ArrayList;

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
}
