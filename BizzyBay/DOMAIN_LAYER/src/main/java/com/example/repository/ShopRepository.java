package com.example.repository;

import com.example.ShopDetails;
import com.example.ShopList;
import com.example.exception.ErrorBundle;

import java.util.ArrayList;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public interface ShopRepository {
    public interface ShopListCallback{
        void OnShopListLoaded(int pageNumber,ArrayList<ShopList>shopList);
        void OnError(ErrorBundle errorBundle);
    }

    public interface ShopDetailsCallback{
        void OnShopDetailsLoaded(ShopDetails shopDetails);
        void OnError(ErrorBundle errorBundle);
    }

    public void getShopList(int pageNumber,ShopListCallback providedCallback);

    public void getShopDetails(int shopId,ShopDetailsCallback providedCallback);
}
