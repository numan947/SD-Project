package numan947.com.data_layer.entity.mapper;

import com.example.ShopDetails;
import com.example.ShopList;

import java.util.ArrayList;
import java.util.List;

import numan947.com.data_layer.entity.ShopDetailsEntity;
import numan947.com.data_layer.entity.ShopListEntity;

/**
 * @author numan947
 * @since 5/13/17.<br>
 **/

public class ShopEntityDataMapper {
    public ArrayList<ShopList> transform(List<ShopListEntity> entities1) {
        ArrayList<ShopList>shopLists = new ArrayList<>();
        ArrayList<ShopListEntity> entities = new ArrayList<>(entities1);

        for(ShopListEntity a: entities){

            ShopList toAdd = new ShopList();
            toAdd.setShopDetails(a.getShopDetails());
            toAdd.setShopDistance(a.getShopDistance()+" KM");
            toAdd.setShopId(a.getShopId());
            toAdd.setShopImage(a.getShopImage());
            toAdd.setShopLocation(a.getShopLocation());
            toAdd.setShopName(a.getShopName());
            toAdd.setShopType(a.getShopType());

            shopLists.add(toAdd);
        }

        return shopLists;
    }

    public ShopDetails transform(ShopDetailsEntity shopDetailsEntity) {
        ShopDetails shopDetails = new ShopDetails(shopDetailsEntity.getShopDetailsAboutUs(),
                shopDetailsEntity.getShopDetailsAboutUsImage(),shopDetailsEntity.getShopName(),
                shopDetailsEntity.getShopAddressLine1(),shopDetailsEntity.getShopAddressLine2(),
                shopDetailsEntity.getShopAddressLine3(),shopDetailsEntity.getShopDetailsPhone(),
                shopDetailsEntity.getShopDetailsAuxPhone(),shopDetailsEntity.getShopDetailsCity(),String.valueOf(shopDetailsEntity.getShopDetailsZip()),
                shopDetailsEntity.getShopDetailsImageViewPagerImages(),shopDetailsEntity.getLat(),shopDetailsEntity.getLng(),
                shopDetailsEntity.getShopUserName(),String.valueOf(shopDetailsEntity.getShopId()),shopDetailsEntity.getFacebookPage(),shopDetailsEntity.getWhatsappNumber());
        return shopDetails;
    }
}
